package models.statements;

public class PrintStatement implements IStatement {

    Expression exp;

    @Override
    public String toString() {
        return "print(".concat(exp.toString()).concat(")");
    }

    @Override
    public ProgramState execute(ProgramState programState) {
        return programState;
    }
}
