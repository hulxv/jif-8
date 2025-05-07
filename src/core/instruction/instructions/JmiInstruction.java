package core.instruction.instructions;

import core.instruction.Instruction;

public class JmiInstruction extends Instruction{
    private int address;

    public JmiInstruction(int address) {
        this.address = address;
    }

    public void execute() {
        // Logic to jump to a memory address
        System.out.println("Jumping to memory address: " + address);
        // Assuming stack is a field in the CPU class
        // stack.push(address);
    }

    @Override
    public String toString() {
        return "JMI " + String.format("%03X", address);
    }
}
