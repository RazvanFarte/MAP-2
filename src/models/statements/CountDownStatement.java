package models.statements;

import datastructures.ICountDownLatch;
import datastructures.LatchTable;
import models.statements.exceptions.StatementException;

public class CountDownStatement implements IStatement {

    private String variableName;

    public CountDownStatement(String variableName) {
        this.variableName = variableName;
    }

    public String getVariableName() {
        return variableName;
    }

    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }

    @Override
    public ProgramState execute(ProgramState programState) throws StatementException {

        Integer index = programState.getSymbolTable().get(this.variableName);

        if(index == null) {
            throw new StatementException("Value not in symbol table");
        }

        synchronized (programState.getLatchTable()) {
            Integer latchAddress = programState.getLatchTable().get(index);

            if(latchAddress == null) {
                return null;
            }

            int latchValue = programState.getLatchTable().get(latchAddress);
            if(latchValue > 0) {
                programState.getLatchTable().replace(latchAddress, latchValue - 1);
                programState.getOutput().add(programState.id);
            }
        }

        return null;
    }

    @Override
    public String toString() {
        return "CountDownStatement{" +
                "variableName='" + variableName + '\'' +
                '}';
    }
}
