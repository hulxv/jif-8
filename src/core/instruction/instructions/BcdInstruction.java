package core.instruction.instructions;

import core.instruction.Instruction;

public class BcdInstruction extends Instruction {
    private final int register;

    public BcdInstruction(int register) {
        this.register = register;
    }

    @Override
    public void execute() {
        byte value = cpu.getRegisters().getRegister(register);

        byte hundreds = (byte) (value / 100);
        byte tens = (byte) ((value / 10) % 10);
        byte ones = (byte) (value % 10);

        char I = cpu.getI();

        cpu.getMemory().write(I, (char) hundreds);
        cpu.getMemory().write((char) (I + 1), (char) tens);
        cpu.getMemory().write((char) (I + 2), (char) ones);
    }

    @Override
    public String toString() {
        return String.format("BCD V%X", register);
    }
}