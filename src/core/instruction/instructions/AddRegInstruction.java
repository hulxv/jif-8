package core.instruction.instructions;

import core.instruction.Instruction;

public class AddRegInstruction extends Instruction {
    private final int registerX;
    private final int registerY;

    public AddRegInstruction(int rx, int ry) {
        this.registerX = rx;
        this.registerY = ry;
    }

    @Override
    public void execute() {
        // Implementation will add VY to VX, set VF = carry
        System.out.printf("ADD V%X, V%X\n", registerX, registerY);
    }

    @Override
    public String toString() {
        return String.format("ADD V%X, V%X", registerX, registerY);
    }
}