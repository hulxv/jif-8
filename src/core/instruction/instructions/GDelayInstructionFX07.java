package core.instruction.instructions;

import core.instruction.Instruction;

public class GDelayInstructionFX07 extends Instruction {
    private final int register;

    public GDelayInstructionFX07(int register) {
        this.register = register;
    }

    @Override
    public void execute() {
        cpu.getRegisters().setRegister(register, cpu.getDelayTimer());
    }

    @Override
    public String toString() {
        return "GDelay " + delay;
    }
}
