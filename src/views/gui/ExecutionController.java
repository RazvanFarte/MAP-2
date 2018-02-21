package views.gui;

import controllers.Controller;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import repo.LogRepository;

public class ExecutionController {

    public ListView programStatesListView;
    public TextField threadsNumberField;
    private Controller controller;

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }
}
