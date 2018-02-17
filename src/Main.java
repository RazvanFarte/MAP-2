import controllers.Controller;
import controllers.exceptions.ControllerException;
import datastructures.Dictionary;
import datastructures.FileTable;
import datastructures.List;
import datastructures.Stack2;
import datastructures.exceptions.Heap;
import models.commands.ExitCommand;
import models.commands.RunExampleCommand;
import models.expressions.ArithmeticExpression;
import models.expressions.ConstantExpression;
import models.expressions.ReadHeapExpression;
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
            case 4: //v=10;new(v,20);new(a,22);print(v)
                IStatement print = new PrintStatement(new VariableExpression("v"));
                IStatement newA = new NewStatement("a", new ConstantExpression(22));
                IStatement newV = new NewStatement("v", new ConstantExpression(20));
                IStatement assign = new AssignStatement("v", new ConstantExpression(10));

                return new MultipleStatements(Arrays.asList(print, newA, newV, assign));

            case 5: //v=10;new(v,20);new(a,22);print(v)
                IStatement print5 = new PrintStatement(new ArithmeticExpression(new ReadHeapExpression("v")
                        , new ConstantExpression(100)
                        , '+'));
                IStatement print25 = new PrintStatement(new ArithmeticExpression(new ReadHeapExpression("a")
                        , new ConstantExpression(100)
                        , '+'));
                IStatement newA5 = new NewStatement("a", new ConstantExpression(22));
                IStatement newV5 = new NewStatement("v", new ConstantExpression(20));
                IStatement assign5 = new AssignStatement("v", new ConstantExpression(10));

                return new MultipleStatements(Arrays.asList(print5, print25, newA5, newV5, assign5));
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

        mRepository.add(new ProgramState(executionStack, new Dictionary<>(), new List<>(), new FileTable(), new Heap(), statement));

        return new Controller(mRepository);
    }

    public static void main(String args[]) {

        List<Controller> controllers = new List<>();
        try {
            TextMenu view = new TextMenu();
            view.addCommand(new ExitCommand("0", "exit"));

            for(int i = 1; i <= 5; i++){
                Controller ctrl = initializeController(i);
                view.addCommand(new RunExampleCommand(new Integer(i).toString(), ctrl.toString(), ctrl));
            }

            view.show();
        } catch (ViewException e) {
            e.printStackTrace();
        }
    }
}
