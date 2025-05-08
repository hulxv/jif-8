package core.instruction.instructions;

import core.instruction.Instruction;

public class AdiInstruction extends Instruction {
    private final char address;

    public AdiInstruction(char address) {
        this.address = address;
    }

    @Override
    public void execute() {
        // Implementation for the ADI instruction
        System.out.println("Executing ADI instruction with address: " + address);
        // Assuming CPU has a method to add immediate value to the accumulator
        // cpu.addImmediate(address);
    }

    public char getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "ADI " + address;
    }

}
