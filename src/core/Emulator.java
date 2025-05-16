package core;

public class Emulator {
    private CPU cpu;
    private Memory memory;
    private Stack stack;
    private Display display;
    private Keyboard keyboard;
    private SoundSystem soundSystem;
    private Loader loader;
    private boolean isRunning = false;

    public Emulator() {
        initialize();
        cpu = new CPU(memory, stack, display, keyboard, soundSystem);
        System.out.println("Emulator initialized");
    }

    private void initialize() {
        memory = new Memory();
        stack = new Stack();
        display = new Display();
        keyboard = new Keyboard();
        soundSystem = new SoundSystem();
        loader = new Loader();
        System.out.println("Emulator components initialized");
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void pause() {
        isRunning = false;
    }

    public void start() {
        isRunning = true;
    }

    public void reset() {
        cpu.reset();
        // memory.reset();
        stack.reset();
        display.reset();
        // keyboard.reset();
        soundSystem.stopSound();
    }

    public void loadRom(String romPath) {
        try {
            cpu.reset();
            System.out.println("Loading ROM from: " + romPath);
            byte[] romData = loader.loadRom(romPath);
            memory.loadROM(romData);
            System.out.println("ROM loaded");
            start();
        } catch (Exception e) {
            System.err.println("Error loading ROM: " + e.getMessage());
            return;
        }
    }

    public void emulateCycle() {
        if (isRunning()) {
            cpu.cycle();
        }
    }

    public CPU getCPU() {
        return cpu;
    }
}
