package core.instruction.instructions;

import core.instruction.Instruction;

public class CallInstruction extends Instruction {
    private final char address;

    public CallInstruction(char address) {
        this.address = address;
    }

    @Override
    public void execute() {
        // Implementation will call subroutine at address
        System.out.printf("CALL %03X\n", (int)address);
    }

    @Override
    public String toString() {
        return String.format("CALL %03X", (int)address);
    }
}