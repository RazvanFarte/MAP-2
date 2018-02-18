package models.statements;

import models.expressions.Expression;
import models.expressions.exceptions.DivisionByZeroException;
import models.expressions.exceptions.NotDefinedException;
import models.expressions.exceptions.UnknownOperatorException;
import models.statements.exceptions.StatementException;

public class IfStatement implements IStatement {

    Expression expression;
    IStatement thenStatement;
    IStatement elseStatement;

    public IfStatement(Expression expression, IStatement thenStatement, IStatement elseStatement) {
        this.expression = expression;
        this.thenStatement = thenStatement;
        this.elseStatement = elseStatement;
    }

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public IStatement getThenStatement() {
        return thenStatement;
    }

    public void setThenStatement(IStatement thenStatement) {
        this.thenStatement = thenStatement;
    }

    public IStatement getElseStatement() {
        return elseStatement;
    }

    public void setElseStatement(IStatement elseStatement) {
        this.elseStatement = elseStatement;
    }

    @Override
    public String toString() {
        return "IF(" + expression.toString() + ")THEN(" + thenStatement + ")ELSE(" + elseStatement.toString() + ")";
    }

    @Override
    public ProgramState execute(ProgramState programState) throws StatementException {

        int expressionValue;
        try {
            expressionValue = expression.evaluate(programState.getSymbolTable(), programState.getHeap());
        } catch (DivisionByZeroException | UnknownOperatorException | NotDefinedException e) {
            throw new StatementException("Cannot execute expression in if statement: " + this.toString());
        }

        if(expressionValue == 0) {
            programState.getExecutionStack().push(this.elseStatement);
        } else {
            programState.getExecutionStack().push(this.thenStatement);
        }

        return null;
    }
}
