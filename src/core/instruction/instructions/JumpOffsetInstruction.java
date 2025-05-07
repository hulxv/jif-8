package core.instruction.instructions;

import core.instruction.Instruction;

public class JumpOffsetInstruction extends Instruction {
    private final char address;

    public JumpOffsetInstruction(char address) {
        this.address = address;
    }

    @Override
    public void execute() {
        // Jump to location nnn + V0
        System.out.printf("JP V0, %03X\n", (int)address);
    }

    @Override
    public String toString() {
        return String.format("JP V0, %03X", (int)address);
    }
}