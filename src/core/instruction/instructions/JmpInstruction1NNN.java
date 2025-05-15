package core.instruction.instructions;

import core.instruction.Instruction;

public class JmpInstruction1NNN extends Instruction {
    private final int address;

    public JmpInstruction1NNN(int address) {
        this.address = address;
    }

    @Override
    public void execute() {
        // Implementation for jump operation
        cpu.setPC(address);
    }

    @Override
    public String toString() {
        return String.format("JMP 0x%03X", address);
    }

}