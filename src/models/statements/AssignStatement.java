package models.statements;

import datastructures.Dictionary;
import datastructures.IDictionary;
import datastructures.IStack;
import datastructures.Stack2;
import models.expressions.Expression;

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
    public ProgramState execute(ProgramState programState) {
        IStack<IStatement> stack = programState.getExecutionStack();
        IDictionary<String, Integer> symbolTable = programState.getSymbolTable();

        int value = expression.evaluate(symbolTable);

        if( symbolTable.get(id) != null) {
            symbolTable.replace(id, value);
        } else {
            symbolTable.put(id, value);
        }

        return programState;
    }
}
