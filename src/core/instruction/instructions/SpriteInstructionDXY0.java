package core.instruction.instructions;

import core.Display;
import core.instruction.Instruction;

public class SpriteInstructionDXY0 extends Instruction {
    private final int registerX;
    private final int registerY;
    private final int height;

    public SpriteInstructionDXY0(int rx, int ry, int height) {
        this.registerX = rx;
        this.registerY = ry;
        this.height = height;
    }

    @Override
    public void execute() {
        cpu.getRegisters().setRegister(0xF, (byte) 0);

        final int spriteWidth = 8;
        Display display = cpu.getDisplay();

        if (display == null) {
            throw new IllegalStateException("Display is not initialized");
        }

        int x = cpu.getRegisters().getRegister(registerX) & 0xFF;
        int y = cpu.getRegisters().getRegister(registerY) & 0xFF;

        boolean collision = false;

        for (int row = 0; row < height; row++) {
            if (y + row >= display.getHeight()) {
                break;
            }

            int spriteByte = cpu.getMemory().read((char) (cpu.getI() + row)) & 0xFF;

            for (int col = 0; col < spriteWidth; col++) {
                if (x + col >= display.getWidth()) {
                    break;
                }

                boolean spritePixel = (spriteByte & (0x80 >> col)) != 0;

                if (spritePixel) {
                    int pixelX = (x + col) % display.getWidth();
                    int pixelY = (y + row) % display.getHeight();

                    boolean currentPixel = display.getPixel(pixelX, pixelY);
                    if (currentPixel && spritePixel) {
                        collision = true;
                    }
                    display.setPixel(pixelX, pixelY, currentPixel ^ spritePixel);
                }
            }
        }
        cpu.getRegisters().setRegister(0xF, (byte) (collision ? 1 : 0));
        cpu.setDrawFlag(true);
    }

    @Override
    public String toString() {
        return String.format("SPRITE V%X, V%X, %X", registerX, registerY, height);
    }
}