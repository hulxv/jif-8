package core;

public class Emulator {
    private CPU cpu;
    private Memory memory;
    private Stack stack;
    private Display display;
    private Keyboard keyboard;
    private SoundSystem soundSystem;
    private Loader loader;

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

    public void loadRom(String romPath) {
        try {
            System.out.println("Loading ROM from: " + romPath);
            byte[] romData = loader.loadRom(romPath);
            memory.loadROM(romData);
            System.out.println("ROM loaded");
        } catch (Exception e) {
            System.err.println("Error loading ROM: " + e.getMessage());
            return;
        }
    }

    public void start() {
        System.out.println("Emulator started");
    }

    public void emulateCycle() {
        cpu.cycle();
    }

    public CPU getCpu(){
        return cpu;
    }
}
