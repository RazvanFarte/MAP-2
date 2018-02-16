package models.commands;

import controllers.Controller;
import controllers.exceptions.ControllerException;

import java.io.IOException;

public class RunExampleCommand extends AbstractCommand {

    private Controller controller;

    public RunExampleCommand(String key, String description, Controller controller) {
        super(key, description);
        this.controller = controller;
    }

    @Override
    public void execute() {
        try {
            controller.execute();
        } catch (ControllerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
