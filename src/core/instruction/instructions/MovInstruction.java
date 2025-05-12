package core.instruction.instructions;

import core.instruction.Instruction;

public class MovInstruction extends Instruction {
    private int sourceRegister;
    private int destinationRegister;

    public MovInstruction(int sourceRegister, int destinationRegister) {
        this.sourceRegister = sourceRegister;
        this.destinationRegister = destinationRegister;
    }

    public void execute() {
        byte source = cpu.getRegisters().getRegister(sourceRegister);
        cpu.getRegisters().setRegister(destinationRegister, source);
    }

    @Override
    public String toString() {
        return "MOV V" + sourceRegister + ", V" + destinationRegister;
    }
}
