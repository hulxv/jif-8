package core.instruction.instructions;

import core.instruction.Instruction;

public class ClsInstruction00E0 extends Instruction {
    public ClsInstruction00E0() {
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
