package core.instruction.instructions;

import core.instruction.Instruction;

public class OrInstruction extends Instruction {
    private final int registerX;
    private final int registerY;

    public OrInstruction(int rx, int ry) {
        this.registerX = rx;
        this.registerY = ry;
    }

    @Override
    public void execute() {
        // Implementation will perform OR operation between registers
        System.out.printf("OR V%X, V%X\n", registerX, registerY);
    }

    @Override
    public String toString() {
        return String.format("OR V%X, V%X", registerX, registerY);
    }
}