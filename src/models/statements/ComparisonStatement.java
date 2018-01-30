package models.statements;

import datastructures.Stack2;

public class ComparisonStatement implements IStatement {

    private IStatement first;
    private IStatement second;

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
        Stack2<IStatement> stack = programState.getExecutionStack();
        stack.push(snd);
        stack.push(first);
        return programState;
    }

}
