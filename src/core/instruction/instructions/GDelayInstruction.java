package core.instruction.instructions;

import core.instruction.Instruction;

public class GDelayInstruction extends Instruction {
    private final int register;

    public GDelayInstruction(int register) {
        this.register = register;
    }

    @Override
    public void execute() {
        cpu.getRegisters().setRegister(register, cpu.getDelayTimer());
    }

    @Override
    public String toString() {
        return String.format("LD V%X, DT", register);
    }
}
