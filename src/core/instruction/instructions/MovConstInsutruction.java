package core.instruction.instructions;

import core.instruction.Instruction;

public class MovConstInsutruction extends Instruction {
    private int value;
    private int destination;

    public MovConstInsutruction(int value, int destination) {
        this.value = value;
        this.destination = destination;
    }

    public void execute() {
        // Logic to move a constant value to a destination
        System.out.println("Moving constant " + value + " to destination " + destination);
        // Assuming memory is a field in the CPU class
        // memory[destination] = value;
    }

    @Override
    public String toString() {
        return "MOV " + value + ", " + destination;
    }
}
