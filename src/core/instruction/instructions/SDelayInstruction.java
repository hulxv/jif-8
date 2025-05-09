package core.instruction.instructions;

import core.instruction.Instruction;

public class SDelayInstruction extends Instruction {
    private final int register;

    public SDelayInstruction(int register) {
        this.register = register;
    }

    @Override
    public void execute() {
        // Implementation will set delay timer to value in VX
        System.out.printf("SDELAY V%X\n", register);
    }

    @Override
    public String toString() {
        return String.format("SDELAY V%X", register);
    }
}