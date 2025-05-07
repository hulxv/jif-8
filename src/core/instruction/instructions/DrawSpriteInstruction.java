package core.instruction.instructions;

import core.instruction.Instruction;

public class DrawSpriteInstruction extends Instruction {
    private final int registerX;
    private final int registerY;
    private final int height;

    public DrawSpriteInstruction(int rx, int ry, int height) {
        this.registerX = rx;
        this.registerY = ry;
        this.height = height;
    }

    @Override
    public void execute() {
        // Implementation will draw sprite at location (VX,VY) with height N
        System.out.printf("DRAW V%X, V%X, %X\n", registerX, registerY, height);
    }

    @Override
    public String toString() {
        return String.format("DRAW V%X, V%X, %X", registerX, registerY, height);
    }
}