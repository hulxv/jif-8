package core.instruction.instructions;

import core.instruction.Instruction;

public class AddConstInstruction extends Instruction {
    private final int register;
    private final int value;

    public AddConstInstruction(int register, int value) {
        this.register = register;
        this.value = value;
    }

    @Override
    public void execute() {
        int current = cpu.getRegisters().getRegister(register) & 0xFF;
        int res = (value+current) & 0xFF;

        cpu.getRegisters().setRegister(register,(byte) res);
        cpu.setPC((char) (cpu.getPC()+2));
    }

    @Override
    public String toString() {
        return String.format("ADD V%X, %02X", register, value);
    }
}