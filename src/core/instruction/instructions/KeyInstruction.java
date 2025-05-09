package core.instruction.instructions;

import core.instruction.Instruction;

public class KeyInstruction extends Instruction {
    private final int register;

    public KeyInstruction(int register) {
        this.register = register;
    }

    @Override
    public void execute() {
        // Wait for key press and store in Vx
        System.out.printf("KEY V%X, K\n", register);
    }

    @Override
    public String toString() {
        return String.format("KEY V%X, K", register);
    }
}