package core.instruction.instructions;

import core.instruction.Instruction;

public class SubtractInstruction extends Instruction {
    private final int registerX;
    private final int registerY;

    public SubtractInstruction(int rx, int ry) {
        this.registerX = rx;
        this.registerY = ry;
    }

    @Override
    public void execute() {
        // Implementation will subtract VY from VX, set VF = NOT borrow
        byte valueX = cpu.getRegisters().getRegister(registerX);
        byte valueY = cpu.getRegisters().getRegister(registerY);

        if (valueX < Byte.MIN_VALUE - valueY)
            cpu.getRegisters().setRegister(15, (byte) 1);
        
        else
            cpu.getRegisters().setRegister(15, (byte) 0);

        cpu.getRegisters().setRegister(registerX, (byte) (valueX - valueY));
    }

    @Override
    public String toString() {
        return String.format("SUB V%X, V%X", registerX, registerY);
    }
}