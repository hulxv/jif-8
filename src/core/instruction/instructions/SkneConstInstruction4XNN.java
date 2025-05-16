package core.instruction.instructions;

import core.instruction.Instruction;

public class SkneConstInstruction4XNN extends Instruction {
    private final int register;
    private final int value;

    public SkneConstInstruction4XNN(int register, int value) {
        this.register = register;
        this.value = value;
    }

    @Override
    public void execute() {
        // Implementation will skip next instruction if VX not equals NN
        byte valueX = cpu.getRegisters().getRegister(register);
        int PC = cpu.getPC();

        if (valueX != (byte) value)
            cpu.setPC(PC + 2);
    }

    @Override
    public String toString() {
        return String.format("SKNE V%X, %X", register, value);
    }
}