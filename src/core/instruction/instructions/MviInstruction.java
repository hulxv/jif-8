package core.instruction.instructions;

import core.instruction.Instruction;

public class MviInstruction extends Instruction {
    private int register;
    private int value;

    public MviInstruction(int register, int value) {
        this.register = register;
        this.value = value;
    }

    public void execute() {
        // Logic to move immediate value to register
        System.out.println("Moving immediate value " + value + " to register " + register);
        // Assuming registers is a field in the CPU class
        // registers[register] = value;
    }

    @Override
    public String toString() {
        return "MVI " + String.format("%02X", register) + ", " + String.format("%02X", value);
    }
}
