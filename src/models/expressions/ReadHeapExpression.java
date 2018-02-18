package models.expressions;

import datastructures.IDictionary;
import datastructures.IHeap;
import datastructures.exceptions.NegativeAddressException;
import datastructures.exceptions.NotAllocatedAddressException;
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

    public int evaluate(IDictionary<String, Integer> symbolTable, IHeap heap) throws DivisionByZeroException, UnknownOperatorException, NotDefinedException {
        int addressValue = symbolTable.get(variableName);

        int value = 0;
        try {
            value = heap.get(addressValue);
        } catch (NotAllocatedAddressException e) {
            e.printStackTrace();
        } catch (NegativeAddressException e) {
            e.printStackTrace();
        }

        return value;
    }

    @Override
    public String toString() {
        return "ReadHeapExpression{" +
                "variableName='" + variableName + '\'' +
                '}';
    }
}
