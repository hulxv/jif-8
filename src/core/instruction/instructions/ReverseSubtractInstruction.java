package core.instruction.instructions;

import core.instruction.Instruction;

public class ReverseSubtractInstruction extends Instruction {
    private final int registerX;
    private final int registerY;

    public ReverseSubtractInstruction(int rx, int ry) {
        this.registerX = rx;
        this.registerY = ry;
    }

    @Override
    public void execute() {
        // Implementation will set VX to VY - VX, set VF = NOT borrow
        System.out.printf("SUBN V%X, V%X\n", registerX, registerY);
    }

    @Override
    public String toString() {
        return String.format("SUBN V%X, V%X", registerX, registerY);
    }
}