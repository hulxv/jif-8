package core.instruction.instructions;

import core.instruction.Instruction;

public class MviInstructionANNN extends Instruction {
    private int value;

    public MviInstructionANNN(int value) {
        this.value = value;
    }

    public void execute() {
        // Logic to move immediate value to I
        cpu.setI((char)value);
    }

    @Override
    public String toString() {
        return String.format("MVI %03X", value);
    }
}
