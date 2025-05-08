package core.instruction.instructions;

import core.instruction.Instruction;

public class TimerInstruction extends Instruction {
    private final int register;
    private final int operation;

    public TimerInstruction(int register, int operation) {
        this.register = register;
        this.operation = operation;
    }

    @Override
    public void execute() {
        switch (operation) {
            case 0x07: // LD Vx, DT
                System.out.printf("LD V%X, DT\n", register);
                break;
            case 0x15: // LD DT, Vx
                System.out.printf("LD DT, V%X\n", register);
                break;
            case 0x18: // LD ST, Vx
                System.out.printf("LD ST, V%X\n", register);
                break;
        }
    }

    @Override
    public String toString() {
        switch (operation) {
            case 0x07:
                return String.format("LD V%X, DT", register);
            case 0x15:
                return String.format("LD DT, V%X", register);
            case 0x18:
                return String.format("LD ST, V%X", register);
            default:
                return "UNKNOWN TIMER";
        }
    }
}