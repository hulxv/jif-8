package core.instruction.instructions;

import core.instruction.Instruction;

<<<<<<< HEAD:src/core/instruction/instructions/LoadFontInstruction.java
public class LoadFontInstruction extends Instruction {
    private final int register; // Register Index
=======
public class FontInstruction extends Instruction {
    private final int register;
>>>>>>> d780447445250ad4c3c508551f8ee6f29b79c3a3:src/core/instruction/instructions/FontInstruction.java

    public FontInstruction(int register) {
        this.register = register;
    }

    @Override
    public void execute() {
        int digit = cpu.getRegisters().getRegister(register) & 0x0F; // Get lower 4 bits (0–F)
        cpu.setI((char) (0x50 + (digit * 5))); // Set I to font sprite address
    }

    @Override
    public String toString() {
        return String.format("FONT V%X", register);
    }
}