package core.instruction.instructions;

import core.instruction.Instruction;

public class SkeqInstruction5XY0 extends Instruction {
    private final int rX;
    private final int rY;

    public SkeqInstruction5XY0(int rx, int ry) {
        this.rX = rx;
        this.rY = ry;
    }

    @Override
    public void execute() {
        // Implementation will skip next instruction if VX not equals VY
        byte valueX = cpu.getRegisters().getRegister(rX);
        byte valueY = cpu.getRegisters().getRegister(rY);
        int PC = cpu.getPC();

        if (valueX == valueY)
            cpu.setPC(PC + 2);
    }

    @Override
    public String toString() {
        return String.format("SKNE V%X, V%X", rX, rY);
    }
}