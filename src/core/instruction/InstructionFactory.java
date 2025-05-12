package core.instruction;

import core.*;
import core.instruction.instructions.*;

public class InstructionFactory {
    private final CPU cpu;

    public InstructionFactory(CPU cpu) {
        this.cpu = cpu;
    }

    private Instruction initInstruction(Instruction instruction) {
        instruction.setResources(cpu);
        return instruction;
    }

    public Instruction createInstruction(char opcode) {
        Opcode op = new Opcode(opcode);
        int firstNibble = (opcode & 0xF000) >> 12;
        int x = op.getX();
        int y = op.getY();
        int n = op.getN();
        int nn = op.getNN();
        int nnn = op.getNNN();

        switch (firstNibble) {
            case 0x0:
                if (opcode == 0x0000)
                    return initInstruction(new NopInstruction0000());
                if (opcode == 0x00E0)
                    return initInstruction(new ClsInstruction00E0());
                if (opcode == 0x00EE)
                    return initInstruction(new RtsInstruction00EE());
                break;

            case 0x1:
                return initInstruction(new JmpInstruction1NNN((char) nnn));

            case 0x2:
                return initInstruction(new JsrInstruction2NNN(nnn));

            case 0x3:
                return initInstruction(new SkeqConstInstruction3XNN(x, nn));

            case 0x4:
                return initInstruction(new SkneConstInstruction4XNN(x, nn));

            case 0x5:
                if (n == 0)
                    return initInstruction(new SkeqInstruction5XY0(x, y));
                break;

            case 0x6:
                return initInstruction(new MovConstInstruction6XNN(x, nn));

            case 0x7:
                return initInstruction(new AddConstInstruction7XNN(x, nn));

            case 0x8:
                switch (n) {
                    case 0x0:
                        return initInstruction(new MovInstruction8XY0(y, x));
                    case 0x1:
                        return initInstruction(new OrInstruction8XY1(x, y));
                    case 0x2:
                        return initInstruction(new AndInstruction8XY2(x, y));
                    case 0x3:
                        return initInstruction(new XorInstruction8XY3(x, y));
                    case 0x4:
                        return initInstruction(new AddInstruction8XY4(x, y));
                    case 0x5:
                        return initInstruction(new SubInstruction8XY5(x, y));
                    case 0x6:
                        return initInstruction(new ShrInstruction8X06(x));
                    case 0x7:
                        return initInstruction(new RsbInstruction8XY7(x, y));
                    case 0xE:
                        return initInstruction(new ShlInstruction8X0E(x));
                }
                break;

            case 0x9:
                if ((opcode & 0x000F) == 0)
                    return initInstruction(new SkneInstruction9XY0(x, y));
                break;

            case 0xA:
                return initInstruction(new MviInstructionANNN(nnn));

            case 0xB:
                return initInstruction(new JmiInstructionBNNN(nnn));

            case 0xC:
                return initInstruction(new RandInstructionCXNN(x, nn));

            case 0xD:
                return initInstruction(new SpriteInstructionDXY0(x, y, n));

            case 0xE:
                switch (nn) {
                    case 0x9E:
                        return initInstruction(new SkprInstructionEX9E(x));
                    case 0xA1:
                        return initInstruction(new SkupPressedInstructionEXA1(x));
                }
                break;

            case 0xF:
                switch (nn) {
                    case 0x07:
                        return initInstruction(new GDelayInstructionFX07(x));
                    case 0x0A:
                        return initInstruction(new KeyInstructionFX0A(x));
                    case 0x15:
                        return initInstruction(new SDelayInstructionFX15(x));
                    case 0x18:
                        return initInstruction(new SSoundInstructionFX18((char) x));
                    case 0x1E:
                        return initInstruction(new AdiInstructionFX1E(x));
                    case 0x29:
                        return initInstruction(new FontInstructionFX29(x));
                    case 0x33:
                        return initInstruction(new BcdInstructionFX33(x));
                    case 0x55:
                        return initInstruction(new StrInstructionFX55(x));
                    case 0x65:
                        return initInstruction(new LdrInstructionFX65(x));
                }
                break;
        }

        throw new IllegalArgumentException("Invalid opcode: " + String.format("%04X", (int) opcode));
    }
}
