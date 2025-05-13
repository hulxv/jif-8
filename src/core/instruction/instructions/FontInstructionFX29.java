package core.instruction.instructions;

import core.instruction.Instruction;

public class FontInstructionFX29 extends Instruction {
    private final int register;

    public FontInstructionFX29(int register) {
        this.register = register;
    }

    @Override
    public void execute() {
        int digit = cpu.getRegisters().getRegister(register) & 0x0F; // Get lower 4 bits (0–F)
        cpu.setI((char) (digit * 5)); // Set I to font sprite address
    }

    @Override
    public String toString() {
        return String.format("FONT V%X", register);
    }
}