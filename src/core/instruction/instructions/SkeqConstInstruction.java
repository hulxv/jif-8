package core.instruction.instructions;

import core.instruction.Instruction;

public class SkeqConstInstruction extends Instruction {
    private final int register;
    private final int value;

    public SkeqConstInstruction(int register, int value) {
        this.register = register;
        this.value = value;
    }

    @Override
    public void execute() {
        // Implementation will skip next instruction if VX equals NN
        byte valueX = cpu.getRegisters().getRegister(register);
        char PC = cpu.getPC();
        char PCNewValue = (char) (PC + 2);

        if (valueX == (byte) value) 
            cpu.setPC(PCNewValue);
    }

    @Override
    public String toString() {
        return String.format("SE V%X, %X", register, value);
    }
}