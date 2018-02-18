package controllers;

import controllers.exceptions.ControllerException;
import datastructures.*;
import datastructures.exceptions.EmptyStackException;
import datastructures.exceptions.HeapException;
import datastructures.exceptions.NegativeAddressException;
import datastructures.exceptions.NotAllocatedAddressException;
import models.statements.exceptions.StatementException;
import repo.ILogRepository;
import repo.IRepository;
import models.statements.IStatement;
import models.statements.ProgramState;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Controller {

    private ILogRepository programStates;
    private ExecutorService executorService;

    public Controller(ILogRepository programStates) {
        this.programStates = programStates;
    }

    public List<ProgramState> removeCompletedPrograms(List<ProgramState> programStates){
        return programStates.stream().filter(x -> x.isNotCompleted()).collect(Collectors.toList());
    }

    public void executeOneStepForAllThreads(List<ProgramState> programStates) {

        programStates.forEach(programState -> {
            try {
                this.programStates.logProgramStates(programState);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        List<Callable<ProgramState>> callList = programStates.
                stream().
                map((ProgramState p) -> (Callable<ProgramState>)(() -> {return p.oneStep();})).
                collect(Collectors.toList());

        List<ProgramState> newProgramStates;
        try {
            newProgramStates = executorService.invokeAll(callList)
                    .stream()
                    .map(future -> {
                        try {
                            return future.get();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        }
                    })
                    .filter(p -> p != null)
                    .collect(Collectors.toList());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        programStates.addAll(newProgramStates);

        this.programStates.setEntities();

    }

    public void execute() throws ControllerException, IOException {
        ProgramState state = programStates.getCurrentEntity();

        System.out.print(state.toString());

        while(!state.getExecutionStack().isEmpty()){
            executeStep(state);
            try {
                state.getHeap().garbageCollect(state.getSymbolTable().values());
            } catch (HeapException e) {
                e.printStackTrace();
            }

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