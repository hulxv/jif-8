package core.instruction.instructions;

import core.instruction.Instruction;

public class StrInstruction extends Instruction {
    private final int lastRegister;

    public StrInstruction(int lastRegister) {
        this.lastRegister = lastRegister;
    }

    @Override
    public void execute() {
        for (int i = 0; i <= lastRegister; i++) {
            byte value = cpu.getRegisters().getRegister(i);
            cpu.getMemory().write((char)(cpu.getI()+i),(char)value);
        }
    }

    @Override
    public String toString() {
        return String.format("STR V0-V%X", lastRegister);
    }
}
