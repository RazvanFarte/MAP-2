package views;

import controllers.Controller;

public class ConsoleView implements IView {

    public Controller mController;

    public ConsoleView(Controller mController) {

        this.mController = mController;
    }

    public Controller getmController() {
        return mController;
    }

    public void setmController(Controller mController) {
        this.mController = mController;
    }

    @Override
    public void run() {

    }
}
