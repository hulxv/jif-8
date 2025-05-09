package core.instruction.instructions;

import core.instruction.Instruction;

public class SkeqInstruction extends Instruction {
    private final int rX;
    private final int rY;

    public SkeqInstruction(int rx, int ry) {
        this.rX = rx;
        this.rY = ry;
    }

    @Override
    public void execute() {
        // Implementation will skip next instruction if VX not equals VY
        byte valueX = cpu.getRegisters().getRegister(rX);
        byte valueY = cpu.getRegisters().getRegister(rY);
        char PC = cpu.getPC();
        char PCNewValue = (char) (PC + 2);

        if (valueX == valueY)
            cpu.setPC(PCNewValue);
    }

    @Override
    public String toString() {
        return String.format("SKNE V%X, V%X", rX, rY);
    }
}