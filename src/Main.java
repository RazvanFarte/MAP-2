import controllers.Controller;
import controllers.exceptions.ControllerException;
import datastructures.Dictionary;
import datastructures.FileTable;
import datastructures.List;
import datastructures.Stack2;
import models.commands.ExitCommand;
import models.commands.RunExampleCommand;
import models.expressions.ConstantExpression;
import models.expressions.VariableExpression;
import models.statements.*;
import repo.ILogRepository;
import repo.LogRepository;
import views.TextMenu;
import views.exceptions.InvalidChoiceException;
import views.exceptions.ViewException;

import javax.xml.soap.Text;
import java.io.IOException;
import java.util.Arrays;

public class Main {

    private static final String logFile = "logFile";

    private static IStatement getStatement(int choice) throws ViewException {
        switch (choice) {
            case 1: //print(5)
                return new PrintStatement(new ConstantExpression(5));

            case 2: // a = 4; print(a);
                return new CompoundStatement(
                                new AssignStatement("a", new ConstantExpression(4)),
                                new PrintStatement(new VariableExpression("a")));

            case 3: /*
                        openRFile(var_f,"test.in");
                        readFile(var_f,var_c);print(var_c);
                        (if var_c then readFile(var_f,var_c);print(var_c)
                        else print(0));
                        closeRFile(var_f)*/
                IStatement openFile = new OpenRFileStatement("var_f", "test.in");
                IStatement readFile = new ReadFile("var_f", "value");
                IStatement printValue = new PrintStatement(new VariableExpression("value"));
                IStatement ifStatement = new IfStatement(new VariableExpression("value"),
                                                        new CompoundStatement(
                                                                new ReadFile("var_f", "value"),
                                                                new PrintStatement(new VariableExpression("value"))),
                                                        new PrintStatement(new ConstantExpression(0)));
                IStatement closeFile = new CloseRFile("var_f");

                return new MultipleStatements(Arrays.asList(closeFile, ifStatement, printValue, readFile, openFile));
            default:
                throw new InvalidChoiceException("Invalid workflow selected. No existent choice.");
        }
    }

    private static Controller initializeController(int choice) throws ViewException {

        //Choosing statement
        IStatement statement = getStatement(choice);

        //Creating Repository
        ILogRepository mRepository = new LogRepository(logFile + choice);

        //Creating the executionStack
        Stack2<IStatement> executionStack = new Stack2<>();
        executionStack.push(statement);

        mRepository.add(new ProgramState(executionStack, new Dictionary<>(), new List<>(), new FileTable(), statement));

        return new Controller(mRepository);
    }

    public static void main(String args[]) {

        Controller ctrl1, ctrl2, ctrl3 = null;
        try {
            ctrl1 = initializeController(1);
            ctrl2 = initializeController(2);
            ctrl3 = initializeController(3);

            TextMenu view = new TextMenu();

            view.addCommand(new ExitCommand("0", "exit"));
            view.addCommand(new RunExampleCommand("1", ctrl1.toString(), ctrl1));
            view.addCommand(new RunExampleCommand("2", ctrl2.toString(), ctrl2));
            view.addCommand(new RunExampleCommand("3", ctrl3.toString(), ctrl3));

            view.show();
        } catch (ViewException e) {
            e.printStackTrace();
        }
    }
}
