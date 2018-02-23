package models.statements;

import models.expressions.ConstantExpression;
import models.expressions.Expression;
import models.expressions.exceptions.DivisionByZeroException;
import models.expressions.exceptions.NotDefinedException;
import models.expressions.exceptions.UnknownOperatorException;
import models.statements.exceptions.StatementException;

public class ConditionalAssignmentStatement implements IStatement {

    private String variableName;
    private Expression expression1;
    private Expression expression2;
    private Expression expression3;

    public ConditionalAssignmentStatement(String variableName, Expression expression1, Expression expression2, Expression expression3) {
        this.variableName = variableName;
        this.expression1 = expression1;
        this.expression2 = expression2;
        this.expression3 = expression3;
    }

    public String getVariableName() {
        return variableName;
    }

    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }

    public Expression getExpression1() {
        return expression1;
    }

    public void setExpression1(Expression expression1) {
        this.expression1 = expression1;
    }

    public Expression getExpression2() {
        return expression2;
    }

    public void setExpression2(Expression expression2) {
        this.expression2 = expression2;
    }

    public Expression getExpression3() {
        return expression3;
    }

    public void setExpression3(Expression expression3) {
        this.expression3 = expression3;
    }

    @Override
    public String toString() {
        return "ConditionalAssignmentStatement{" +
                "variableName='" + variableName + '\'' +
                ", expression1=" + expression1 +
                ", expression2=" + expression2 +
                ", expression3=" + expression3 +
                '}';
    }

    @Override
    public ProgramState execute(ProgramState programState) throws StatementException {

        int e1,e2,e3;
        try {
            e1 = this.expression1.evaluate(programState.getSymbolTable(), programState.getHeap());
            if(e1 != 0)
                programState.getExecutionStack().push(new AssignStatement(this.variableName, new ConstantExpression(this.expression2.evaluate(programState.getSymbolTable(), programState.getHeap()))));
            else
                programState.getExecutionStack().push(new AssignStatement(this.variableName, new ConstantExpression(this.expression3.evaluate(programState.getSymbolTable(), programState.getHeap()))));

        } catch (DivisionByZeroException | UnknownOperatorException | NotDefinedException e) {
            throw new StatementException("Cannot execute conditional assignment", e);
        }
        return null;
    }
}
