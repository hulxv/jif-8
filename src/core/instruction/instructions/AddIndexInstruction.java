package core.instruction.instructions;

import core.instruction.Instruction;

public class AddIndexInstruction extends Instruction {
    private final int register;

    public AddIndexInstruction(int register) {
        this.register = register;
    }

    @Override
    public void execute() {
        // Implementation will add value in VX to index register I
        System.out.printf("ADI V%X\n", register);
    }

    @Override
    public String toString() {
        return String.format("ADI V%X", register);
    }
}