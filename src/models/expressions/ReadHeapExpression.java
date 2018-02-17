package models.expressions;

import datastructures.IDictionary;
import models.expressions.exceptions.DivisionByZeroException;
import models.expressions.exceptions.NotDefinedException;
import models.expressions.exceptions.UnknownOperatorException;

public class ReadHeapExpression extends Expression {

    String variableName;

    public ReadHeapExpression(String variableName) {
        this.variableName = variableName;
    }

    public String getVariableName() {
        return variableName;
    }

    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }

    @Override
    public int evaluate(IDictionary<String, Integer> symbolTable) throws DivisionByZeroException, UnknownOperatorException, NotDefinedException {
        int addressValue = symbolTable.get(variableName);

        return 0;
    }
}
