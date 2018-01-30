package models.statements;

import datastructures.Dictionary;
import datastructures.Stack2;

public class AssignStatement implements IStatement {

    String id;
    Expression expression;

    @Override
    public String toString() {
        return new String().concat(id).concat("=").concat(expression.toString());
    }

    @Override
    public ProgramState execute(ProgramState programState) {
        Stack2<IStatement> stack = programState.getExecutionStack();
        Dictionary<String, Integer> symbolTable = programState.getSymbolTable();

        int value = expression.evaluate(symbolTable);

        if( symbolTable.isDefined(id)) {
            symbolTable.update(id, value);
        } else {
            symbolTable.add(id, value);
        }

        return programState;

    }
}
