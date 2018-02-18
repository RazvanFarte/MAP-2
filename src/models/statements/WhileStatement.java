package models.statements;

import models.expressions.Expression;
import models.expressions.exceptions.DivisionByZeroException;
import models.expressions.exceptions.NotDefinedException;
import models.expressions.exceptions.UnknownOperatorException;
import models.statements.exceptions.StatementException;

public class WhileStatement implements IStatement {
    Expression testExpression;
    IStatement executeStatement;

    public WhileStatement(Expression testExpression, IStatement executeStatement) {
        this.testExpression = testExpression;
        this.executeStatement = executeStatement;
    }

    public Expression getTestExpression() {
        return testExpression;
    }

    public void setTestExpression(Expression testExpression) {
        this.testExpression = testExpression;
    }

    public IStatement getExecuteStatement() {
        return executeStatement;
    }

    public void setExecuteStatement(IStatement executeStatement) {
        this.executeStatement = executeStatement;
    }

    @Override
    public String toString() {
        return "WhileStatement{" +
                "testExpression=" + testExpression +
                ", executeStatement=" + executeStatement +
                '}';
    }

    @Override
    public ProgramState execute(ProgramState programState) throws StatementException {

        try {
            if(this.testExpression.evaluate(programState.getSymbolTable(), programState.getHeap()) != 0){
                programState.getExecutionStack().push(this);
                programState.getExecutionStack().push(this.executeStatement);
            }

        } catch (DivisionByZeroException | UnknownOperatorException | NotDefinedException e) {
            throw new StatementException("Cannot evaluate expression " + testExpression.toString(), e);
        }

        return programState;
    }
}
