package core.instruction.instructions;

import core.instruction.Instruction;

public class JsrInstruction2NNN extends Instruction {
    private int address;

    public JsrInstruction2NNN(int address) {
        this.address = address;
    }

    public void execute() {
        // Logic to call a subroutine
        System.out.println("Calling subroutine at address: " + address);
        cpu.getStack().push(cpu.getPC());
        cpu.setPC((char) address);
    }

    @Override
    public String toString() {
        return "JSR " + String.format("%03X", address);
    }
}
