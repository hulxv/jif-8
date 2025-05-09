package core.instruction.instructions;

import core.instruction.Instruction;

public class SkipIfKeyPressedInstruction extends Instruction {
    private final int register;

    public SkipIfKeyPressedInstruction(int register) {
        this.register = register;
    }

    @Override
    public void execute() {
        // Implementation will skip next instruction if key in register is pressed
        System.out.printf("SKPR V%X\n", register);
    }

    @Override
    public String toString() {
        return String.format("SKPR V%X", register);
    }
}