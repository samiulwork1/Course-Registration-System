package fileio;

import java.io.*;
import java.util.Scanner;

public class FileManager {
    private String fileName;

    public FileManager(String fileName) {
        this.fileName = fileName;
    }

    public void writeData(String data) {
        try {
            File file = new File(fileName);
            FileWriter writer = new FileWriter(file);

            writer.write(data);

            writer.flush();
            writer.close();
        } catch (IOException ex) {
            System.out.println("Cannot Write into the File.");
        }
    }

    public String readData() {
        String data = "";

        try {
            File file = new File(fileName);
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                data += sc.nextLine() + "\n";
            }

            sc.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Cannot Find File.");
        }

        return data;
    }
}