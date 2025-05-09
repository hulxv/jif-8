package core.instruction.instructions;

import core.instruction.Instruction;

public class AddInstruction extends Instruction {
    private final int registerX;
    private final int registerY;

    public AddInstruction(int rx, int ry) {
        this.registerX = rx;
        this.registerY = ry;
    }

    @Override
    public void execute() {
        // Implementation will add VY to VX, set VF = carry
        byte valueX = cpu.getRegisters().getRegister(registerX);
        byte valueY = cpu.getRegisters().getRegister(registerY);

        if (valueX > Byte.MAX_VALUE - valueY)
            cpu.getRegisters().setRegister(15, (byte) 1);
        
        else
            cpu.getRegisters().setRegister(15, (byte) 0);

        cpu.getRegisters().setRegister(registerX, (byte) (valueX + valueY));
    }

    @Override
    public String toString() {
        return String.format("ADD V%X, V%X", registerX, registerY);
    }
}