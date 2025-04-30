package core;

public class Emulator {
    CPU cpu;
    Memory memory;
    Loader loader;

    Emulator() {
        System.out.println("Emulator initialized");
    }

    public void initialize() {
        cpu = new CPU();
        memory = new Memory();
        loader = new Loader();
        System.out.println("Emulator components initialized");
    }

    public void loadRom(String romPath) {
        loader.load(romPath, memory);
        System.out.println("ROM loaded");
    }

    public void start() {
        System.out.println("Emulator started");
    }
}
