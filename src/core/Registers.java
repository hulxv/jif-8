package core;

public class Registers {
    private byte[] registers;
    private static final int NUM_REGISTERS = 16;

    public Registers() {
        reset();
        System.out.println("Registers initialized");
    }

    public byte getRegister(int index) {
        if (index < 0 || index >= registers.length) {
            throw new IllegalArgumentException("Invalid register index");
        }
        return registers[index];
    }

    public void setRegister(int index, byte value) {
        if (index < 0 || index >= registers.length) {
            throw new IllegalArgumentException("Invalid register index");
        }
        registers[index] = value;
    }

    public void reset() {
        registers = new byte[NUM_REGISTERS];
    }

    public int getSize() {
        return NUM_REGISTERS;
    }

}
