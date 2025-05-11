package core.instruction.instructions;

import core.instruction.Instruction;

public class SkneInstruction extends Instruction {
    private final int registerX;
    private final int registerY;

    public SkneInstruction(int rx, int ry) {
        this.registerX = rx;
        this.registerY = ry;
    }

    @Override
    public void execute() {
        // Implementation will skip next instruction if VX not equals VY
        byte valueX = cpu.getRegisters().getRegister(registerX);
        byte valueY = cpu.getRegisters().getRegister(registerY);
        char PC = cpu.getPC();
        char PCNewValue = (char) (PC + 2);

        if (valueX != valueY) 
            cpu.setPC(PCNewValue);;
    }

    @Override
    public String toString() {
        return String.format("SKNE V%X, V%X", registerX, registerY);
    }
}