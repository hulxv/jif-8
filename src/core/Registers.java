package core;

public class Registers {
    private byte[] registers;
    private static final int NUM_REGISTERS = 16;
    public Registers() {
        registers = new byte[NUM_REGISTERS]; 
        for (int i = 0; i < registers.length; i++) {
            registers[i] = 0;
        }
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
        for (int i = 0; i < registers.length; i++) {
            registers[i] = 0;
        }
    }

    public int getSize() {
        return NUM_REGISTERS;
    }

}
