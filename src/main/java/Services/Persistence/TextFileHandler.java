package Services.Persistence;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
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

    public static void main(String[] args) {
        List<String> data = new ArrayList<>() {{
            add("0 Kore wa zombie desu ka (Full OP)");
            add("1 Kill la Kill Opening 2");
            add("2 Sugar Sweet Nightmare FULL SUB HQ (Bakemonogatari Opening 5) by Yui Horie");
            add("3 Another Heaven (Heaven's Feel Opening)");
            add("4 Kieru Daydream FULL HQ (Nekomonogatari: Kuro Ending) by Marina Kawano");
            add("5 Shingeki no Bahamut: Genesis OP 「EXiSTENCE」");
        }};

        String path = Paths.get("./test.txt").toAbsolutePath().toString();
        System.out.println("path: " + path);

        try {
            TextFileHandler textFile = new TextFileHandler();
            textFile.save(data, path);
            textFile.load(path, data);
        } catch (IOException e) {
            System.out.println("OOPS\n");
            e.printStackTrace();
        }
    }
}
