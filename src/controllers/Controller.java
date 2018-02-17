package controllers;

import controllers.exceptions.ControllerException;
import datastructures.exceptions.EmptyStackException;
import models.statements.exceptions.StatementException;
import repo.ILogRepository;
import repo.IRepository;
import datastructures.IStack;
import models.statements.IStatement;
import models.statements.ProgramState;

import java.io.IOException;

public class Controller {

    private ILogRepository programStates;

    public Controller(ILogRepository programStates) {
        this.programStates = programStates;
    }

    private ProgramState executeStep(ProgramState state) throws ControllerException {
        IStack<IStatement> stack = state.getExecutionStack();

        IStatement statement = null;
        try {
            statement = stack.pop();
            return statement.execute(state);
        } catch (EmptyStackException e) {
            throw new ControllerException("No more statements to execute on execution stack.", e);
        } catch (StatementException e) {
            throw new ControllerException("Cannot execute statement: " + statement.toString(), e);
        }
    }

    public void execute() throws ControllerException, IOException {
        ProgramState state = programStates.getCurrentEntity();

        System.out.print(state.toString());

        while(!state.getExecutionStack().isEmpty()){
            executeStep(state);
            System.out.print(state.toString());
            System.out.println("========================================");
            programStates.logProgramStates();

        }

        System.out.println("Execution terminated!");

    }

    @Override
    public String toString() {
        return "Controller{" +
                programStates.getCurrentEntity().getExecutionStack().toString() +
                '}';
    }
}