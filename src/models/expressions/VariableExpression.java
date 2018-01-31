package models.expressions;

import datastructures.IDictionary;

public class VariableExpression extends Expression {

    String id;

    public VariableExpression(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int evaluate(IDictionary<String, Integer> symbolTable) {
        return symbolTable.get(id);
    }

    @Override
    public String toString() {
        throw new RuntimeException("To be implemented");
    }
}
