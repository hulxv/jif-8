package core.instruction.instructions;

import core.instruction.Instruction;

public class ShlInstruction extends Instruction {
    private final int register;

    public ShlInstruction(int register) {
        this.register = register;
    }

    @Override
    public void execute() {
        // Implementation will shift register left, bit 7 goes to VF
        byte valueRegister = cpu.getRegisters().getRegister(register);
        cpu.getRegisters().setRegister(15, (byte) (valueRegister & 0b10000000)); // Saving the most left bit to VF
        cpu.getRegisters().setRegister(register, (byte) (valueRegister << 1));
    }

    @Override
    public String toString() {
        return String.format("SHL V%X", register);
    }
}