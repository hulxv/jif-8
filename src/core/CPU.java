package core;

import java.util.Random;

import core.instruction.Instruction;

// fetch -> decode -> execute

public class CPU {
    private Registers registers;
    private int I;
    private int PC;
    private Stack stack;
    private byte delayTimer;
    private byte soundTimer;
    private Memory memory;
    private Display display;
    private Keyboard keyboard;
    private SoundSystem soundSystem;
    private Decoder decoder;
    private Executer executer;

    public CPU(Memory memory, Stack stack, Display display, Keyboard keyboard, SoundSystem soundSystem) {
        registers = new Registers();

        this.stack = stack;
        this.memory = memory;
        this.display = display;
        this.keyboard = keyboard;
        this.soundSystem = soundSystem;
        decoder = new Decoder(this);
        executer = new Executer();
        reset();
    }

    public Registers getRegisters() {
        return registers;
    }

    public Stack getStack() {
        return stack;
    }

    public Memory getMemory() {
        return memory;
    }

    public Display getDisplay() {
        return display;
    }

    public Keyboard getKeyboard() {
        return keyboard;
    }

    public SoundSystem getSoundSystem() {
        return soundSystem;
    }

    public Decoder getDecoder() {
        return decoder;
    }

    public Executer getExecuter() {
        return executer;
    }

    public void reset() {
        registers.reset();
        I = 0;
        PC = 0x200;
        stack.reset();
        delayTimer = 0;
        soundTimer = 0;
        memory.reset();
        display.reset();
        soundSystem.stopSound();
    }

    public void cycle() {
        char instruction = fetch();
        Instruction decodedInstruction = decoder.decode(instruction);
        System.out.printf("EXECUTE: PC: 0x%03X, I: 0x%03X, Instruction: %s\n", (int) PC, (int) I,
                decodedInstruction.toString());
        executer.execute(decodedInstruction);
        PC += 2;
        System.out.println("PC: " + PC);
        updateTimers();
    }

    private void updateTimers() {
        if (delayTimer > 0)
            delayTimer--;
        if (soundTimer > 0)
            soundTimer--;

        if (soundTimer == 0)
            soundSystem.beeb();
    }

    public char fetch() {
        // Read high byte first, then low byte
        char high = (char) (memory.RAM[PC] & 0xFF);
        char low = (char) (memory.RAM[PC + 1] & 0xFF);
        char op = (char) ((high << 8) | low);
        System.out.printf("FETCH: PC=0x%03X, Opcode=0x%04X\n", PC, (int) op);
        return op;
    }

    public int getI() {
        return I;
    }

    public void setI(char value) {
        I = value & 0xFFF;
    }

    public int getPC() {
        return PC;
    }

    public void setPC(int value) {
        PC = value & 0xFFF;
    }

    public byte getDelayTimer() {
        return delayTimer;
    }

    public void setDelayTimer(byte value) {
        delayTimer = value;
    }

    public byte getSoundTimer() {
        return soundTimer;
    }

    public void setSoundTimer(byte value) {
        soundTimer = value;
    }

    public byte generateRandomByte() {
        return (byte) new Random().nextInt(256);
    }
}
