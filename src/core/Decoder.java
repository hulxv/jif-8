package core;

import core.instruction.Instruction;
import core.instruction.InstructionFactory;

public class Decoder{

    protected Opcode opcode;
    Decoder() {
        System.out.println("Decoder initialized");
    }

    public Instruction decode(char opcode) {
        throw new UnsupportedOperationException("Not implemented");
    }


}
