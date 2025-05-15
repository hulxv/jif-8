package core.instruction.instructions;

import core.instruction.Instruction;

public class BcdInstructionFX33 extends Instruction {
    private final int register;

    public BcdInstructionFX33(int register) {
        this.register = register;
    }

    @Override
    public void execute() {
        byte value = cpu.getRegisters().getRegister(register);

        byte hundreds = (byte) (value / 100);
        byte tens = (byte) ((value / 10) % 10);
        byte ones = (byte) (value % 10);

        int I = cpu.getI();

        cpu.getMemory().write(I, hundreds);
        cpu.getMemory().write(I + 1, tens);
        cpu.getMemory().write(I + 2, ones);
    }

    @Override
    public String toString() {
        return String.format("BCD V%X", register);
    }
}