package Services.Persistence;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class TextFileHandler implements PersistenceService {
    @Override
    public boolean save(List<String> data, String path) throws IOException {
        try (BufferedWriter textFile = new BufferedWriter(new FileWriter(path))) {
            for (String line : data) {
                textFile.write(line + "\n");
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public boolean load(String path, List<String> data) throws IOException {
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(path)))) {
            while (scanner.hasNextLine()) {
                data.add(scanner.nextLine());
            }
            return true;
        }
    }
    
}
