package core.instruction.instructions;

import core.Display;
import core.instruction.Instruction;

<<<<<<< HEAD:src/core/instruction/instructions/DrawSpriteInstruction.java
public class DrawSpriteInstruction extends Instruction {
    private final int registerX; // Holds X-Coordinate (VX)
    private final int registerY; // Holds Y-Coordinate (VY)
    private final int height; // Number of bytes in the sprite, representing height (each byte is one row of 8 pixels)
=======
public class SpriteInstruction extends Instruction {
    private final int registerX;
    private final int registerY;
    private final int height;
>>>>>>> d780447445250ad4c3c508551f8ee6f29b79c3a3:src/core/instruction/instructions/SpriteInstruction.java

    public SpriteInstruction(int rx, int ry, int height) {
        this.registerX = rx;
        this.registerY = ry;
        this.height = height;
    }

    @Override
    public void execute() {
<<<<<<< HEAD:src/core/instruction/instructions/DrawSpriteInstruction.java
        cpu.getRegisters().setRegister(0xF, (byte) 0); // Reset Collision Flag
        final int pixelWidth = 8; // Sprites are always 8 pixels wide

        int xCoordinate = cpu.getRegisters().getRegister(registerX) & 0xFF;
        int yCoordinate = cpu.getRegisters().getRegister(registerY) & 0xFF;

        for(int row = 0 ; row < height ; row++) {
            int spriteByte = cpu.getMemory().read((char) (cpu.getI() + row)); // (cpu.getI() + row) is address

            for (int column = 0 ; column < pixelWidth ; column++) {
                if((spriteByte & (0x80 >> column)) != 0) {
                int x = (column + xCoordinate) % Display.WIDTH;
                int y = (row + yCoordinate) % Display.HEIGHT;
                boolean currentPixel = cpu.getDisplay().getPixel(x, y);

                if(currentPixel) {
                    cpu.getRegisters().setRegister(0xF, (byte) 1); // Set collision flag
                }
                cpu.getDisplay().setPixel(x, y, currentPixel ^ true);
                }
            }
        }
=======
        // Implementation will draw sprite at location (VX,VY) with height N
        System.out.printf("SPRITE V%X, V%X, %X\n", registerX, registerY, height);
>>>>>>> d780447445250ad4c3c508551f8ee6f29b79c3a3:src/core/instruction/instructions/SpriteInstruction.java
    }

    @Override
    public String toString() {
        return String.format("SPRITE V%X, V%X, %X", registerX, registerY, height);
    }
}