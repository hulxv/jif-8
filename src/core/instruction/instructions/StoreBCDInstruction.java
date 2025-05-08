package core.instruction.instructions;

import core.instruction.Instruction;

public class StoreBCDInstruction extends Instruction {
    private final int register;

    public StoreBCDInstruction(int register) {
        this.register = register;
    }

    @Override
    public void execute() {
        byte value = cpu.getRegisters().getRegister(register);
        
        byte hundreds = (byte)(value/100);
        byte tens = (byte)((value/10) % 10);
        byte ones = (byte)(value % 10);

        char index = cpu.getI();

        cpu.getMemory().write(index,(char) hundreds);
        cpu.getMemory().write((char)(index+1),(char) tens);
        cpu.getMemory().write((char)(index+2),(char) ones);
    }

    @Override
    public String toString() {
        return String.format("BCD V%X", register);
    }
}