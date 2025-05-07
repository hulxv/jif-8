package core.instruction.instructions;

import core.instruction.Instruction;

public class ShiftLeftInstruction extends Instruction {
    private final int register;

    public ShiftLeftInstruction(int register) {
        this.register = register;
    }

    @Override
    public void execute() {
        // Implementation will shift register left, bit 7 goes to VF
        System.out.printf("SHL V%X\n", register);
    }

    @Override
    public String toString() {
        return String.format("SHL V%X", register);
    }
}