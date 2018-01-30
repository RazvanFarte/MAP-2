package datastructures;

import datastructures.IStack;
import datastructures.exceptions.EmptyStackException;
import datastructures.exceptions.FullStackException;

public class Stack2<T> implements IStack<T> {

    @Override
    public void push(T element) throws FullStackException {

    }

    @Override
    public T pop() throws EmptyStackException {
        return null;
    }

    @Override
    public T top() {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public String toString() {
        return new String();
    }
}
