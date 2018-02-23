package models.expressions;

import datastructures.IDictionary;
import datastructures.IHeap;

public class ConstantExpression extends Expression {

    int constantValue;

    public ConstantExpression(int constantValue) {
        this.constantValue = constantValue;
    }

    public int getConstantValue() {
        return constantValue;
    }

    public void setConstantValue(int constantValue) {
        this.constantValue = constantValue;
    }

    @Override
    public int evaluate(IDictionary<String, Integer> symbolTable, IHeap heap) {
        return constantValue;
    }

    @Override
    public String toString() {
        //throw new RuntimeException("Method must be implemented");

        return "" + constantValue;
    }
}
