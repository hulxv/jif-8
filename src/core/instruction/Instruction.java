package core.instruction;

import core.*;

public abstract class Instruction {
    public enum InstructionType {
        JUMP,
        NULL,
    }

    protected CPU cpu;

    public void setResources(CPU cpu) {
        this.cpu = cpu;
    }

    public Instruction() {
        System.out.println("Instruction initialized");
    }

    public abstract void execute();
}
