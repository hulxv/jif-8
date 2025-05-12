package core.instruction.instructions;

import core.instruction.Instruction;

public class MovConstInstruction6XNN extends Instruction {
    private final int register;
    private final int value;

    public MovConstInstruction6XNN(int register, int value) {
        this.register = register;
        this.value = value;
    }

    @Override
    public void execute() {
        // Set Vx = kk
        System.out.printf("LD V%X, %02X\n", register, value);
    }

    @Override
    public String toString() {
        return String.format("LD V%X, %02X", register, value);
    }
}