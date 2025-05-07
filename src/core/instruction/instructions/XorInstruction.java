package core.instruction.instructions;

import core.instruction.Instruction;

public class XorInstruction extends Instruction {
    private final int registerX;
    private final int registerY;

    public XorInstruction(int rx, int ry) {
        this.registerX = rx;
        this.registerY = ry;
    }

    @Override
    public void execute() {
        // Implementation will perform XOR operation between registers
        System.out.printf("XOR V%X, V%X\n", registerX, registerY);
    }

    @Override
    public String toString() {
        return String.format("XOR V%X, V%X", registerX, registerY);
    }
}