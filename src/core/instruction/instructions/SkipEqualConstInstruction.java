package core.instruction.instructions;

import core.instruction.Instruction;

public class SkipEqualConstInstruction extends Instruction {
    private final int register;
    private final int value;

    public SkipEqualConstInstruction(int register, int value) {
        this.register = register;
        this.value = value;
    }

    @Override
    public void execute() {
        // Implementation will skip next instruction if VX equals NN
        System.out.printf("SE V%X, %X\n", register, value);
    }

    @Override
    public String toString() {
        return String.format("SE V%X, %X", register, value);
    }
}