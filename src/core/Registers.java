package core;

public class Registers {
    private int[] registers;

    public Registers() {
        registers = new int[16]; 
        for (int i = 0; i < registers.length; i++) {
            registers[i] = 0;
        }
        System.out.println("Registers initialized");
    }
    public int getRegister(int index) {
        if (index < 0 || index >= registers.length) {
            throw new IllegalArgumentException("Invalid register index");
        }
        return registers[index];
    }

    public void setRegister(int index, int value) {
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

}
