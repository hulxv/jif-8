package core;

public class Opcode {
    private char opcode;

    public Opcode(char opcode) {
        this.opcode = opcode;
    }

    public char getOpcode() {
        return opcode;
    }

    public void setOpcode(char opcode) {
        this.opcode = opcode;
    }

    @Override
    public String toString() {
        return String.format("Opcode: %04X", opcode);
    }

    public int getX() {
        return (opcode & 0x0F00) >> 8;
    }

    public int getY() {
        return (opcode & 0x00F0) >> 4;
    }

    public int getN() {
        return opcode & 0x000F;
    }

    public int getNN() {
        return opcode & 0x00FF;
    }

    public int getNNN() {
        return opcode & 0x0FFF;
    }

    public int getAddress() {
        return opcode & 0x0FFF;
    }

    public int getInstruction() {
        throw new UnsupportedOperationException("Not implemented");
    }

}
