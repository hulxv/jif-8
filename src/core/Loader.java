package core;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Loader {
    private static final int ROM_START_ADDRESS = 0x200;
    private static final int ROM_MAX_SIZE = 0xFFF - ROM_START_ADDRESS;
    private final String romDirectory;

    public Loader() {
        this.romDirectory = System.getProperty("user.dir") + File.separator + "roms";
    }

    public Loader(String romDirectory) {
        this.romDirectory = romDirectory;
    }

    public byte[] loadRom(String filePath) throws IOException {
        File romFile = new File(filePath);
        if (!romFile.exists()) {
            throw new IOException("ROM file does not exist: " + filePath);
        }

        try (FileInputStream fis = new FileInputStream(romFile)) {
            byte[] romData = fis.readAllBytes();
            validateROM(romData);
            return romData;
        } catch (IllegalArgumentException e) {
            throw new IOException("Invalid ROM file: " + e.getMessage());
        }
    }

    private void validateROM(byte[] data) {
        if (data == null || data.length == 0) {
            throw new IllegalArgumentException("ROM file is empty");
        }

        if (data.length > ROM_MAX_SIZE) {
            throw new IllegalArgumentException(
                    String.format("ROM file is larger than maximum size (%d bytes)", ROM_MAX_SIZE));
        }
    }

    public List<String> listAvailableROMs() {
        List<String> romFiles = new ArrayList<>();
        File directory = new File(romDirectory);

        if (!directory.exists() || !directory.isDirectory()) {
            throw new IllegalStateException("ROM directory does not exist or is not a directory: " + romDirectory);
        }

        File[] files = directory
                .listFiles((dir, name) -> name.toLowerCase().endsWith(".ch8") || name.toLowerCase().endsWith(".rom"));

        if (files != null) {
            for (File file : files) {
                romFiles.add(file.getName());
            }
        }

        return romFiles;
    }

    public String getRomDirectory() {
        return romDirectory;
    }
}
