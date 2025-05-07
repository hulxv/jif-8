package core.instruction.instructions;

import core.instruction.Instruction;

public class WaitKeyInstruction extends Instruction {
    private final int register;

    public WaitKeyInstruction(int register) {
        this.register = register;
    }

    @Override
    public void execute() {
        // Wait for key press and store in Vx
        System.out.printf("LD V%X, K\n", register);
    }

    @Override
    public String toString() {
        return String.format("LD V%X, K", register);
    }
}