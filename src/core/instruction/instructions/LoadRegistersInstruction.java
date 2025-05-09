package core.instruction.instructions;

import core.instruction.Instruction;

public class LoadRegistersInstruction extends Instruction {
    private final int lastRegister;

    public LoadRegistersInstruction(int lastRegister) {
        this.lastRegister = lastRegister;
    }

    @Override
    public void execute() {
        // Implementation will load registers V0 through VX from memory location I
        System.out.printf("LOAD V0-V%X\n", lastRegister);
    }

    @Override
    public String toString() {
        return String.format("LOAD V0-V%X", lastRegister);
    }
}