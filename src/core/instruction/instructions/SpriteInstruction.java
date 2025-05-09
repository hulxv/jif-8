package core.instruction.instructions;

import core.instruction.Instruction;

public class SpriteInstruction extends Instruction {
    private final int registerX;
    private final int registerY;
    private final int height;

    public SpriteInstruction(int rx, int ry, int height) {
        this.registerX = rx;
        this.registerY = ry;
        this.height = height;
    }

    @Override
    public void execute() {
        // Implementation will draw sprite at location (VX,VY) with height N
        System.out.printf("SPRITE V%X, V%X, %X\n", registerX, registerY, height);
    }

    @Override
    public String toString() {
        return String.format("SPRITE V%X, V%X, %X", registerX, registerY, height);
    }
}