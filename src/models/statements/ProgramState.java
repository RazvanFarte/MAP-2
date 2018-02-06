package models.statements;

import datastructures.IDictionary;
import datastructures.IList;
import datastructures.IStack;

import java.util.Formatter;

public class ProgramState {

    IStack<IStatement> executionStack;
    IDictionary<String, Integer> symbolTable;
    IList<Integer> output;

    IStatement originalProgram;

    public ProgramState(IStack<IStatement> executionStack, IDictionary<String, Integer> symbolTable,
                        IList<Integer> output, IStatement originalProgram) {
        this.executionStack = executionStack;
        this.symbolTable = symbolTable;
        this.output = output;
        this.originalProgram = originalProgram;
    }

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

    public IStatement getOriginalProgram() {
        return originalProgram;
    }

    public void setOriginalProgram(IStatement originalProgram) {
        this.originalProgram = originalProgram;
    }

    @Override
    public String toString() {
        //throw new RuntimeException("Must be implemented");
//        Formatter mFormatter = new Formatter();

//        return mFormatter.format("Program state:\n\tExecution stack: %0\n\tTable of values: %1\n\tPrinted values: %2\n",
//            this.executionStack.toString(),
//            this.symbolTable.toString(),
//            this.output.toString()
//        ).toString();
        StringBuilder sb = new StringBuilder();

        return new StringBuilder().append("Program state:\n")
                .append("\tExecution stack:\n\t\t").append(executionStack.toString())
                .append("\tTable of values:\n\t\t").append(symbolTable.toString())
                .append("\tOutput values:\n\t\t").append(output.toString()).toString();
    }
}
