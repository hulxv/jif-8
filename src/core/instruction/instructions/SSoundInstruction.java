package core.instruction.instructions;

import core.instruction.Instruction;

public class SSoundInstruction extends Instruction {
    private final char address;

    public SSoundInstruction(char address) {
        this.address = address;
    }

    @Override
    public void execute() {
        // Implementation for sound operation
        System.out.println("Playing sound at address: " + address);
        // Assuming sound is a field in the CPU class
        // sound.play(address);
    }

    public char getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "SSOUND " + address;
    }

}
