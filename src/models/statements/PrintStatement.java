package models.statements;

import models.expressions.Expression;
import models.expressions.exceptions.DivisionByZeroException;
import models.expressions.exceptions.NotDefinedException;
import models.expressions.exceptions.UnknownOperatorException;
import models.statements.exceptions.StatementException;

public class PrintStatement implements IStatement {

    Expression expression;

    public PrintStatement(Expression expression) {
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "print(".concat(expression.toString()).concat(")");
    }

    @Override
    public ProgramState execute(ProgramState programState) throws StatementException {

        int value;
        try {
            value = expression.evaluate(programState.getSymbolTable());
        } catch (DivisionByZeroException | UnknownOperatorException | NotDefinedException e) {
            throw new StatementException("Cannot execute expression in print statement " + this.toString());
        }

        programState.output.add(new Integer(value));

        return programState;
    }
}
