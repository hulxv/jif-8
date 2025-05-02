package core;

import core.instruction.Instruction;
import core.instruction.InstructionFactory;

public class Decoder{

    protected char opcode;
    Decoder() {
        System.out.println("Decoder initialized");
    }

    private char extractX(char opcode) {
        return (char) (opcode & 0x0F00 >> 8);
    }

    private char extractY(char opcode) {
        return (char) (opcode & 0x00F0 >> 4);
    }

    private char extractNNN(char opcode) {
        return (char) (opcode & 0x0FFF);
    }

    private char extractNN(char opcode) {
        return (char) (opcode & 0x0FF0);
    }

    private char extractN(char opcode) {
        return (char) (opcode & 0x000F);
    }

    public Instruction decode(char opcode) {
        throw new UnsupportedOperationException("Not implemented");
    }


}
