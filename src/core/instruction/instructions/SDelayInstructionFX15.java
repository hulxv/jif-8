package core.instruction.instructions;

import core.instruction.Instruction;

public class SDelayInstructionFX15 extends Instruction {
    private final int register;

    public SDelayInstructionFX15(int register) {
        this.register = register;
    }

    @Override
    public void execute() {
        byte value = cpu.getRegisters().getRegister(register);
        cpu.setDelayTimer(value);
    }

    @Override
    public String toString() {
        return String.format("SDELAY V%X", register);
    }
}
