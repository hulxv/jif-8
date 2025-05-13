package core.instruction.instructions;

import core.instruction.Instruction;

public class AddConstInstruction7XNN extends Instruction {
    private final int register;
    private final int value;

    public AddConstInstruction7XNN(int register, int value) {
        this.register = register;
        this.value = value;
    }

    @Override
    public void execute() {
        int current = cpu.getRegisters().getRegister(register) & 0xFF;
        int res = (value+current) & 0xFF;
        cpu.getRegisters().setRegister(register,(byte) res);
    }

    @Override
    public String toString() {
        return String.format("ADD V%X, %02X", register, value);
    }
}
