package core.instruction.instructions;

import core.instruction.Instruction;

public class LoadFontInstruction extends Instruction {
    private final int register;

    public LoadFontInstruction(int register) {
        this.register = register;
    }

    @Override
    public void execute() {
        // Implementation will set I to location of sprite for hex digit VX
        System.out.printf("FONT V%X\n", register);
    }

    @Override
    public String toString() {
        return String.format("FONT V%X", register);
    }
}