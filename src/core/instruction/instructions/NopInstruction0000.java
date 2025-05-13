package core.instruction.instructions;

import core.instruction.Instruction;

public class NopInstruction0000 extends Instruction {
    public NopInstruction0000() {
        super();
    }

    @Override
    public void execute() {
        return;
    }

    @Override
    public String toString() {
        return "NOP";
    }

}
