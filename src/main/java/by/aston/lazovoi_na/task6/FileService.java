package by.aston.lazovoi_na.task6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class FileService {
    public static final String FILE_PATH = "./src/main/resources/files/file.txt";
    private static final String FILE_PATH1 = "./src/main/resources/files/file1.txt";
    private static final String FILE_PATH2 = "./src/main/resources/files/file2.txt";
    public static final String MERGED_FILE_PATH = "./src/main/resources/files/mergedFile.txt";
    public static final String REPLACE_TEXT_BY_SYMBOL_FILE_PATH = "./src/main/resources/files/replaceTextBySymbol.txt";

    public static List<String> readFile(String filePath) {
        List<String> list = new LinkedList<>();
        try {
            Files.readAllLines(Paths.get(filePath))
                .forEach(line -> list.addAll(Arrays.stream(line.split(" ")).toList()));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

    public static void addText(String filePath, String text) {
        try {
            Files.writeString(Paths.get(filePath), text, StandardOpenOption.APPEND);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void mergeFiles() {
        List<String> text = readFile(FILE_PATH1);
        text.addAll(readFile(FILE_PATH2));

        addText(MERGED_FILE_PATH, String.join(" ", text));
    }

    public static void replaceTextBySymbol(Character symbol) {
        try {
            Path path = Paths.get(REPLACE_TEXT_BY_SYMBOL_FILE_PATH);
            List<String> text = Files.readAllLines(path);
            text = text.stream().map(line -> line.replaceAll("[^a-zA-Zа-яА-Я0-9]", "\\" + symbol)).toList();

            Files.write(path, text, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
