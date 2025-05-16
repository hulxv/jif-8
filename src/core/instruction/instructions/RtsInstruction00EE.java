package core.instruction.instructions;

import core.instruction.Instruction;

public class RtsInstruction00EE extends Instruction {
    public RtsInstruction00EE() {
        // Constructor logic if needed
    }

    public void execute() {
        // Logic to return from a subroutine
        // Assuming stack is a field in the CPU class
        // stack.pop();
        cpu.setPC(cpu.getStack().pop());
        cpu.setDrawFlag(true);
    }

    @Override
    public String toString() {
        return "RTS";
    }
}
