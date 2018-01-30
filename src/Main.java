import datastructures.Stack;
import datastructures.exceptions.EmptyStackException;
import datastructures.exceptions.FullStackException;

public class Main {

    public static void main(String args[]) {
        double[] doubleElements = { 1.1, 2.2, 3.3, 4.4, 5.5, 6.6 };
        int[] integerElements = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };

        Stack<Double> doubleStack = new Stack<Double>(5);
        Stack<Integer> integerStack = new Stack<Integer>(10);

        // test Push Double
        try {
            System.out.println("\nPushing elements onto doubleStack");

            for (double element : doubleElements) {
                System.out.printf("%.1f ", element);
                doubleStack.push(element);
            }
        } catch (FullStackException fullStackException) {
            System.err.println();
            fullStackException.printStackTrace();
        }

        // test Pop Double

        try {
            System.out.println("\nPopping elements from doubleStack");
            double popValue;

            while (true) {
                popValue = doubleStack.pop(); // pop from doubleStack
                System.out.printf("%.1f ", popValue);
            }
        } catch (EmptyStackException emptyStackException) {
            System.err.println();
            emptyStackException.printStackTrace();
        }

        // test push method with integer stack
        try {
            System.out.println("\nPushing elements onto integerStack");

            for (int element : integerElements) {
                System.out.printf("%d ", element);
                integerStack.push(element);
            }
        } catch (FullStackException fullStackException) {
            System.err.println();
            fullStackException.printStackTrace();
        }
        // test pop method with integer stack
        try {
            System.out.println("\nPopping elements from integerStack");
            int popValue; // store element removed from stack

            // remove all elements from Stack
            while (true) {
                popValue = integerStack.pop();
                System.out.printf("%d ", popValue);
            }
        } catch (EmptyStackException emptyStackException) {
            System.err.println();
            emptyStackException.printStackTrace();
        }

    }
}
