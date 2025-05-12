package core.instruction.instructions;

import core.instruction.Instruction;

public class MovConstInstruction extends Instruction {
    private final int register;
    private final int value;

    public MovConstInstruction(int register, int value) {
        this.register = register;
        this.value = value;
    }

    @Override
    public void execute() {
        cpu.getRegisters().setRegister(register,(byte) value);
    }

    @Override
    public String toString() {
        return String.format("LD V%X, %02X", register, value);
    }
}