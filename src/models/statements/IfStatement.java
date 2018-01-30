package models.statements;

public class IfStatement implements IStatement {

    Expression expression;
    IStatement thenStatement;
    IStatement elseStatement;

    public IfStatement(Expression expression, IStatement thenStatement, IStatement elseStatement) {
        this.expression = expression;
        this.thenStatement = thenStatement;
        this.elseStatement = elseStatement;
    }

    @Override
    public String toString() {
        return "IF(" + expression.toString() + ")THEN(" + thenStatement + ")ELSE(" + elseStatement.toString() + ")";
    }

    @Override
    public ProgramState execute(ProgramState programState) {
        return programState;
    }
}
