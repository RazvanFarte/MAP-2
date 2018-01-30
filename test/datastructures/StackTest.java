package datastructures;

import datastructures.exceptions.EmptyStackException;
import datastructures.exceptions.FullStackException;
import org.junit.Assert;
import org.junit.Test;

public class StackTest {

    @Test
    public void testStack() {
        double[] doubleElements = { 1.1, 2.2, 3.3, 4.4, 5.5, 6.6 };
        int[] integerElements = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };

        Stack<Double> doubleStack = new Stack<Double>(5);
        Stack<Integer> integerStack = new Stack<Integer>(10);

        // test Push Double
        try {

            for (double element : doubleElements) {
                System.out.printf("%.1f ", element);
                doubleStack.push(element);
            }
        } catch (FullStackException fullStackException) {
            Assert.assertTrue(false);
        }

        // test Pop Double

        try {
            double popValue;

            while (true) {
                popValue = doubleStack.pop(); // pop from doubleStack
            }
        } catch (EmptyStackException emptyStackException) {
            Assert.assertTrue(false);
        }

        // test push method with integer stack
        try {

            for (int element : integerElements) {
                integerStack.push(element);
            }
        } catch (FullStackException fullStackException) {
            Assert.assertTrue(false);
        }
        // test pop method with integer stack
        try {
            int popValue; // store element removed from stack

            // remove all elements from Stack
            while (true) {
                popValue = integerStack.pop();
            }
        } catch (EmptyStackException emptyStackException) {
            Assert.assertTrue(false);
        }

    }
}
