package core.instruction.instructions;

import core.instruction.Instruction;

public class XorInstruction8XY3 extends Instruction {
    private final int registerX;
    private final int registerY;

    public XorInstruction8XY3(int rx, int ry) {
        this.registerX = rx;
        this.registerY = ry;
    }

    @Override
    public void execute() {
        // Implementation will perform XOR operation between registers
        byte valueX = cpu.getRegisters().getRegister(registerX);
        byte valueY = cpu.getRegisters().getRegister(registerY);
        cpu.getRegisters().setRegister(registerX, (byte) (valueX ^ valueY));
    }

    @Override
    public String toString() {
        return String.format("XOR V%X, V%X", registerX, registerY);
    }
}