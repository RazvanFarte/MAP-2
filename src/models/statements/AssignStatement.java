package models.statements;

import datastructures.IDictionary;
import models.expressions.Expression;
import models.expressions.exceptions.DivisionByZeroException;
import models.expressions.exceptions.NotDefinedException;
import models.expressions.exceptions.UnknownOperatorException;
import models.statements.exceptions.StatementException;

public class AssignStatement implements IStatement {


    String id;
    Expression expression;

    public AssignStatement(String id, Expression expression) {
        this.id = id;
        this.expression = expression;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    @Override
    public String toString() {
        return new String().concat(id).concat("=").concat(expression.toString());
    }

    @Override
    public ProgramState execute(ProgramState programState) throws StatementException {
        IDictionary<String, Integer> symbolTable = programState.getSymbolTable();

        int value;
        try {
            value = expression.evaluate(symbolTable, programState.getHeap());
        } catch (DivisionByZeroException | UnknownOperatorException | NotDefinedException e) {
            throw new StatementException("Cannot execute expression in assignment statement " + this.toString());
        }

        if( symbolTable.get(id) != null) {
            symbolTable.replace(id, value);
        } else {
            symbolTable.put(id, value);
        }

        return programState;
    }
}
