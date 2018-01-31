package controllers;

import Repo.IRepository;
import datastructures.IStack;
import models.statements.IStatement;
import models.statements.ProgramState;

public class Controller {

    private IRepository<ProgramState> programStates;

    public Controller(IRepository<ProgramState> repo){
        this.programStates = repo;
    }

    private ProgramState executeStep(ProgramState state) {
        IStack<IStatement> stack = state.getExecutionStack();

        IStatement statement = stack.pop();
        return statement.execute(state);
    }

    public void execute() {
        ProgramState state = programStates.getCurrentEntity();

        while(!state.getExecutionStack().isEmpty()){
            System.out.print(state.toString());
            executeStep(state);
        }

    }
}