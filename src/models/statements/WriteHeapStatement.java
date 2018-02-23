package models.statements;

import datastructures.exceptions.NegativeAddressException;
import datastructures.exceptions.NotAllocatedAddressException;
import models.expressions.Expression;
import models.expressions.exceptions.DivisionByZeroException;
import models.expressions.exceptions.NotDefinedException;
import models.expressions.exceptions.UnknownOperatorException;
import models.statements.exceptions.StatementException;

public class WriteHeapStatement implements IStatement {

    String variableName;
    Expression expression;

    public WriteHeapStatement(String variableName, Expression expression) {
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
    public String toString() {
        return "WriteHeapStatement{" +
                "variableName='" + variableName + '\'' +
                ", expression=" + expression +
                '}';
    }

    @Override
    public ProgramState execute(ProgramState programState) throws StatementException {

        int newValue;
        try {
            newValue = expression.evaluate(programState.getSymbolTable(), programState.getHeap());
        } catch (DivisionByZeroException | UnknownOperatorException | NotDefinedException e) {
            throw new StatementException("Cannot evaluate expression.", e);
        }

        int address = programState.getSymbolTable().get(variableName);

        try {
            programState.getHeap().replace(address, newValue);
        } catch (NegativeAddressException | NotAllocatedAddressException e) {
            throw new StatementException("Cannot rewrite heap.", e);
        }


        return null;
    }
}
