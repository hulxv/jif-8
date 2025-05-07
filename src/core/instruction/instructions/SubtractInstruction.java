package core.instruction.instructions;

import core.instruction.Instruction;

public class SubtractInstruction extends Instruction {
    private final int registerX;
    private final int registerY;

    public SubtractInstruction(int rx, int ry) {
        this.registerX = rx;
        this.registerY = ry;
    }

    @Override
    public void execute() {
        // Implementation will subtract VY from VX, set VF = NOT borrow
        System.out.printf("SUB V%X, V%X\n", registerX, registerY);
    }

    @Override
    public String toString() {
        return String.format("SUB V%X, V%X", registerX, registerY);
    }
}