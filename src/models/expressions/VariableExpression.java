package models.expressions;

import datastructures.IDictionary;
import models.expressions.exceptions.NotDefinedException;

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
    public int evaluate(IDictionary<String, Integer> symbolTable) throws NotDefinedException {
        if(!symbolTable.containsKey(id))
            throw new NotDefinedException("Variable with id #" + id + " not defined in Table of Symbols.");
        return symbolTable.get(id);
    }


    @Override
    public String toString() {
        return id;
    }
}
