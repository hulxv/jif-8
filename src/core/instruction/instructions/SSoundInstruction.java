package core.instruction.instructions;

import core.instruction.Instruction;

public class SSoundInstruction extends Instruction {
    private final char address;

    public SSoundInstruction(char address) {
        this.address = address;
    }

    @Override
    public void execute() {
        System.out.println("Playing sound at address: " + address);
        cpu.getSoundSystem().playSound(address);                       //remake Method play sound or my implementaion is wrong, i really do not know
        cpu.setPC((char) (cpu.getPC()+2));
    }

    public char getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "SSOUND " + address;
    }

}
