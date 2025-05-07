package core.instruction.instructions;

import core.instruction.Instruction;

public class JsrInstruction extends Instruction {
    private int address;

    public JsrInstruction(int address) {
        this.address = address;
    }

    public void execute() {
        // Logic to call a subroutine
        System.out.println("Calling subroutine at address: " + address);
        // Assuming stack is a field in the CPU class
        // stack.push(address);
    }

    @Override
    public String toString() {
        return "JSR " + String.format("%03X", address);
    }
}
