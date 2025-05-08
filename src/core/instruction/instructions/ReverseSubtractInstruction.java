package core.instruction.instructions;

import core.instruction.Instruction;

public class ReverseSubtractInstruction extends Instruction {
    private final int registerX;
    private final int registerY;

    public ReverseSubtractInstruction(int rx, int ry) {
        this.registerX = rx;
        this.registerY = ry;
    }

    @Override
    public void execute() {
        // Implementation will set VX to VY - VX, set VF = NOT borrow
        byte valueX = cpu.getRegisters().getRegister(registerX);
        byte valueY = cpu.getRegisters().getRegister(registerY);

        if (valueY < Byte.MIN_VALUE - valueX)
            cpu.getRegisters().setRegister(15, (byte) 1);
        
        else
            cpu.getRegisters().setRegister(15, (byte) 0);

        cpu.getRegisters().setRegister(registerX, (byte) (valueY - valueX));
    }

    @Override
    public String toString() {
        return String.format("SUBN V%X, V%X", registerX, registerY);
    }
}