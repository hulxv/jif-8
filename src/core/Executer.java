package core;

import core.instruction.Instruction;

public class Executer {
    public Executer() {
        System.out.println("Executer initialized");
    }
    
    public void execute(Instruction instruction) {
        System.out.println("Executing instruction...");
    }
    
}
