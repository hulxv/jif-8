package core.instruction.instructions;

import core.instruction.Instruction;

public class ClsInstruction extends Instruction {
    public ClsInstruction() {
        super();
    }

    @Override
    public void execute() {
        // Clear the display
        System.out.println("Clearing the display");
        // Assuming display is a field in the CPU class
        // display.clear();
    }

    @Override
    public String toString() {
        return "CLS";
    }

}
