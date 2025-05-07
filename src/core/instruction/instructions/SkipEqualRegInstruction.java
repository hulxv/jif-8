package core.instruction.instructions;

import core.instruction.Instruction;

public class SkipEqualRegInstruction extends Instruction {
    private final int registerX;
    private final int registerY;

    public SkipEqualRegInstruction(int rx, int ry) {
        this.registerX = rx;
        this.registerY = ry;
    }

    @Override
    public void execute() {
        // Implementation will skip next instruction if VX equals VY
        System.out.printf("SE V%X, V%X\n", registerX, registerY);
    }

    @Override
    public String toString() {
        return String.format("SE V%X, V%X", registerX, registerY);
    }
}