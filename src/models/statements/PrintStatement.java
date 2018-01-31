package models.statements;

import models.expressions.Expression;

public class PrintStatement implements IStatement {

    Expression exp;

    public PrintStatement(Expression exp) {
        this.exp = exp;
    }

    public Expression getExp() {
        return exp;
    }

    public void setExp(Expression exp) {
        this.exp = exp;
    }

    @Override
    public String toString() {
        return "print(".concat(exp.toString()).concat(")");
    }

    @Override
    public ProgramState execute(ProgramState programState) {
        return programState;
    }
}
