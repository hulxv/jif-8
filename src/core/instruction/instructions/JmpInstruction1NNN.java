package core.instruction.instructions;

import core.instruction.Instruction;

public class JmpInstruction1NNN extends Instruction {
    private final char address;
    
    public JmpInstruction1NNN(char address) {
        this.address = address;
    }
    
    @Override
    public void execute() {
        // Implementation for jump operation
        cpu.setPC(address);
    }
    
    public char getAddress() {
        return address;
    }
}