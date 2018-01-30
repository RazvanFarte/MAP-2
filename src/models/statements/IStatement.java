package models.statements;

public interface IStatement {

    public ProgramState execute(ProgramState programState);
}
