package models.statements;

import datastructures.Dictionary;
import datastructures.IStack;
import datastructures.Stack2;
import models.statements.exceptions.StatementException;

public class ForkStatement implements IStatement {

    IStatement statement;

    public ForkStatement(IStatement statement) {
        this.statement = statement;
    }

    public IStatement getStatement() {
        return statement;
    }

    public void setStatement(IStatement statement) {
        this.statement = statement;
    }

    @Override
    public String toString() {
        return "ForkStatement{" +
                "statement=" + statement +
                '}';
    }

    @Override
    public ProgramState execute(ProgramState programState) throws StatementException {

        IStack<IStatement> newExecutionStack = new Stack2<>();
        newExecutionStack.push(statement);

        Dictionary<String, Integer> newSymbolTable =
                (Dictionary<String, Integer>) ((Dictionary<String, Integer>) programState.getSymbolTable()).clone();


        ProgramState newProgramState = new ProgramState(
                programState.id * 10,
                newExecutionStack,
                newSymbolTable,
                programState.output,
                programState.fileDescriptors,
                programState.heap,
                this.statement);

        return newProgramState;
    }
}
