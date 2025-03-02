package by.aston.lazovoi_na.task6;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileService {
    public static final String FILE_PATH = "./src/main/resources/files/file.txt";
    private static final String FILE_PATH1 = "./src/main/resources/files/file1.txt";
    private static final String FILE_PATH2 = "./src/main/resources/files/file2.txt";
    public static final String MERGED_FILE_PATH = "./src/main/resources/files/mergedFile.txt";
    public static final String REPLACE_TEXT_BY_SYMBOL_FILE_PATH = "./src/main/resources/files/replaceTextBySymbol.txt";
    private static final Logger logger = LoggerFactory.getLogger(FileService.class);

    public static List<String> readFile(String filePath) {
        List<String> list = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                list.addAll(Arrays.stream(line.split(" ")).toList());
            }
        } catch (IOException ex) {
            logger.error("Error reading file.", ex);
        }
        return list;
    }

    public static void addText(String filePath, String text) {
        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(filePath), StandardOpenOption.APPEND)) {
            bw.write(text);
        } catch (IOException ex) {
            logger.error("Error add text to file.", ex);
        }
    }

    public static void mergeFiles() {
        List<String> text = readFile(FILE_PATH1);
        text.addAll(readFile(FILE_PATH2));

        addText(MERGED_FILE_PATH, String.join(" ", text));
    }

    public static void replaceTextBySymbol(Character symbol) {
        Path path = Paths.get(REPLACE_TEXT_BY_SYMBOL_FILE_PATH);
        List<String> text = new ArrayList<>();

        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line;
            while ((line = br.readLine()) != null) {
                text.add(line.replaceAll("[^a-zA-Zа-яА-Я0-9]", "\\" + symbol));
            }
        } catch (IOException ex) {
            logger.error("Error reading file.", ex);
        }

        try (BufferedWriter bw = Files.newBufferedWriter(path)) {
            for (int i = 0; i < text.size(); i++) {
                bw.write(text.get(i));
                if (i < text.size() - 1) {
                    bw.newLine();
                }
            }
        } catch (IOException ex) {
            logger.error("Error writing file.", ex);
        }
    }

    public static void rewriteFile(String path, String text) {
        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(path), StandardOpenOption.TRUNCATE_EXISTING)) {
            bw.write(text);
        } catch (IOException ex) {
            logger.error("Error writing file.", ex);
        }
    }
}
