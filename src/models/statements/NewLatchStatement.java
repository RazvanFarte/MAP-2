package models.statements;

import datastructures.ICountDownLatch;
import datastructures.IStack;
import models.expressions.Expression;
import models.expressions.exceptions.DivisionByZeroException;
import models.expressions.exceptions.NotDefinedException;
import models.expressions.exceptions.UnknownOperatorException;
import models.statements.exceptions.StatementException;

public class NewLatchStatement implements IStatement{

    private String variableName;
    private Expression expression;

    public NewLatchStatement(String variableName, Expression expression) {
        this.variableName = variableName;
        this.expression = expression;
    }

    public String getVariableName() {
        return variableName;
    }

    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState programState) throws StatementException {

        int value;
        try {
            value = expression.evaluate(programState.getSymbolTable(), programState.getHeap());
        } catch (DivisionByZeroException | UnknownOperatorException | NotDefinedException e) {
            throw new StatementException("New latch could not be executed", e);
        }

        int freeAddress;
        synchronized (programState.getLatchTable()) {
            freeAddress = programState.getLatchTable().getEmptyAddress();
            programState.getLatchTable().put(freeAddress, value);
        }

        if(programState.getSymbolTable().containsKey(this.variableName))
            programState.getSymbolTable().replace(this.variableName, freeAddress);
        else
            programState.getSymbolTable().put(this.variableName, freeAddress);

        return null;
    }

    @Override
    public String toString() {
        return "NewLatchStatement{" +
                "variableName='" + variableName + '\'' +
                ", expression=" + expression +
                '}';
    }
}
