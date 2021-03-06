package controllers;

import controllers.exceptions.ControllerException;
import datastructures.IList;
import models.statements.ProgramState;
import repo.ILogRepository;

import java.io.IOException;
import java.net.ConnectException;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Controller {

    private ILogRepository programStates;
    private ExecutorService executorService;

    public Controller(ILogRepository programStates) {

        this.programStates = programStates;
        this.executorService = Executors.newFixedThreadPool(2);
    }

    public ILogRepository getRepository() {
        return programStates;
    }

    public void setRepository(ILogRepository programStates) {
        this.programStates = programStates;
    }

    public List<ProgramState> removeCompletedPrograms(List<ProgramState> programStates){
        return programStates.stream().filter(x -> x.isNotCompleted()).collect(Collectors.toList());
    }

    public void executeOneStepForAllThreads(List<ProgramState> programStates) throws IOException, ControllerException {
        List<Callable<ProgramState>> callList = programStates.
                stream().
                map((ProgramState p) -> (Callable<ProgramState>)(() -> {return p.oneStep();})).
                collect(Collectors.toList());

        List<ProgramState> newProgramStates = null;
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
                        return null;
                    })
                    .filter(p -> p != null)
                    .collect(Collectors.toList());
        } catch (InterruptedException e) {
            throw new ControllerException("Thread stuff", e);
        }

        programStates.addAll(newProgramStates);


        //TODO Remove
        for (ProgramState programState : programStates) {
            this.programStates.logProgramStates(programState);
            System.out.println(programState.toString());
        }

        this.programStates.setEntities(programStates);
    }

    @Override
    public String toString() {
        return "Controller{" +
                programStates.getEntities().get(0).getExecutionStack().toString() +
                '}';
    }
}