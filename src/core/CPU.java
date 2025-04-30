package core;

// fetch -> decode -> execute

public class CPU {
    Decoder decoder;
    Registers registers;
    Executer executer;

    public CPU() {
        System.out.println("CPU initialized");
    }
}
