package core.instruction;

import core.*;

public abstract class Instruction {
    protected CPU cpu;

    public void setResources(CPU cpu) {
        this.cpu = cpu;
    }

    public abstract void execute();
}
