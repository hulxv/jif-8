# JIF-8 Architecture


## Project Overview

The goal is to develop a fully functional CHIP-8 emulator in Java that accurately simulates the original CHIP-8 interpreter from the 1970s. CHIP-8 is an ideal first emulation project due to its simplicity while still offering a complete virtual machine experience.

## System Architecture

```mermaid
classDiagram
    %% Main Application Classes
    class Application {
        +main(String[] args)
        +initialize()
        +start()
        +stop()
    }

    class Emulator {
        -CPU cpu
        -Memory memory
        -Display display
        -Keyboard keyboard
        -SoundSystem soundSystem
        -boolean running
        -int cyclesPerSecond
        +initialize()
        +loadROM(byte[] data)
        +start()
        +stop()
        -emulationLoop()
        +emulateCycle()
        +getDisplay()
        +getKeyboard()
        +getCPU()
        +getMemory()
        +setCyclesPerSecond(int cyclesPerSecond)
    }

    class GUI {
        -JFrame frame
        -EmulatorPanel panel
        -JMenuBar menuBar
        -Emulator emulator
        +initialize()
        +createMenuBar()
        +createPanel()
        +refresh()
        +handleUserInput(KeyEvent event)
        +showOpenROMDialog()
        +showPreferencesDialog()
    }

    class ROMLoader {
        +byte[] loadROM(String filePath)
        +validateROM(byte[] data)
        +List~String~ listAvailableROMs()
    }

    %% Core Emulator Components
    class CPU {
        -byte[] V
        -char I
        -char PC
        -byte SP
        -char[] stack
        -byte delayTimer
        -byte soundTimer
        -Memory memory
        -Display display
        -Keyboard keyboard
        -SoundSystem soundSystem
        -InstructionDecoder decoder
        -Random random
        +initialize()
        +reset()
        +cycle()
        -updateTimers()
        +getRegister(int index)
        +setRegister(int index, byte value)
        +getI()
        +setI(char value)
        +getPC()
        +setPC(char value)
        +push(char value)
        +pop()
        +getDelayTimer()
        +setDelayTimer(byte value)
        +getSoundTimer()
        +setSoundTimer(byte value)
        +generateRandomByte()
    }

    class Memory {
        -byte[] RAM
        -byte[] fontset
        +initialize()
        +reset()
        +loadFontset()
        +read(char address)
        +write(char address, byte value)
        +loadProgram(byte[] program, char offset)
        +dump(char start, char length)
    }

    class Display {
        -boolean[][] pixels
        <<static>> +WIDTH: int
        <<static>> +HEIGHT: int
        +initialize()
        +clear()
        +setPixel(int x, int y, boolean value)
        +getPixel(int x, int y)
        +getDisplayBuffer()
    }

    class Keyboard {
        -boolean[] keys
        -KeyEventListener listener
        -byte waitingKey
        +initialize()
        +isKeyPressed(byte key)
        +setKeyPressed(byte key, boolean pressed)
        +waitForKeyPress()
        +handleKeyEvent(KeyEvent event)
        +registerListener(KeyEventListener listener)
    }

    class SoundSystem {
        -boolean soundPlaying
        -AudioClip beep
        +initialize()
        +playSound()
        +stopSound()
        +loadSound(String path)
    }

    %% Instruction System
    class InstructionDecoder {
        -InstructionFactory factory
        +decode(char opcode)
        -extractNNN(char opcode)
        -extractNN(char opcode)
        -extractX(char opcode)
        -extractY(char opcode)
        -decodeArithmeticOperation(char opcode)
        -decodeFOperations(char opcode)
    }

    class InstructionFactory {
        +createCLS()
        +createRET()
        +createJP(char address)
        +createCALL(char address)
        +createSE_VX_BYTE(byte x, byte value)
        +createSNE_VX_BYTE(byte x, byte value)
        +createSE_VX_VY(byte x, byte y)
        +createLD_VX_BYTE(byte x, byte value)
        +createADD_VX_BYTE(byte x, byte value)
        +createLD_VX_VY(byte x, byte y)
        +createOR_VX_VY(byte x, byte y)
        +createAND_VX_VY(byte x, byte y)
        +createXOR_VX_VY(byte x, byte y)
        +createADD_VX_VY(byte x, byte y)
        +createSUB_VX_VY(byte x, byte y)
        +createSHR_VX(byte x)
        +createSUBN_VX_VY(byte x, byte y)
        +createSHL_VX(byte x)
        +createSNE_VX_VY(byte x, byte y)
        +createLD_I_ADDR(char address)
        +createJP_V0_ADDR(char address)
        +createRND_VX_BYTE(byte x, byte value)
        +createDRW_VX_VY_N(byte x, byte y, byte n)
        +createSKP_VX(byte x)
        +createSKNP_VX(byte x)
        +createLD_VX_DT(byte x)
        +createLD_VX_K(byte x)
        +createLD_DT_VX(byte x)
        +createLD_ST_VX(byte x)
        +createADD_I_VX(byte x)
        +createLD_F_VX(byte x)
        +createLD_B_VX(byte x)
        +createLD_I_VX(byte x)
        +createLD_VX_I(byte x)
    }

    class Instruction {
        <<interface>>
        +execute(CPU cpu, Memory memory, Display display, Keyboard keyboard)
        +toString()
    }

    class AbstractInstruction {
        <<abstract>>
        #formatOpcode(int opcode)
        #extractNibbles(char opcode)
        #extractNN(char opcode)
        #extractNNN(char opcode)
    }

    class CLS {
        +execute(CPU cpu, Memory memory, Display display, Keyboard keyboard)
        +toString()
    }

    class RET {
        +execute(CPU cpu, Memory memory, Display display, Keyboard keyboard)
        +toString()
    }

    class JP {
        -char address
        +execute(CPU cpu, Memory memory, Display display, Keyboard keyboard)
        +toString()
    }

    class LD_VX_BYTE {
        -byte registerX
        -byte value
        +execute(CPU cpu, Memory memory, Display display, Keyboard keyboard)
        +toString()
    }

    class DRW {
        -byte x
        -byte y
        -byte height
        +execute(CPU cpu, Memory memory, Display display, Keyboard keyboard)
        +toString()
    }

    %% Debugging Tools
    class Debugger {
        -boolean enabled
        -Map breakpoints
        -CPU cpu
        -Memory memory
        -boolean paused
        +toggleDebugMode()
        +isPaused()
        +setPaused(boolean paused)
        +addBreakpoint(int address)
        +removeBreakpoint(int address)
        +hasBreakpoint(int address)
        +displayState()
        +stepInstruction()
        +continueExecution()
    }

    class Disassembler {
        -InstructionDecoder decoder
        +disassemble(char opcode)
        +disassembleRange(Memory memory, char start, char end)
        +getInstructionString(char address, char opcode)
    }

    %% Configuration System
    class Configuration {
        -int cyclesPerSecond
        -int displayScale
        -Map keyMapping
        -Color[] displayColors
        +load()
        +save()
        +getKeyMapping()
        +setKeyMapping(int chipKey, int javaKey)
        +getCyclesPerSecond()
        +setCyclesPerSecond(int cycles)
        +getDisplayScale()
        +setDisplayScale(int scale)
        +getDisplayColors()
        +setDisplayColors(Color[] colors)
    }

    %% Component Relationships
    Application --> Emulator
    Application --> GUI
    Application --> ROMLoader
    Application --> Configuration
    Emulator --> CPU
    Emulator --> Memory
    Emulator --> Display
    Emulator --> Keyboard
    Emulator --> SoundSystem
    CPU --> InstructionDecoder
    InstructionDecoder --> InstructionFactory
    InstructionFactory ..> CLS
    InstructionFactory ..> RET
    InstructionFactory ..> JP
    InstructionFactory ..> LD_VX_BYTE
    InstructionFactory ..> DRW
    Instruction <|-- AbstractInstruction
    AbstractInstruction <|-- CLS
    AbstractInstruction <|-- RET
    AbstractInstruction <|-- JP
    AbstractInstruction <|-- LD_VX_BYTE
    AbstractInstruction <|-- DRW
    GUI --> Debugger
    GUI --> Disassembler
    Debugger --> CPU
    Debugger --> Memory
    Disassembler --> InstructionDecoder

```

## Project Tasks Breakdown

### Phase 1: Core Emulator Components 

1. **Memory Subsystem** 
   - Implement 4KB memory space
   - Add font set loading
   - Create memory read/write methods

2. **CPU Implementation** 
   - Implement registers (V0-VF, I, PC, SP)
   - Create fetch-decode-execute cycle
   - Set up timers (delay and sound)

3. **Instruction Set Development** 
   - Create instruction decoder
   - Implement all 35 CHIP-8 opcodes
   - Develop instruction execution framework

4. **Display Component** 
   - Create 64x32 display buffer
   - Implement sprite drawing
   - Add screen clearing functionality

### Phase 2: I/O and Integration

5. **Keyboard Input**
- Map physical keys to CHIP-8 hexadecimal keypad
   - Implement key press/release detection
   - Add key-wait functionality

6. **Sound System** 
   - Implement basic tone generation
   - Connect to sound timer

7. **ROM Loading** 
   - Create file loader for CHIP-8 programs
   - Add validation for ROM format
   - Implement memory mapping of program data

### Phase 3: User Interface

8. **GUI Development** 
   - Create main application window
   - Implement display rendering with scaling
   - Add menus and file dialog for ROM selection
   - Create control panel for emulator operation

9. **Debugger Implementation** 
   - Add register and memory viewers
   - Implement breakpoint system
   - Create instruction stepping functionality
   - Add disassembly view

10. **Configuration System** 
    - Add settings for display colors
    - Create key mapping configuration
    - Implement emulation speed controls

### Phase 4: Testing and Refinement 

11. **Unit Testing** 
    - Write tests for CPU operations
    - Create test cases for instruction execution
    - Validate memory and display functions

### Phase 5: Documentation 

14. **Documentation** (3 days)
    - Create JavaDoc for all components
    - Write user guide for the emulator
    - Document the CHIP-8 specification

15. **Packaging and Deployment** (2 days)
    - Create build scripts
    - Package as executable JAR
    - Set up release platform

## Development Environment

- **Language**: Java 17+
- **Build Tool**: Maven 
- **Version Control**: Git with GitHub
