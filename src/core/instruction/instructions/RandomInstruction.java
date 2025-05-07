package core.instruction.instructions;

import core.instruction.Instruction;

public class RandomInstruction extends Instruction {
    private final int register;
    private final int mask;

    public RandomInstruction(int register, int mask) {
        this.register = register;
        this.mask = mask;
    }

    @Override
    public void execute() {
        // Logic to generate random number & mask will be implemented in CPU
        System.out.printf("RAND V%X, %X\n", register, mask);
    }

    @Override
    public String toString() {
        return String.format("RAND V%X, %X", register, mask);
    }
}