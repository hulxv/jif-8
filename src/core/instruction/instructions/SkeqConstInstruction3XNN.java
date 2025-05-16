package core.instruction.instructions;

import core.instruction.Instruction;

public class SkeqConstInstruction3XNN extends Instruction {
    private final int register;
    private final int value;

    public SkeqConstInstruction3XNN(int register, int value) {
        this.register = register;
        this.value = value;
    }

    @Override
    public void execute() {
        // Implementation will skip next instruction if VX equals NN
        byte valueX = cpu.getRegisters().getRegister(register);
        int PC = cpu.getPC();

        if (valueX == (byte) value)
            cpu.setPC(PC + 2);
    }

    @Override
    public String toString() {
        return String.format("SE V%X, %X", register, value);
    }
}