package datastructures;

import datastructures.exceptions.EmptyStackException;
import datastructures.exceptions.FullStackException;

public interface IStack<T> {
    void push(T element);
    T pop() throws EmptyStackException;
    T top() throws EmptyStackException;
    boolean isEmpty();
    boolean isFull();

    @Override
    String toString();
}