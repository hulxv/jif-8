package core;

import core.instruction.Instruction;
import core.instruction.InstructionFactory;

public class Decoder {
    protected Opcode opcode;
    private final InstructionFactory factory;

    public Decoder(CPU cpu) {
        this.factory = new InstructionFactory(cpu);
    }

    public Instruction decode(char opcode) {
        return factory.createInstruction(opcode);
    }
}
