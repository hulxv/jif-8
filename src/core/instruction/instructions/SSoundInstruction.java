package core.instruction.instructions;

import core.instruction.Instruction;

public class SSoundInstructionFX18 extends Instruction {
    private final char address;

    public SSoundInstructionFX18(char address) {
        this.address = address;
    }

    @Override
    public void execute() {
        byte value = cpu.getRegisters().getRegister(address);
        cpu.setSoundTimer(value);
    }

    public char getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "SSOUND " + address;
    }

}
