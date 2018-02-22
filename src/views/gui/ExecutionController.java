package views.gui;

import controllers.Controller;
import controllers.exceptions.ControllerException;
import datastructures.*;
import datastructures.Dictionary;
import javafx.beans.property.*;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableIntegerArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import models.FileDescriptor;
import models.statements.ProgramState;

import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class ExecutionController {

    public ListView programStatesListView;
    public TextField threadsNumberField;
    public ListView executionStackView;
    public ListView outputListView;
    public GridPane window;

    public TableView<Map.Entry<Integer, String>> fileTable;
    public TableColumn<Map.Entry<Integer, String>, Integer> fileTableFileIdColumn;
    public TableColumn<Map.Entry<Integer, String>, String> fileTableFileNameColumn;
    public Tab symbolsTable;
    public TableColumn symbolsTableIdColumn;
    public TableColumn symbolTaleValueColumn;

    private Controller controller;
    private Dictionary<String, Integer> symbolTable;
    private Stack2 executionStack;
    private Heap heap;
    private IList<Integer> output;
    private FileTable fileDescriptors;

    private List<ProgramState> programStates;

    public ExecutionController() {
    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
        init();

        this.programStates = this.controller.removeCompletedPrograms(this.controller.getRepository().getEntities());
    }

    public void init() {
        this.threadsNumberField.setText("No of programs:" + this.controller.getRepository().getNumberOfEntities());
        this.programStatesListView.setItems(FXCollections.observableArrayList(this.controller.getRepository().getEntities()));

        ProgramState firstProgramState = this.controller.getRepository().getEntities().get(0);

        symbolTable = (Dictionary) firstProgramState.getSymbolTable();
        executionStack = (Stack2) firstProgramState.getExecutionStack();
        heap = (Heap) firstProgramState.getHeap();
        fileDescriptors = (FileTable) firstProgramState.getFileDescriptors();
        output = firstProgramState.getOutput();

        this.fileTableFileIdColumn.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().getKey()).asObject());
        this.fileTableFileNameColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getValue()));

        refreshViews();
    }

    private void refreshViews(){
        System.out.println(executionStack.toString());

        this.executionStackView.setItems(FXCollections.observableArrayList(executionStack.toString()));

        this.outputListView.setItems(FXCollections.observableArrayList(this.output));


        List l = this.fileDescriptors.entrySet()
                .stream()
                .map(entry -> new AbstractMap.SimpleEntry(entry.getKey(), entry.getValue().getFilename()))
                .collect(Collectors.toList());

        this.fileTable.setItems(FXCollections.observableArrayList(l));
        this.fileTable.refresh();

        

    }


    public void executeStep(MouseEvent mouseEvent) {
        try {
            //Logging file
            for (ProgramState programState : programStates) {
                this.controller.getRepository().logProgramStates(programState);
                System.out.println(programState.toString());
            }

            this.controller.executeOneStepForAllThreads(this.programStates);
            this.programStates = this.controller.removeCompletedPrograms(this.controller.getRepository().getEntities());
            refreshViews();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ControllerException e) {
            e.printStackTrace();
        }

    }
}
