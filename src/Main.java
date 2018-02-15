import controllers.Controller;
import controllers.exceptions.ControllerException;
import datastructures.Dictionary;
import datastructures.FileTable;
import datastructures.List;
import datastructures.Stack2;
import datastructures.exceptions.FullStackException;
import models.expressions.ConstantExpression;
import models.expressions.VariableExpression;
import models.statements.*;
import repo.IRepository;
import repo.MemoryRepository;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    private static IStatement getStatement(int choice) {
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
                throw new RuntimeException("Not a valid choice");
        }
    }

    private static Controller initializeController() {

        //Choosing statement
        IStatement statement = getStatement(3);

        //Creating Repository
        IRepository<ProgramState> mRepository = new MemoryRepository<>();

        Stack2<IStatement> executionStack = new Stack2<>();
        executionStack.push(statement);

        mRepository.add(new ProgramState(executionStack, new Dictionary<>(), new List<>(), new FileTable(), statement));

        return new Controller(mRepository);
    }

    public static void main(String args[]) {

        Controller interpreterController = initializeController();
        try {
            interpreterController.execute();
        } catch (ControllerException e) {
            e.printStackTrace();
        }

    }
}
