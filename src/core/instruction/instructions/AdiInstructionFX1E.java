package core.instruction.instructions;

import core.instruction.Instruction;

public class AdiInstructionFX1E extends Instruction {
    private final int register;

    public AdiInstructionFX1E(int register) {
        this.register = register;
    }

    @Override
    public void execute() {
        // Implementation will add value in VX to index register I
        System.out.printf("ADI V%X\n", register);
    }

    @Override
    public String toString() {
        return String.format("ADI V%X", register);
    }
}