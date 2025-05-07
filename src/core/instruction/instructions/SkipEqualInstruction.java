package core.instruction.instructions;

import core.instruction.Instruction;

public class SkipEqualInstruction extends Instruction {
    private final int register;
    private final int value;

    public SkipEqualInstruction(int register, int value) {
        this.register = register;
        this.value = value;
    }

    @Override
    public void execute() {
        // Implementation will skip next instruction if VX equals constant
        System.out.printf("SE V%X, %02X\n", register, value);
    }

    @Override
    public String toString() {
        return String.format("SE V%X, %02X", register, value);
    }
}