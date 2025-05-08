package core.instruction.instructions;

import core.instruction.Instruction;

public class StoreRegistersInstruction extends Instruction {
    private final int lastRegister;

    public StoreRegistersInstruction(int lastRegister) {
        this.lastRegister = lastRegister;
    }

    @Override
    public void execute() {
        // Implementation will store registers V0 through VX at memory location I
        System.out.printf("STORE V0-V%X\n", lastRegister);
    }

    @Override
    public String toString() {
        return String.format("STORE V0-V%X", lastRegister);
    }
}