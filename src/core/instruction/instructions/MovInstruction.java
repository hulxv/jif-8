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
        // Logic to move data from one register to another
        System.out.println("Moving data from register " + sourceRegister + " to register " + destinationRegister);
        // Assuming registers is a field in the CPU class
        // registers[destinationRegister] = registers[sourceRegister];
    }

    @Override
    public String toString() {
        return "MOV V" + sourceRegister + ", V" + destinationRegister;
    }
}
