package datastructures;

public interface IStack<T>{
    void push(T element);
    T pop();
    T top();
    boolean isEmpty();
    boolean isFull();
}