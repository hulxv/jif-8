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
        // Logic to set the delay
        System.out.println("Setting delay to " + delay);
        // Assuming there's a method in the CPU class to set the delay
        // cpu.setDelay(delay);
    }

    @Override
    public String toString() {
        return "GDelay " + delay;
    }

    public int getDelay() {
        return delay;
    }

}
