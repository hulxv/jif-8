package core.instruction.instructions;

import core.instruction.Instruction;

public class SkipNotEqualRegInstruction extends Instruction {
    private final int registerX;
    private final int registerY;

    public SkipNotEqualRegInstruction(int rx, int ry) {
        this.registerX = rx;
        this.registerY = ry;
    }

    @Override
    public void execute() {
        // Implementation will skip next instruction if VX not equals VY
        System.out.printf("SNE V%X, V%X\n", registerX, registerY);
    }

    @Override
    public String toString() {
        return String.format("SNE V%X, V%X", registerX, registerY);
    }
}