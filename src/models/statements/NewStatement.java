package models.statements;

import datastructures.exceptions.NegativeAddressException;
import datastructures.exceptions.NotAllocatedAddressException;
import datastructures.exceptions.TakenAddressException;
import models.expressions.Expression;
import models.expressions.exceptions.DivisionByZeroException;
import models.expressions.exceptions.NotDefinedException;
import models.expressions.exceptions.UnknownOperatorException;
import models.statements.exceptions.StatementException;

public class NewStatement implements IStatement {

    String variableName;
    Expression expression;

    public NewStatement(String variableName, Expression expression) {
        this.variableName = variableName;
        this.expression = expression;
    }

    public String getVariableName() {
        return variableName;
    }

    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "NewStatement{" +
                "variableName='" + variableName + '\'' +
                ", expression=" + expression +
                '}';
    }

    @Override
    public ProgramState execute(ProgramState programState) throws StatementException {

        int result;
        try {
            result = this.expression.evaluate(programState.getSymbolTable(), programState.getHeap());
        } catch (DivisionByZeroException | UnknownOperatorException | NotDefinedException e) {
            throw new StatementException(e.getMessage());
        }

        //Generates a new memory location
        int newAddress = programState.getHeap().getEmptyAddress();

        try {
            //Adds the value to the heap
            programState.getHeap().put(newAddress, result);
            programState.getSymbolTable().put(this.variableName, newAddress);
        } catch (NegativeAddressException e) {
            e.printStackTrace();
        } catch (TakenAddressException e) {
            //If location took, overwrite the location address
            try {
                programState.getHeap().replace(newAddress, result);

                //Assign to the pointer the new heap address location
                programState.getSymbolTable().replace(this.variableName, newAddress);
            } catch (NegativeAddressException e1) {
                e1.printStackTrace();
            } catch (NotAllocatedAddressException e1) {
                e1.printStackTrace();
            }
        }

        return null;
    }
}
