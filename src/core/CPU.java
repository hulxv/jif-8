package core;

import java.util.Random;

import core.instruction.Instruction;

// fetch -> decode -> execute

public class CPU {
    private Registers registers;
    private char I;
    private char PC;
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
        I = 0;
        PC = 0x200;
        this.stack = stack;
        //delayTimer = 0;
        //soundTimer = 0;
        this.memory = memory;
        this.display = display;
        this.keyboard = keyboard;
        this.soundSystem = soundSystem;
        decoder = new Decoder();
        executer = new Executer();
    }

    public void reset() {
        registers.reset();
        I = 0;
        PC = 0x200;
        stack.reset();
        delayTimer = 0;
        soundTimer = 0;
        memory.reset();
        display.clear();
        soundSystem.stopSound();
    }

    public void cycle() {
        char opcode = fetch();
        //Instruction instruction = decoder.decode(opcode);
        //executer.execute(instruction);
        updateTimers();
    }

    private void updateTimers() {
        if (delayTimer > 0) delayTimer--;
        if (soundTimer > 0) soundTimer--;

        if (soundTimer == 0); //soundSystem.beeb();
    }

    public char fetch() {
        return (char) (memory.RAM[this.PC] << 8 | memory.RAM[this.PC + 1]);
    }

    public char getI() {
        return I;
    }

    public void setI(char value) {
        I = value;
    }

    public char getPC() {
        return PC;
    }

    public void setPC(char value) {
        PC = value;
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
