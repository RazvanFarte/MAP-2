package datastructures;

import datastructures.exceptions.EmptyStackException;
import datastructures.exceptions.FullStackException;

public interface IStack<T>{
    void push(T element) throws FullStackException;
    T pop() throws EmptyStackException;
    T top();
    boolean isEmpty();
    boolean isFull();

    @Override
    String toString();
}