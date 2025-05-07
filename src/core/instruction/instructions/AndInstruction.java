package core.instruction.instructions;

import core.instruction.Instruction;

public class AndInstruction extends Instruction {
    private final int registerX;
    private final int registerY;

    public AndInstruction(int rx, int ry) {
        this.registerX = rx;
        this.registerY = ry;
    }

    @Override
    public void execute() {
        // Implementation will perform AND operation between registers
        System.out.printf("AND V%X, V%X\n", registerX, registerY);
    }

    @Override
    public String toString() {
        return String.format("AND V%X, V%X", registerX, registerY);
    }
}