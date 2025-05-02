package core;

public class Memory {

   protected byte[] RAM;
   protected byte[] fontSet;

   public Memory() {
      RAM = new byte[4096];
      fontSet = new byte[]{ 
         (byte) 0xF0, (byte) 0x90, (byte) 0x90, (byte) 0x90, (byte) 0xF0, // 0
         (byte) 0x20, (byte) 0x60, (byte) 0x20, (byte) 0x20, (byte) 0x70, // 1
         (byte) 0xF0, (byte) 0x10, (byte) 0xF0, (byte) 0x80, (byte) 0xF0, // 2
         (byte) 0xF0, (byte) 0x10, (byte) 0xF0, (byte) 0x10, (byte) 0xF0, // 3
         (byte) 0x90, (byte) 0x90, (byte) 0xF0, (byte) 0x10, (byte) 0x10, // 4
         (byte) 0xF0, (byte) 0x80, (byte) 0xF0, (byte) 0x10, (byte) 0xF0, // 5
         (byte) 0xF0, (byte) 0x80, (byte) 0xF0, (byte) 0x90, (byte) 0xF0, // 6
         (byte) 0xF0, (byte) 0x10, (byte) 0x20, (byte) 0x40, (byte) 0x40, // 7
         (byte) 0xF0, (byte) 0x90, (byte) 0xF0, (byte) 0x90, (byte) 0xF0, // 8
         (byte) 0xF0, (byte) 0x90, (byte) 0xF0, (byte) 0x10, (byte) 0xF0, // 9
         (byte) 0xF0, (byte) 0x90, (byte) 0xF0, (byte) 0x90, (byte) 0x90, // A
         (byte) 0xE0, (byte) 0x90, (byte) 0xE0, (byte) 0x90, (byte) 0xE0, // B
         (byte) 0xF0, (byte) 0x80, (byte) 0x80, (byte) 0x80, (byte) 0xF0, // C
         (byte) 0xE0, (byte) 0x90, (byte) 0x90, (byte) 0x90, (byte) 0xE0, // D
         (byte) 0xF0, (byte) 0x80, (byte) 0xF0, (byte) 0x80, (byte) 0xF0, // E
         (byte) 0xF0, (byte) 0x80, (byte) 0xF0, (byte) 0x80, (byte) 0x80  // F
       };
      System.out.println("Memory initialized");
   }
   
   public void reset() {
      for (int i = 0; i < RAM.length; i++) {
         RAM[i] = 0;
      }
   }

   public void loadFontSet() {
      for(int i = 0x50; i < 0x9F; i++) {
         RAM[i] = fontSet[i];
      }
   }

   public void loadProgram(char[] program, char offset) { //هنسأل عمدة فيها
      for (int i = 0; i < program.length; i++) {
         RAM[i + offset] = (byte) program[i];
      }
   }

   public byte read(char address) {
      if (address >= RAM.length) {
         throw new IllegalArgumentException("Memory address out of bounds");
     }
      return (byte) (RAM[address] & 0xFF);
   }

   public void write(char address, char value) {
      if (address >= RAM.length) {
         throw new IllegalArgumentException("Memory address out of bounds");
      }
      RAM[address] = (byte) value;
   }

   public byte[] dump(char start, char length) {
      byte[] segment = new byte[length];
      for (int i = 0; i < length; i++) {
         segment[i] = RAM[start + i];
      }
      return segment;
   }
}
