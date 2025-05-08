package core.instruction.instructions;

import core.instruction.Instruction;

public class AddRegisterConstInstruction extends Instruction {
    private final int register;
    private final int value;

    public AddRegisterConstInstruction(int register, int value) {
        this.register = register;
        this.value = value;
    }

    @Override
    public void execute() {
        // Add value to register Vx
        System.out.printf("ADD V%X, %02X\n", register, value);
    }

    @Override
    public String toString() {
        return String.format("ADD V%X, %02X", register, value);
    }
}