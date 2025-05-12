package core.instruction.instructions;

import core.instruction.Instruction;

public class SDelayInstruction extends Instruction {
    private final int register;

    public SDelayInstruction(int register) {
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