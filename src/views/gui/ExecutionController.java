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
import models.statements.IStatement;
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

    public TableView<Map.Entry<String, Integer>> symbolsTable;
    public TableColumn<Map.Entry<String, Integer>, String> symbolsTableIdColumn;
    public TableColumn<Map.Entry<String, Integer>, Integer> symbolTaleValueColumn;

    public TableView<Map.Entry<Integer, Integer>> heapTable;
    public TableColumn<Map.Entry<Integer, Integer>, Integer> heapTableIdColumn;
    public TableColumn<Map.Entry<Integer, Integer>, Integer> heapTableValueColumn;

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
        ProgramState firstProgramState = this.controller.getRepository().getEntities().get(0);

        symbolTable = (Dictionary) firstProgramState.getSymbolTable();
        executionStack = (Stack2) firstProgramState.getExecutionStack();
        heap = (Heap) firstProgramState.getHeap();
        fileDescriptors = (FileTable) firstProgramState.getFileDescriptors();
        output = firstProgramState.getOutput();

        this.programStatesListView.setItems(FXCollections.observableArrayList(this.controller.getRepository().getEntities()));

        this.fileTableFileIdColumn.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().getKey()).asObject());
        this.fileTableFileNameColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getValue()));

        this.symbolsTableIdColumn.setCellValueFactory(entry -> new SimpleStringProperty(entry.getValue().getKey()));
        this.symbolTaleValueColumn.setCellValueFactory(entry -> new SimpleIntegerProperty(entry.getValue().getValue()).asObject());

        this.heapTableIdColumn.setCellValueFactory(entry -> new SimpleIntegerProperty(entry.getValue().getKey()).asObject());
        this.heapTableValueColumn.setCellValueFactory(entry -> new SimpleIntegerProperty(entry.getValue().getValue()).asObject());

        refreshViews();
    }

    private void refreshViews(){
        List l;

        this.executionStackView.setItems(FXCollections.observableArrayList(executionStack.toString()));
        this.outputListView.setItems(FXCollections.observableArrayList(this.output));
        this.outputListView.refresh();

        this.threadsNumberField.setText("No of programs:" + this.controller.getRepository().getNumberOfEntities());
        this.programStatesListView.refresh();

        l = this.fileDescriptors.entrySet()
                .stream()
                .map(entry -> new AbstractMap.SimpleEntry(entry.getKey(), entry.getValue().getFilename()))
                .collect(Collectors.toList());
        this.fileTable.setItems(FXCollections.observableArrayList(l));
        this.fileTable.refresh();

        l = this.symbolTable.entrySet()
                .stream()
                .map(entry -> new AbstractMap.SimpleEntry(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
        this.symbolsTable.setItems(FXCollections.observableArrayList(l));
        this.symbolsTable.refresh();

        l = this.heap.entrySet()
                .stream()
                .map(entry -> new AbstractMap.SimpleEntry<>(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
        this.heapTable.setItems(FXCollections.observableArrayList(l));
        this.heapTable.refresh();

        this.programStatesListView.refresh();
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

    public void onProgramStateSelected(MouseEvent mouseEvent) {
        ProgramState selected = (ProgramState) this.programStatesListView.getSelectionModel().getSelectedItem();
        this.symbolTable = (Dictionary<String, Integer>) selected.getSymbolTable();
        this.executionStack = (Stack2) selected.getExecutionStack();

        refreshViews();
    }
}
