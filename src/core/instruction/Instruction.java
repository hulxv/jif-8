package core.instruction;

public abstract class Instruction {
    enum InstructionType {
        JUMP,
    }
    public Instruction() {
        System.out.println("Instruction initialized");
    }
    
    public void execute() {
        System.out.println("Executing instruction");
    }
}
