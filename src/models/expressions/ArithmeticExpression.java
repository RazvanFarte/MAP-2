package models.expressions;

import datastructures.IDictionary;
import datastructures.IHeap;
import models.expressions.exceptions.DivisionByZeroException;
import models.expressions.exceptions.NotDefinedException;
import models.expressions.exceptions.UnknownOperatorException;

public class ArithmeticExpression extends Expression {

    Expression expression1;
    Expression expression2;
    char operator;

    public ArithmeticExpression(Expression expression1, Expression expression2, char operator) {
        this.expression1 = expression1;
        this.expression2 = expression2;
        this.operator = operator;
    }

    public Expression getExpression1() {
        return expression1;
    }

    public void setExpression1(Expression expression1) {
        this.expression1 = expression1;
    }

    public Expression getExpression2() {
        return expression2;
    }

    public void setExpression2(Expression expression2) {
        this.expression2 = expression2;
    }

    public char getOperator() {
        return operator;
    }

    public void setOperator(char operator) {
        this.operator = operator;
    }

    @Override
    public int evaluate(IDictionary<String, Integer> symbolTable, IHeap heap) throws DivisionByZeroException, UnknownOperatorException, NotDefinedException {
        switch (operator) {
            case '+': return expression1.evaluate(symbolTable, heap) + expression2.evaluate(symbolTable, heap);
            case '-': return expression1.evaluate(symbolTable, heap) - expression2.evaluate(symbolTable, heap);
            case '*': return expression1.evaluate(symbolTable, heap) * expression2.evaluate(symbolTable, heap);
            case '/':
                if(expression2.evaluate(symbolTable, heap) == 0)
                    throw new DivisionByZeroException("Division by zero for expresion" + this.toString());
                return expression1.evaluate(symbolTable, heap) / expression2.evaluate(symbolTable, heap);
            default: throw new UnknownOperatorException("Operation +'" + operator + "' does not exist.");
        }
    }

    @Override
    public String toString() {
        //throw new RuntimeException("Method must be implemented");
        return this.expression1.toString() + " " + this.operator + " " + this.expression2;
    }
}
