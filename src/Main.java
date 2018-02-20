import controllers.Controller;
import datastructures.Dictionary;
import datastructures.FileTable;
import datastructures.List;
import datastructures.Stack2;
import datastructures.Heap;
import models.commands.ExitCommand;
import models.commands.RunExampleCommand;
import models.expressions.*;
import models.statements.*;
import repo.ILogRepository;
import repo.LogRepository;
import views.TextMenu;
import views.exceptions.InvalidChoiceException;
import views.exceptions.ViewException;

import java.util.ArrayList;
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

            case 6:
                java.util.List<IStatement> list6 = new ArrayList<IStatement>();
                list6.add(new PrintStatement(new ReadHeapExpression("a")));
                list6.add(new PrintStatement(new VariableExpression("a")));
                list6.add(new WriteHeapStatement("a", new ConstantExpression(30)));
                list6.add(new NewStatement("a", new ConstantExpression(22)));
                list6.add(new NewStatement("v", new ConstantExpression(20)));
                list6.add(new AssignStatement("v", new ConstantExpression(10)));

                return new MultipleStatements(list6);

            case 7:
                java.util.List<IStatement> list7 = new ArrayList<IStatement>();
                list7.add(new AssignStatement("a", new ConstantExpression(0)));
                list7.add(new PrintStatement(new ReadHeapExpression("a")));
                list7.add(new PrintStatement(new VariableExpression("a")));
                list7.add(new WriteHeapStatement("a", new ConstantExpression(30)));
                list7.add(new NewStatement("a", new ConstantExpression(22)));
                list7.add(new NewStatement("v", new ConstantExpression(20)));
                list7.add(new AssignStatement("v", new ConstantExpression(10)));

                return new MultipleStatements(list7);

            case 8:
                return new PrintStatement(new ArithmeticExpression(
                        new ConstantExpression(10),
                        new BooleanExpression(
                                new ConstantExpression(2),
                                new ConstantExpression(6),
                                BooleanExpression.BooleanOperator.LT
                        ),
                        '+'
                ));
            case 9:
                return new PrintStatement(new BooleanExpression(
                        new ArithmeticExpression(
                                new ConstantExpression(10),
                                new ConstantExpression(2),
                                '+'
                        ),
                        new ConstantExpression(6),
                        BooleanExpression.BooleanOperator.LT
                ));
            case 10:
                return new MultipleStatements(Arrays.asList(
                        new PrintStatement(new VariableExpression("v")),
                        new WhileStatement(
                                new ArithmeticExpression(
                                        new VariableExpression("v"),
                                        new ConstantExpression(4),
                                        '-'
                                ),
                                new CompoundStatement(
                                        new PrintStatement(
                                                new VariableExpression("v")
                                        ),
                                        new AssignStatement(
                                                "v",
                                                new ArithmeticExpression(
                                                        new VariableExpression("v"),
                                                        new ConstantExpression(1),
                                                        '-'
                                                )
                                        )
                                )
                        ),
                        new AssignStatement("v", new ConstantExpression(6))
                ));

            case 11:
                java.util.List<IStatement> list11 = new ArrayList<IStatement>();
                list11.add(new PrintStatement(new ReadHeapExpression("a")));
                list11.add(new PrintStatement(new VariableExpression("v")));
                list11.add(new ForkStatement(
                        new MultipleStatements(
                                Arrays.asList(
                                        new PrintStatement(new ReadHeapExpression("a")),
                                        new PrintStatement(new VariableExpression("v")),
                                        new AssignStatement("v", new ConstantExpression(32)),
                                        new WriteHeapStatement("a", new ConstantExpression(30))
                                )
                        )
                ));
                list11.add(new NewStatement("a", new ConstantExpression(22)));
                list11.add(new AssignStatement("v", new ConstantExpression(10)));

                return new MultipleStatements(list11);

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

        mRepository.add(new ProgramState(1, executionStack, new Dictionary<>(), new List<>(), new FileTable(), new Heap(), statement));

        return new Controller(mRepository);
    }

    public static void main(String args[]) {

        List<Controller> controllers = new List<>();
        try {
            TextMenu view = new TextMenu();
            view.addCommand(new ExitCommand("0", "exit"));

            for(int i = 1; i <= 11; i++){
                Controller ctrl = initializeController(i);
                view.addCommand(new RunExampleCommand(new Integer(i).toString(), ctrl.toString(), ctrl));
            }

            view.show();
        } catch (ViewException e) {
            e.printStackTrace();
        }
    }
}
