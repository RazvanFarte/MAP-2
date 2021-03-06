package models.statements;

import datastructures.List;
import models.statements.exceptions.StatementException;

import java.util.function.Consumer;

public class MultipleStatements implements IStatement {

    java.util.List<IStatement> statements;

    public MultipleStatements(java.util.List<IStatement> statements) {
        this.statements = statements;

    }

    public java.util.List<IStatement> getStatements() {
        return statements;
    }

    public void setStatements(java.util.List<IStatement> statements) {
        this.statements = statements;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        statements.forEach(new Consumer<IStatement>() {
            @Override
            public void accept(IStatement statement) {
                sb.append(statement.toString());
                sb.append("; ");
            }
        });

        return sb.toString();
    }

    @Override
    public ProgramState execute(ProgramState programState) throws StatementException {

        statements.forEach(new Consumer<IStatement>() {
            @Override
            public void accept(IStatement statement) {
                programState.getExecutionStack().push(statement);
            }
        });

        return null;
    }
}
