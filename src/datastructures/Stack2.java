package datastructures;

import datastructures.IStack;
import datastructures.exceptions.EmptyStackException;
import datastructures.exceptions.FullStackException;

import java.util.Stack;

public class Stack2<T> implements IStack<T> {

    private java.util.Stack<T> mStack;

    public Stack2() {
        this.mStack = new Stack<>();
    }

    @Override
    public void push(T element) throws FullStackException {
        mStack.push(element);
        if(false)
            throw new FullStackException("This will never be called. It is only added for class adapter compatibility");
    }

    @Override
    public T pop() throws EmptyStackException {
        if(this.mStack.isEmpty())
            throw new EmptyStackException("Empty stack. Pop operation impossible.");
        return mStack.pop();
    }

    @Override
    public T top() throws EmptyStackException{
        if(this.mStack.isEmpty())
            throw new EmptyStackException("Empty stack. No element on top of stack.");
        return mStack.peek();
    }

    @Override
    public boolean isEmpty() {
        return mStack.isEmpty();
    }

    @Override
    public boolean isFull() {
        return !mStack.isEmpty();
    }

    @Override
    public String toString() {

        String resultString = new String();

        resultString += "{";
        for (T elem: mStack)
            resultString += elem.toString() + " | ";
        resultString += "}\n";

        return resultString;
    }
}
