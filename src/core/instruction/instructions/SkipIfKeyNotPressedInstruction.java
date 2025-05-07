package core.instruction.instructions;

import core.instruction.Instruction;

public class SkipIfKeyNotPressedInstruction extends Instruction {
    private final int register;

    public SkipIfKeyNotPressedInstruction(int register) {
        this.register = register;
    }

    @Override
    public void execute() {
        // Implementation will skip next instruction if key in register is not pressed
        System.out.printf("SKUP V%X\n", register);
    }

    @Override
    public String toString() {
        return String.format("SKUP V%X", register);
    }
}