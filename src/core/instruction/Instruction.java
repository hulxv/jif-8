package core.instruction;

public abstract class Instruction {
    public enum InstructionType {
        JUMP,
        NULL,
    }
    public Instruction() {
        System.out.println("Instruction initialized");
    }
    
    public void execute() {
        System.out.println("Executing instruction");
    }
}
