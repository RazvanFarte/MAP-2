package views.gui;

import controllers.Controller;
import controllers.exceptions.ControllerException;
import datastructures.*;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ReadOnlyListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableIntegerArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import models.statements.ProgramState;

import java.io.IOException;
import java.util.List;

public class ExecutionController {

    public ListView programStatesListView;
    public TextField threadsNumberField;
    public ListView executionStackView;
    public ListView outputListView;
    public TableView<FileTable> fileTable;
    public GridPane window;

    private Controller controller;
    private Dictionary<String, Integer> symbolTable;
    private Stack2 executionStack;
    private Heap heap;
    private FileTable fileDescriptors;


    public ExecutionController() {
    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
        init();
    }

    public void init() {
        this.threadsNumberField.setText("Number of active programs:" + this.controller.getRepository().getNumberOfEntities());
        this.programStatesListView.setItems(FXCollections.observableArrayList(this.controller.getRepository().getEntities()));
        this.outputListView.setItems(FXCollections.observableArrayList(this.controller.getRepository().getEntities()));

        ProgramState firstProgramState = this.controller.getRepository().getEntities().get(0);

        symbolTable = (Dictionary) firstProgramState.getSymbolTable();
        executionStack = (Stack2) firstProgramState.getExecutionStack();
        heap = (Heap) firstProgramState.getHeap();
        fileDescriptors = (FileTable) firstProgramState.getFileDescriptors();

        refreshViews();
    }

    @FXML
    public void initialize() {

    }

    private void refreshViews(){
        this.executionStackView.setItems(FXCollections.observableArrayList(executionStack.toString()));
    }



    public void executeStep(MouseEvent mouseEvent) {

        try {
            this.controller.executeOneStepForAllThreads(this.controller.getRepository().getEntities());
            refreshViews();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ControllerException e) {
            e.printStackTrace();
        }

    }
}
