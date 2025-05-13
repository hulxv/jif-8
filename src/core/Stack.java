package core;

public class Stack {
    private char[] stack;
    private char stackPointer = 0;

    public Stack() {
        stack = new char[16];
        for (int i = 0; i < stack.length; i++) {
            stack[i] = 0;
        }
        System.out.println("Stack initialized");
    }

    public void push(char value) {
        if (stackPointer + 1 < 16)
            stack[stackPointer++] = value;
    }

    public char pop() {
        char value = getLast();
        if (stackPointer - 1 >= 0)
            stack[stackPointer] = 0;
        stackPointer--;
        return value;
    }

    public char getLast() {
        return stack[stackPointer];
    }

    public void reset() {
        for (int i = 0; i < stack.length; i++) {
            stack[i] = 0;
        }
    }
}