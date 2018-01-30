package datastructures;

import datastructures.exceptions.EmptyStackException;
import datastructures.exceptions.FullStackException;
import org.junit.Assert;
import org.junit.Test;

public class StackTest {

    private void testImplementation(IStack<Double> doubleStack, IStack<Integer> integerStack) {

        double[] doubleElements = { 1.1, 2.2, 3.3, 4.4, 5.5, 6.6 };
        int[] integerElements = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };

        // test Push Double
        try {

            for (double element : doubleElements) {
                doubleStack.push(element);
            }
        } catch (FullStackException fullStackException) {
        }

        Assert.assertTrue(doubleStack.top() == 5.5);

        // test Pop Double

        try {
            double popValue;

            while (true) {
                popValue = doubleStack.pop(); // pop from doubleStack
            }
        } catch (EmptyStackException emptyStackException) {
        }

        Assert.assertTrue(doubleStack.isEmpty() == true);

        // test push method with integer stack
        try {

            for (int element : integerElements) {
                integerStack.push(element);
            }
        } catch (FullStackException fullStackException) {
        }

        Assert.assertTrue(integerStack.isFull() == true);
        Assert.assertTrue(integerStack.top() == 10);


        // test pop method with integer stack
        try {
            int popValue; // store element removed from stack

            // remove all elements from Stack
            while (true) {
                popValue = integerStack.pop();
            }
        } catch (EmptyStackException emptyStackException) {
        }

        Assert.assertTrue(integerStack.isEmpty() == true);

    }

    @Test
    public void testStack() {

        testImplementation(new Stack<Double>(5), new Stack<Integer>(10));
        //testImplementation(new Stack2<Double>(5), new Stack2<Integer>(10));
    }
}
