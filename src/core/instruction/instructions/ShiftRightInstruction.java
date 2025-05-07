package core.instruction.instructions;

import core.instruction.Instruction;

public class ShiftRightInstruction extends Instruction {
    private final int register;

    public ShiftRightInstruction(int register) {
        this.register = register;
    }

    @Override
    public void execute() {
        // Implementation will shift register right, bit 0 goes to VF
        System.out.printf("SHR V%X\n", register);
    }

    @Override
    public String toString() {
        return String.format("SHR V%X", register);
    }
}