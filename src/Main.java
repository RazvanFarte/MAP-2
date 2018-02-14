import controllers.Controller;
import controllers.exceptions.ControllerException;
import datastructures.Dictionary;
import datastructures.FileTable;
import datastructures.List;
import datastructures.Stack2;
import models.expressions.ConstantExpression;
import models.expressions.VariableExpression;
import models.statements.*;
import repo.IRepository;
import repo.MemoryRepository;

public class Main {

    private static Controller initializeController() {

        //Choosing initial statement
        IStatement originalStatement = new PrintStatement(new ConstantExpression(5));

        IStatement originalStatement2 = new CompoundStatement(
                new AssignStatement("a",
                        new ConstantExpression(4)), new PrintStatement(new VariableExpression("a")));

        //Creating Repository
        IRepository<ProgramState> mRepository = new MemoryRepository<>();

        Stack2<IStatement> executionStack = new Stack2<>();
        executionStack.push(originalStatement2);
        mRepository.add(new ProgramState(executionStack, new Dictionary<>(), new List<>(), new FileTable(), originalStatement2));

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
