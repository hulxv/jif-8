package core.instruction.instructions;

import core.instruction.Instruction;

public class KeyInstruction extends Instruction {
    private final int register;

    public KeyInstruction(int register) {
        this.register = register;
    }

    @Override
    public void execute() {
        
        if (!cpu.getKeyboard().isWaitingForKey()) {

            System.out.println("Waiting for key press.....");
            cpu.getKeyboard().waitForKeyPress(key->{
                cpu.getRegisters().setRegister(register, key);
                cpu.setPC((char)(cpu.getPC()+2));
            });
            
        }        


    }

    @Override
    public String toString() {
        return String.format("LD V%X, K", register);
    }
}
