package core;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Loader {
    

    Loader() {
        System.out.println("Loader initialized");
    }
 
    public byte [] loadRom(String filePath) {

        try {
            FileInputStream fis = new FileInputStream(filePath);
            byte [] romData = fis.readAllBytes();
            validateROM (romData);
            fis.close();
            return romData;        
        } catch (IOException |IllegalArgumentException e) {
            System.out.println("Error loading ROM: " + e.getMessage());
            return null;                                                   //take care of the null ya 3mda
        }
    }

    public boolean validateROM (byte [] data) {
        
        if ( data== null || data.length==0) {
            throw new IllegalArgumentException("ROM file is empty");
        }

        if (data.length > (0xFFF-0x200)) {
            throw new IllegalArgumentException("Rom file is larger than the Maximum size (0xFFF-0x200 bytes)");
        }

        return true;
    }    

    public List<String> listAvailableROMs() {
        throw new UnsupportedOperationException("Not implemented");
    }

}

