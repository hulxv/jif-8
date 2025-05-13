package core.instruction.instructions;

import core.instruction.Instruction;

public class AdiInstructionFX1E extends Instruction {
    private final int register;

    public AdiInstructionFX1E(int register) {
        this.register = register;
    }

    @Override
    public void execute() {
        int value = cpu.getRegisters().getRegister(register) & 0xFF;
        int index = cpu.getI() & 0xFFFF;

        int res = (value+index);
        byte carryFlag = (res > 0xFFFF ? (byte)1 : (byte)0);

        cpu.setI((char) (res& 0xFFFF));
        cpu.getRegisters().setRegister(0xF, carryFlag);
    }

    @Override
    public String toString() {
        return String.format("ADI V%X", register);
    }
}
