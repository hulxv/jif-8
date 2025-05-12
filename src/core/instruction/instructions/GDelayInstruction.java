package core.instruction.instructions;

import core.instruction.Instruction;

public class GDelayInstruction extends Instruction {
    private final int delay;

    public GDelayInstruction(int delay) {
        super();
        this.delay = delay;
    }

    @Override
    public void execute() {
        cpu.setDelayTimer((byte)delay);
    }

    @Override
    public String toString() {
        return "GDelay " + delay;
    }

    public int getDelay() {
        return delay;
    }

}
