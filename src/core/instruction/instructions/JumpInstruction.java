package core.instruction.instructions;

import core.instruction.Instruction;

public class JumpInstruction extends Instruction {
    private final char address;
    
    public JumpInstruction(char address) {
        this.address = address;
    }
    
    @Override
    public void execute() {
        // Implementation for jump operation
    }
    
    public char getAddress() {
        return address;
    }
}