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
                if (opcode == 0x00E0)
                    return initInstruction(new ClsInstruction());
                if (opcode == 0x00EE)
                    return initInstruction(new RtsInstruction());
                break;

            case 0x1:
                return initInstruction(new JumpInstruction((char) nnn));

            case 0x2:
                return initInstruction(new JsrInstruction(nnn));

            case 0x3:
                return initInstruction(new SkipEqualConstInstruction(x, nn));

            case 0x4:
                return initInstruction(new SkipNotEqualConstInstruction(x, nn));

            case 0x5:
                if ((opcode & 0x000F) == 0)
                    return initInstruction(new SkipEqualRegInstruction(x, y));
                break;

            case 0x6:
                return initInstruction(new SetRegisterInstruction(x, nn));

            case 0x7:
                return initInstruction(new AddRegisterConstInstruction(x, nn));

            case 0x8:
                switch (n) {
                    case 0x0:
                        return initInstruction(new MovRegInstruction(y, x));
                    case 0x1:
                        return initInstruction(new OrInstruction(x, y));
                    case 0x2:
                        return initInstruction(new AndInstruction(x, y));
                    case 0x3:
                        return initInstruction(new XorInstruction(x, y));
                    case 0x4:
                        return initInstruction(new AddRegInstruction(x, y));
                    case 0x5:
                        return initInstruction(new SubtractInstruction(x, y));
                    case 0x6:
                        return initInstruction(new ShiftRightInstruction(x));
                    case 0x7:
                        return initInstruction(new ReverseSubtractInstruction(x, y));
                    case 0xE:
                        return initInstruction(new ShiftLeftInstruction(x));
                }
                break;

            case 0x9:
                if ((opcode & 0x000F) == 0)
                    return initInstruction(new SkipNotEqualRegInstruction(x, y));
                break;

            case 0xA:
                return initInstruction(new MviInstruction(0, nnn));

            case 0xB:
                return initInstruction(new JmiInstruction(nnn));

            case 0xC:
                return initInstruction(new RandomInstruction(x, nn));

            case 0xD:
                return initInstruction(new DrawSpriteInstruction(x, y, n));

            case 0xE:
                switch (nn) {
                    case 0x9E:
                        return initInstruction(new SkipIfKeyPressedInstruction(x));
                    case 0xA1:
                        return initInstruction(new SkipIfKeyNotPressedInstruction(x));
                }
                break;

            case 0xF:
                switch (nn) {
                    case 0x07:
                        return initInstruction(new GDelayInstruction(x));
                    case 0x0A:
                        return initInstruction(new WaitKeyInstruction(x));
                    case 0x15:
                        return initInstruction(new SetDelayTimerInstruction(x));
                    case 0x18:
                        return initInstruction(new SSoundInstruction((char) x));
                    case 0x1E:
                        return initInstruction(new AddIndexInstruction(x));
                    case 0x29:
                        return initInstruction(new LoadFontInstruction(x));
                    case 0x33:
                        return initInstruction(new StoreBCDInstruction(x));
                    case 0x55:
                        return initInstruction(new StoreRegistersInstruction(x));
                    case 0x65:
                        return initInstruction(new LoadRegistersInstruction(x));
                }
                break;
        }

        throw new IllegalArgumentException("Invalid opcode: " + String.format("%04X", (int) opcode));
    }
}
