package core;

public class Stack {
    public static final int STACK_SIZE = 16;
    private int[] stack;
    private int stackPointer;

    public Stack() {
        stack = new int[STACK_SIZE];
        reset();
    }

    public void push(int value) {
        if (stackPointer >= STACK_SIZE) {
            throw new RuntimeException("CHIP-8 stack overflow");
        }
        stack[stackPointer++] = value;
    }

    public int pop() {
        if (stackPointer <= 0) {
            throw new RuntimeException("CHIP-8 stack underflow");
        }
        return stack[--stackPointer];
    }

    public void reset() {
        stackPointer = 0;
        for (int i = 0; i < stack.length; i++) {
            stack[i] = 0;
        }
    }

    public int getStackPointer() {
        return stackPointer;
    }

    public int get(int index) {
        return stack[index];
    }
}