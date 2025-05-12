package core.instruction.instructions;

import core.instruction.Instruction;

public class MviInstruction extends Instruction {
    private int value;

    public MviInstruction(int value) {
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
