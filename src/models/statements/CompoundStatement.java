package models.statements;

import models.statements.exceptions.StatementException;

public class CompoundStatement implements IStatement {

    private IStatement firstStatement;
    private IStatement secondStatement;


    public CompoundStatement(IStatement firstStatement, IStatement secondStatement) {
        this.firstStatement = firstStatement;
        this.secondStatement = secondStatement;
    }

    public IStatement getFirstStatement() {
        return firstStatement;
    }

    public void setFirstStatement(IStatement firstStatement) {
        this.firstStatement = firstStatement;
    }

    public IStatement getSecondStatement() {
        return secondStatement;
    }

    public void setSecondStatement(IStatement secondStatement) {
        this.secondStatement = secondStatement;
    }

    @Override
    public ProgramState execute(ProgramState programState) throws StatementException {
        programState.getExecutionStack().push(this.secondStatement);
        programState.getExecutionStack().push(this.firstStatement);

        return null;
    }

    @Override
    public String toString() {
        return "(" + firstStatement +
                ", " + secondStatement +
                ')';
    }
}
