package models.statements;

import models.statements.exceptions.StatementException;

public interface IStatement {

    public ProgramState execute(ProgramState programState) throws StatementException;
}
