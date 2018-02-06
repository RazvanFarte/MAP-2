package models.expressions;

import datastructures.IDictionary;
import models.expressions.exceptions.DivisionByZeroException;
import models.expressions.exceptions.NotDefinedException;
import models.expressions.exceptions.UnknownOperatorException;

public abstract class Expression {

    public abstract int evaluate(IDictionary<String, Integer> symbolTable) throws DivisionByZeroException, UnknownOperatorException, NotDefinedException;

    @Override
    public String toString() {
        return "Expression";
    }
}
