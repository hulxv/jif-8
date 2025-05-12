package core.instruction.instructions;

import core.instruction.Instruction;

public class ClsInstruction extends Instruction {
    public ClsInstruction() {
        super();
    }

    @Override
    public void execute() {
        cpu.getDisplay().reset();
    }

    @Override
    public String toString() {
        return "CLS";
    }

}
