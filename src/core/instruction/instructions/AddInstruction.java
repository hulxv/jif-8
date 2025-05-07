package core.instruction.instructions;

import core.instruction.Instruction;

public class AddInstruction extends Instruction {
    private int operand1;
    private int operand2;

    public AddInstruction(int operand1, int operand2) {
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    public void execute() {
        // Logic to add two operands
        int result = operand1 + operand2;
        System.out.println("Result of addition: " + result);
    }

    @Override
    public String toString() {
        return "ADD " + operand1 + ", " + operand2;
    }
}
