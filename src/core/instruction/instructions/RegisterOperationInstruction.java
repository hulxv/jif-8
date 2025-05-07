package core.instruction.instructions;

import core.instruction.Instruction;

public class RegisterOperationInstruction extends Instruction {
    private final int registerX;
    private final int registerY;
    private final int operation;

    public RegisterOperationInstruction(int rx, int ry, int op) {
        this.registerX = rx;
        this.registerY = ry;
        this.operation = op;
    }

    @Override
    public void execute() {
        // Implementation will handle various register operations based on last nibble
        switch(operation) {
            case 0x0: // LD Vx, Vy
                System.out.printf("LD V%X, V%X\n", registerX, registerY);
                break;
            case 0x1: // OR Vx, Vy
                System.out.printf("OR V%X, V%X\n", registerX, registerY);
                break;
            case 0x2: // AND Vx, Vy
                System.out.printf("AND V%X, V%X\n", registerX, registerY);
                break;
            case 0x3: // XOR Vx, Vy
                System.out.printf("XOR V%X, V%X\n", registerX, registerY);
                break;
            case 0x4: // ADD Vx, Vy
                System.out.printf("ADD V%X, V%X\n", registerX, registerY);
                break;
            case 0x5: // SUB Vx, Vy
                System.out.printf("SUB V%X, V%X\n", registerX, registerY);
                break;
            case 0x6: // SHR Vx
                System.out.printf("SHR V%X\n", registerX);
                break;
            case 0x7: // SUBN Vx, Vy
                System.out.printf("SUBN V%X, V%X\n", registerX, registerY);
                break;
            case 0xE: // SHL Vx
                System.out.printf("SHL V%X\n", registerX);
                break;
        }
    }

    @Override
    public String toString() {
        return String.format("REG_OP V%X, V%X, %X", registerX, registerY, operation);
    }
}