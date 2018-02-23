package models.statements;

import datastructures.IStack;

public class ComparisonStatement implements IStatement {

    private IStatement first;
    private IStatement second;

    public ComparisonStatement(IStatement first, IStatement second) {
        this.first = first;
        this.second = second;
    }

    public IStatement getFirst() {
        return first;
    }

    public void setFirst(IStatement first) {
        this.first = first;
    }

    public IStatement getSecond() {
        return second;
    }

    public void setSecond(IStatement second) {
        this.second = second;
    }

    @Override
    public String toString() {

        StringBuilder sBld = new StringBuilder();

        sBld.append("(");
        sBld.append(first);
        sBld.append(";");
        sBld.append(second);
        sBld.append(")");

        return sBld.toString();
    }

    @Override
    public ProgramState execute(ProgramState programState) {
        IStack<IStatement> stack = programState.getExecutionStack();
        stack.push(second);
        stack.push(first);
        return null;
    }

}
