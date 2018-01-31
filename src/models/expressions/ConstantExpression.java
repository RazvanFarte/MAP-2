package models.expressions;

import datastructures.IDictionary;

public class ConstantExpression extends Expression {

    int constantValue;

    @Override
    public int evaluate(IDictionary<String, Integer> symbolTable) {
        return constantValue;
    }

    @Override
    public String toString() {
        throw new RuntimeException("Method must be implemented");
    }
}
