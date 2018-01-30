package datastructures;

import datastructures.exceptions.EmptyStackException;
import datastructures.exceptions.FullStackException;

public class Stack<E> implements IStack<E> {
    private final int size;

    private int top;

    private E[] elements;

    public Stack() {
        this(10);
    }

    public Stack(int s) {
        size = s > 0 ? s : 10;
        top = -1;

        elements = (E[]) new Object[size]; // create array
    }

    @Override
    public void push(E pushValue) throws FullStackException {
        if (top == size - 1) // if stack is full
            throw new FullStackException(String.format("Stack is full, cannot push element %s", pushValue.toString()));

        elements[++top] = pushValue; // place pushValue on Stack
    }

    @Override
    public E pop() throws EmptyStackException {
        if (top == -1) // if stack is empty
            throw new EmptyStackException("Stack is empty, cannot pop");

        return elements[top--]; // remove and return top element of Stack
    }

    @Override
    public E top() {
        return elements[top];
    }

    @Override
    public boolean isFull() {
        return top == size - 1;
    }

    @Override
    public boolean isEmpty() {
        return top == -1;
    }

    @Override
    public String toString() {

        String resultString = new String();

        for (E elem: this.elements
             ) {
            resultString += elem.toString() + "\n";
        }

        return resultString;
    }
}
