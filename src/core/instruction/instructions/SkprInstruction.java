package core.instruction.instructions;

import core.instruction.Instruction;

public class SkprInstruction extends Instruction {
    private final int register;

    public SkprInstruction(int register) {
        this.register = register;
    }

    @Override
    public void execute() {        
        byte key = cpu.getRegisters().getRegister(register);

        if (cpu.getKeyboard().isKeyPressed(key)) {
            cpu.setPC((char)(cpu.getPC()+2));
        }
    }

    @Override
    public String toString() {
        return String.format("SKPR V%X", register);
    }
}
