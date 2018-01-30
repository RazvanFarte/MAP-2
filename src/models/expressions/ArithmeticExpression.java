package models.expressions;

import datastructures.IDictionary;

public class ArithmeticExpression extends Expression {

    Expression expression1;
    Expression expression2;
    char operator;

    @Override
    public int evaluate(IDictionary<String, Integer> symbolTable) {
        switch (operator) {
            case '+': return expression1.evaluate(symbolTable) + expression2.evaluate(symbolTable);
            case '-': return expression1.evaluate(symbolTable) - expression2.evaluate(symbolTable);
            case '*': return expression1.evaluate(symbolTable) * expression2.evaluate(symbolTable);
            case '/': return expression1.evaluate(symbolTable) / expression2.evaluate(symbolTable);
            default: return 0;
        }
    }
}
