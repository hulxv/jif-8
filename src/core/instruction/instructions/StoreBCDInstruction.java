package core.instruction.instructions;

import core.instruction.Instruction;

public class StoreBCDInstruction extends Instruction {
    private final int register;

    public StoreBCDInstruction(int register) {
        this.register = register;
    }

    @Override
    public void execute() {
        // Will store BCD representation of VX.
        // The interpreter takes the decimal value of VX, and places:
        // - hundreds digit at location in I
        // - tens digit at location I+1
        // - ones digit at location I+2
        System.out.printf("BCD V%X\n", register);
    }

    @Override
    public String toString() {
        return String.format("BCD V%X", register);
    }
}