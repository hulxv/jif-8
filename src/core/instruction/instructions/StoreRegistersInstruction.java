package core.instruction.instructions;

import core.instruction.Instruction;

public class StoreRegistersInstruction extends Instruction {
    private final int lastRegister;

    public StoreRegistersInstruction(int lastRegister) {
        this.lastRegister = lastRegister;
    }

    @Override
    public void execute() {
        for (int i = 0; i <= lastRegister; i++) {
            byte value = cpu.getRegisters().getRegister(i);
            cpu.getMemory().write(cpu.getI(), (char) value);
        }
    }

    @Override
    public String toString() {
        return String.format("STORE V0-V%X", lastRegister);
    }
}