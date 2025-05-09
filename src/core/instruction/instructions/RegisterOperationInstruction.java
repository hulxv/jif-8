package core.instruction.instructions;

import core.instruction.Instruction;

public class RegisterOperationInstruction extends Instruction {
    private final int registerX; // Index of the VX register
    private final int registerY; // Index of the VY register
    private final int operation;

    public RegisterOperationInstruction(int rx, int ry, int op) {
        this.registerX = rx;
        this.registerY = ry;
        this.operation = op;
    }

    @Override
    public void execute() {
        //   Binary       :     Hexadecimal
        
        // 0b00000001    -->        0x1         Least Significant Bit
        // 0b10000000    -->        0x80        Most Significant Bit
        // 0b11111111    -->        0xFF        Masking all 8 bits (0xFF is the full 8-bit value)
        // 0b00000000    -->        0x00        Represents the value 0 (no bits set)
        // 0b00001111    -->        0xF         VF

        // Read signed 8-bit values from registers
        byte valueX = (byte) cpu.getRegisters().getRegister(registerX);
        byte valueY = (byte) cpu.getRegisters().getRegister(registerY);

        // Convert to unsigned 8-bit integers for correct arithmetic operations
        int unsignedX = valueX & 0xFF;
        int unsignedY = valueY & 0xFF;

        switch (operation) {
            case 0x0: { // LD Vx, Vy (Sets VX to the value of VY)
                cpu.getRegisters().setRegister(registerX, valueY);
                break;
            }

            case 0x1: { // OR Vx, Vy (Sets VX to VX OR VY. (bitwise OR operation))
                byte orResult = (byte) (unsignedX | unsignedY);
                cpu.getRegisters().setRegister(registerX, orResult);
                break;
            }

            case 0x2: { // AND Vx, Vy (Sets VX to VX AND VY. (bitwise AND operation))
                byte andResult = (byte) (unsignedX & unsignedY);
                cpu.getRegisters().setRegister(registerX, andResult);
                break;
            }

            case 0x3: { // XOR Vx, Vy (Sets VX to VX XOR VY)
                byte xorResult = (byte) (unsignedX ^ unsignedY);
                cpu.getRegisters().setRegister(registerX, xorResult);
                break;
            }

            case 0x4: { // ADD Vx, Vy (Adds VY to VX. VF is set to 1 when there's an overflow, and to 0 when there is not)
                int sum = unsignedX + unsignedY;
                byte sumResult = (byte) (sum & 0xFF); // Mask to 8 bits in hexadecimal
                byte carryFlag = (byte) (sum > 0xFF ? 1 : 0); // Set VF if overflow happened
                cpu.getRegisters().setRegister(registerX, sumResult);
                cpu.getRegisters().setRegister(0xF, carryFlag);
                break;
            }

            case 0x5: { // SUB Vx, Vy (VY is subtracted from VX. VF is set to 0 when there's an underflow, and 1 when there is not. (i.e. VF set to 1 if VX >= VY and 0 if not))
                int sub = unsignedX - unsignedY;
                byte subResult = (byte) (sub & 0xFF);
                byte noBorrowFlag = (byte) (unsignedX >= unsignedY ? 1 : 0); // Set VF if no borrow
                cpu.getRegisters().setRegister(registerX, subResult);
                cpu.getRegisters().setRegister(0xF, noBorrowFlag);
                break;
            }

            case 0x6: { // SHR Vx (Shifts VX to the right by 1, then stores the least significant bit of VX prior to the shift into VF)
                byte shiftRightVXResult = (byte) (unsignedX >>> 1);  // Logical shift right
                byte leastSignificantBit = (byte) (unsignedX & 0x1); // Least Significant Bit before shift in hexadecimal
                cpu.getRegisters().setRegister(registerX, shiftRightVXResult);
                cpu.getRegisters().setRegister(0xF, leastSignificantBit); // Save Least Significant Bit to VF
                break;
            }

            case 0x7: { // SUBN Vx, Vy (Sets VX to VY minus VX. VF is set to 0 when there's an underflow, and 1 when there is not. (i.e. VF set to 1 if VY >= VX))
                int subn = unsignedY - unsignedX;
                byte subnResult = (byte) (subn & 0xFF);
                byte noBorrowFlag = (byte) (unsignedY >= unsignedX ? 1 : 0); // Set VF if no borrow
                cpu.getRegisters().setRegister(registerX, subnResult);
                cpu.getRegisters().setRegister(0xF, noBorrowFlag);
                break;
            }

            case 0xE: { // SHL Vx (Shifts VX to the left by 1, then sets VF to 1 if the most significant bit of VX prior to that shift was set, or to 0 if it was unset)
                byte shiftLeftVXResult = (byte) ((unsignedX << 1) & 0xFF);
                byte mostSignificantBit = (byte) ((unsignedX & 0x80) >>> 7); // Most Significant Bit before shift in hexadecimal
                cpu.getRegisters().setRegister(0xF, mostSignificantBit); // Save Most Significant Bit to VF
                cpu.getRegisters().setRegister(registerX, shiftLeftVXResult);
                break;
            }

            default:
                throw new IllegalArgumentException("Unknown register operation: " + operation);
        }
    }

    @Override
    public String toString() {
        return String.format("REG_OP V%X, V%X, %X", registerX, registerY, operation);
    }
}