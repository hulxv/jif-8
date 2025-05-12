package core.instruction.instructions;

import core.instruction.Instruction;

public class ShrInstruction8X06 extends Instruction {
    private final int register;

    public ShrInstruction8X06(int register) {
        this.register = register;
    }

    @Override
    public void execute() {
        // Implementation will shift register right, bit 0 goes to VF
        byte valueRegister = cpu.getRegisters().getRegister(register);
        cpu.getRegisters().setRegister(15, (byte) (valueRegister & 0b00000001)); // Saving the most right bit to VF
        cpu.getRegisters().setRegister(register, (byte) (valueRegister >> 1));
    }

    @Override
    public String toString() {
        return String.format("SHR V%X", register);
    }
}