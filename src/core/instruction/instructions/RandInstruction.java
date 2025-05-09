package core.instruction.instructions;

import core.instruction.Instruction;

public class RandInstruction extends Instruction {
    private final int register; // The Vx register index (0 to 15)
    private final int mask; // NN

    public RandInstruction(int register, int mask) {
        this.register = register;
        this.mask = mask;
    }

    @Override
    public void execute() {
        byte randomByte = (byte) cpu.generateRandomByte(); // From 0 to 255
        byte result = (byte) (randomByte & mask); // VX = rand() & NN
        cpu.getRegisters().setRegister(register, result); // Store the result in register VX
    }

    @Override
    public String toString() {
        return String.format("RAND V%X, %X", register, mask);
    }
}