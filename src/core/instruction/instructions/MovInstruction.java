package core.instruction.instructions;

import core.instruction.Instruction;

public class MovInstruction extends Instruction {
    private int sourceRegister;
    private int destinationRegister;

    public MovInstruction(int sourceRegister, int destinationRegister) {
        this.sourceRegister = sourceRegister;
        this.destinationRegister = destinationRegister;
    }

    @Override
    public void execute() {
        byte source = cpu.getRegisters().getRegister(sourceRegister);
        System.out.println("Moving data from register " + sourceRegister + " to register " + destinationRegister); //see if you will need it for testing or delete it
        cpu.getRegisters().setRegister(destinationRegister, source);
        cpu.setPC((char) (cpu.getPC()+2));
    }

    @Override
    public String toString() {
        return "MOV V" + sourceRegister + ", V" + destinationRegister;
    }
}
