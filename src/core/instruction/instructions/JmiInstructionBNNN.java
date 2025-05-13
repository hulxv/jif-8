package core.instruction.instructions;

import core.instruction.Instruction;

public class JmiInstructionBNNN extends Instruction {
    private int address;

    public JmiInstructionBNNN(int address) {
        this.address = address;
    }

    public void execute() {
        int v0 = cpu.getRegisters().getRegister(0);
        cpu.setPC((char) (v0 + address));
    }

    @Override
    public String toString() {
        return String.format("JMI %03X", address);
    }
}
