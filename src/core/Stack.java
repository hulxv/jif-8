package core;


public class Stack {
    private short[] stack;
    private short stackPointer = 0;

    public Stack() {
        stack = new short[16]; 
        for (int i = 0; i < stack.length; i++) {
            stack[i] = 0;
        }
        System.out.println("Stack initialized");
    }
    public void push(short value) {
        if(stackPointer + 1 < 16)
            stack[stackPointer++] = value;
    }

    public void pop() {
        if(stackPointer - 1 >= 0)
            stack[--stackPointer] = 0;
    }

    public void reset() {
        for (int i = 0; i < stack.length; i++) {
            stack[i] = 0;
        }
    }
}