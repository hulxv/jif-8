package core.instruction;

import core.Opcode;
import core.instruction.instructions.*;

public class InstructionFactory {
    public InstructionFactory() {
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
                    return new ClsInstruction();
                if (opcode == 0x00EE)
                    return new RtsInstruction();
                break;

            case 0x1:
                return new JumpInstruction((char)nnn);
                
            case 0x2:
                return new JsrInstruction(nnn);

            case 0x3:
                return new SkipEqualConstInstruction(x, nn);

            case 0x4:
                return new SkipNotEqualConstInstruction(x, nn);

            case 0x5:
                if ((opcode & 0x000F) == 0)
                    return new SkipEqualRegInstruction(x, y);
                break;

            case 0x6:
                return new SetRegisterInstruction(x, nn);

            case 0x7:
                return new AddRegisterConstInstruction(x, nn);

            case 0x8:
                switch (n) {
                    case 0x0:
                        return new MovRegInstruction(y, x);
                    case 0x1:
                        return new OrInstruction(x, y);
                    case 0x2:
                        return new AndInstruction(x, y);
                    case 0x3:
                        return new XorInstruction(x, y);
                    case 0x4:
                        return new AddRegInstruction(x, y);
                    case 0x5:
                        return new SubtractInstruction(x, y);
                    case 0x6:
                        return new ShiftRightInstruction(x);
                    case 0x7:
                        return new ReverseSubtractInstruction(x, y);
                    case 0xE:
                        return new ShiftLeftInstruction(x);
                }
                break;

            case 0x9:
                if ((opcode & 0x000F) == 0)
                    return new SkipNotEqualRegInstruction(x, y);
                break;

            case 0xA:
                return new MviInstruction(0, nnn);

            case 0xB:
                return new JmiInstruction(nnn);

            case 0xC:
                return new RandomInstruction(x, nn);

            case 0xD:
                return new DrawSpriteInstruction(x, y, n);

            case 0xE:
                switch (nn) {
                    case 0x9E:
                        return new SkipIfKeyPressedInstruction(x);
                    case 0xA1:
                        return new SkipIfKeyNotPressedInstruction(x);
                }
                break;

            case 0xF:
                switch (nn) {
                    case 0x07:
                        return new GDelayInstruction(x);
                    case 0x0A:
                        return new WaitKeyInstruction(x);
                    case 0x15:
                        return new SetDelayTimerInstruction(x);
                    case 0x18:
                        return new SSoundInstruction((char)x);
                    case 0x1E:
                        return new AddIndexInstruction(x);
                    case 0x29:
                        return new LoadFontInstruction(x);
                    case 0x33:
                        return new StoreBCDInstruction(x);
                    case 0x55:
                        return new StoreRegistersInstruction(x);
                    case 0x65:
                        return new LoadRegistersInstruction(x);
                }
                break;
        }

        throw new IllegalArgumentException("Invalid opcode: " + String.format("%04X", (int)opcode));
    }
}
