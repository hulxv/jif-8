package core.instruction.instructions;

import core.instruction.Instruction;

public class SkipKeyInstruction extends Instruction {
    private final int register;
    private final boolean skipIfPressed;

    public SkipKeyInstruction(int register, boolean skipIfPressed) {
        this.register = register;
        this.skipIfPressed = skipIfPressed;
    }

    @Override
    public void execute() {
        // Skip next instruction based on key state
        if (skipIfPressed) {
            System.out.printf("SKP V%X\n", register);
        } else {
            System.out.printf("SKNP V%X\n", register);
        }
    }

    @Override
    public String toString() {
        return skipIfPressed ? 
            String.format("SKP V%X", register) :
            String.format("SKNP V%X", register);
    }
}