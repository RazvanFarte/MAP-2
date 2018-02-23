package models.statements;

import models.statements.exceptions.StatementException;

public class AwaitStatement implements IStatement {

    private String variableName;

    public AwaitStatement(String variableName) {
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
                throw new StatementException("Value not in latch table");
            }

            if(programState.getLatchTable().get(latchAddress) > 0) {
                programState.getExecutionStack().push(this);
            }
        }


        return null;
    }

    @Override
    public String toString() {
        return "AwaitStatement{" +
                "variableName='" + variableName + '\'' +
                '}';
    }
}
