package fileio;

import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class FileManager {
    private String fileName;

    public FileManager(String fileName) {
        this.fileName = fileName;
    }

    public void writeData(String data) throws IOException {
        FileWriter writer = new FileWriter(fileName);
        writer.write(data);
        writer.close();
    }

    public String readData() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        StringBuilder data = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            data.append(line).append("\n");
        }

        reader.close();
        return data.toString();
    }
}
