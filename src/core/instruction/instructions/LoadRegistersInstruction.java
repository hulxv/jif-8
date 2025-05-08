package core.instruction.instructions;

import core.instruction.Instruction;

public class LoadRegistersInstruction extends Instruction {
    private final int lastRegister;

    public LoadRegistersInstruction(int lastRegister) {
        this.lastRegister = lastRegister;
    }

    @Override
    public void execute() {

        for (int i=0; i<=lastRegister;i++) {
            byte value = cpu.getMemory().read((char)(cpu.getI()+i));
            cpu.getRegisters().setRegister(i, value);
        }

    }

    @Override
    public String toString() {
        return String.format("LOAD V0-V%X", lastRegister);
    }
}