package models.expressions;

import datastructures.IDictionary;

public abstract class Expression {

    public abstract int evaluate(IDictionary<String, Integer> symbolTable);

    @Override
    public String toString() {
        return "Expression";
    }
}
