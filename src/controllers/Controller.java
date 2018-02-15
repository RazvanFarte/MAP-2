package controllers;

import controllers.exceptions.ControllerException;
import datastructures.exceptions.EmptyStackException;
import models.statements.exceptions.StatementException;
import repo.IRepository;
import datastructures.IStack;
import models.statements.IStatement;
import models.statements.ProgramState;

public class Controller {

    private IRepository<ProgramState> programStates;

    public Controller(IRepository<ProgramState> programStates) {
        this.programStates = programStates;
    }

    private ProgramState executeStep(ProgramState state) throws ControllerException {
        IStack<IStatement> stack = state.getExecutionStack();

        IStatement statement = null;
        try {
            statement = stack.pop();
            return statement.execute(state);
        } catch (EmptyStackException e) {
            throw new ControllerException("No more statements to execute on execution stack.");
        } catch (StatementException e) {
            throw new ControllerException("Cannot execute statement: " + statement.toString());
        }
    }

    public void execute() throws ControllerException {
        ProgramState state = programStates.getCurrentEntity();

        System.out.print(state.toString());

        while(!state.getExecutionStack().isEmpty()){
            executeStep(state);
            System.out.print(state.toString());
            System.out.println("========================================");
        }

        System.out.println("Execution terminated!");

    }
}