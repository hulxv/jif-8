package core.instruction.instructions;

import core.instruction.Instruction;

public class LdrInstructionFX65 extends Instruction {
    private final int lastRegister;

    public LdrInstructionFX65(int lastRegister) {
        this.lastRegister = lastRegister;
    }

    @Override
    public void execute() {
        for (int i = 0; i <= lastRegister; i++) {
            byte value = cpu.getMemory().read((char) (cpu.getI() + i));
            cpu.getRegisters().setRegister(i, value);
        }
    }

    @Override
    public String toString() {
        return String.format("LDR V0-V%X", lastRegister);
    }
}