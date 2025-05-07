package core.instruction.instructions;

import core.instruction.Instruction;

public class SetIndexInstruction extends Instruction {
    private final char address;

    public SetIndexInstruction(char address) {
        this.address = address;
    }

    @Override
    public void execute() {
        // Set index register I to nnn
        System.out.printf("LD I, %03X\n", (int)address);
    }

    @Override
    public String toString() {
        return String.format("LD I, %03X", (int)address);
    }
}