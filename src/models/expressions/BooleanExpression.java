package models.expressions;

import datastructures.IDictionary;
import datastructures.IHeap;
import models.expressions.exceptions.DivisionByZeroException;
import models.expressions.exceptions.ExpressionsException;
import models.expressions.exceptions.NotDefinedException;
import models.expressions.exceptions.UnknownOperatorException;

public class BooleanExpression extends Expression {

    public enum BooleanOperator{
        LT, LE, GT, GE, EQ, NE
    }

    Expression first;
    Expression second;
    BooleanOperator operator;

    public BooleanExpression(Expression first, Expression second, BooleanOperator operator) {
        this.first = first;
        this.second = second;
        this.operator = operator;
    }

    public Expression getFirst() {
        return first;
    }

    public void setFirst(Expression first) {
        this.first = first;
    }

    public Expression getSecond() {
        return second;
    }

    public void setSecond(Expression second) {
        this.second = second;
    }

    public BooleanOperator getOperator() {
        return operator;
    }

    public void setOperator(BooleanOperator operator) {
        this.operator = operator;
    }

    @Override
    public String toString() {
        return "BooleanExpression{" +
                "first=" + first +
                ", second=" + second +
                ", operator=" + operator.toString() +
                '}';
    }

    @Override
    public int evaluate(IDictionary<String, Integer> symbolTable, IHeap heap) throws UnknownOperatorException, DivisionByZeroException, NotDefinedException {
        int first = this.first.evaluate(symbolTable, heap);
        int second = this.second.evaluate(symbolTable, heap);

        switch (operator) {
            case EQ:
                return first == second ? 1 : 0;
            case GE:
                return first >= second ? 1 : 0;
            case GT:
                return first > second ? 1 : 0;
            case LE:
                return first <= second ? 1 : 0;
            case LT:
                return first < second ? 1 : 0;
            case NE:
                return first != second ? 1 : 0;
            default:
                throw new UnknownOperatorException("Operator not defined");
        }
    }
}
