package models.statements;

import datastructures.IDictionary;
import datastructures.IList;
import datastructures.IStack;

public class ProgramState {



    IStack<IStatement> executionStack;
    IDictionary<String, Integer> symbolTable;
    IList<Integer> output;

    IStatement originalProgram;

    public IStack<IStatement> getExecutionStack() {
        return executionStack;
    }

    public void setExecutionStack(IStack<IStatement> executionStack) {
        this.executionStack = executionStack;
    }

    public IDictionary<String, Integer> getSymbolTable() {
        return symbolTable;
    }

    public void setSymbolTable(IDictionary<String, Integer> symbolTable) {
        this.symbolTable = symbolTable;
    }

    public IList<Integer> getOutput() {
        return output;
    }

    public void setOutput(IList<Integer> output) {
        this.output = output;
    }
}
