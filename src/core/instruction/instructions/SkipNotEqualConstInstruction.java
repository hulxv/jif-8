package core.instruction.instructions;

import core.instruction.Instruction;

public class SkipNotEqualConstInstruction extends Instruction {
    private final int register;
    private final int value;

    public SkipNotEqualConstInstruction(int register, int value) {
        this.register = register;
        this.value = value;
    }

    @Override
    public void execute() {
        // Implementation will skip next instruction if VX not equals NN
        System.out.printf("SNE V%X, %X\n", register, value);
    }

    @Override
    public String toString() {
        return String.format("SNE V%X, %X", register, value);
    }
}