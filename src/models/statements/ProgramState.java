package models.statements;

import datastructures.*;
import datastructures.exceptions.EmptyStackException;
import models.statements.exceptions.StatementException;

public class ProgramState {

    int id;
    IStack<IStatement> executionStack;
    IDictionary<String, Integer> symbolTable;
    IList<Integer> output;
    IFileTable fileDescriptors;
    IHeap heap;
    ICountDownLatch latchTable;

    IStatement originalProgram;

    public ProgramState(int id, IStack<IStatement> executionStack, IDictionary<String, Integer> symbolTable, IList<Integer> output, IFileTable fileDescriptors, IHeap heap, ICountDownLatch latchTable, IStatement originalProgram) {
        this.id = id;
        this.executionStack = executionStack;
        this.symbolTable = symbolTable;
        this.output = output;
        this.fileDescriptors = fileDescriptors;
        this.heap = heap;
        this.latchTable = latchTable;
        this.originalProgram = originalProgram;
    }

    public ICountDownLatch getLatchTable() {
        return latchTable;
    }

    public void setLatchTable(ICountDownLatch latchTable) {
        this.latchTable = latchTable;
    }

    public IHeap getHeap() {
        return heap;
    }

    public void setHeap(IHeap heap) {
        this.heap = heap;
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

    public IFileTable getFileDescriptors() {
        return fileDescriptors;
    }

    public void setFileDescriptors(IFileTable fileDescriptors) {
        this.fileDescriptors = fileDescriptors;
    }

    public IStatement getOriginalProgram() {
        return originalProgram;
    }

    public void setOriginalProgram(IStatement originalProgram) {
        this.originalProgram = originalProgram;
    }

    public boolean isNotCompleted() {
        return !this.executionStack.isEmpty();
    }

    public ProgramState oneStep() throws EmptyStackException, StatementException {
        IStatement statement = this.executionStack.pop();
        return statement.execute(this);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        return new StringBuilder().append("Program state:\n")
                .append(this.id).append('\n')
                .append("\tExecution stack:\n\t\t").append(executionStack.toString())
                .append("\tTable of values:\n\t\t").append(symbolTable.toString())
                .append("\tOutput values:\n\t\t").append(output.toString())
                .append("\tFile descriptors:\n\t\t").append(this.fileDescriptors.toString())
                .append("\tHeap:\n\t\t").append(this.heap.toString())
                .toString();
    }
}
