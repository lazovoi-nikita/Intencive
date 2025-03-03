package by.aston.lazovoi_na.task6;

import jdk.jfr.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.stream.Stream;

public class FileServiceTest {
    @Test
    @Description("1. Метод, который читает текстовый файл и возвращает его в виде списка строк")
    public void readFileTest() {
        String text = "Grandpa planted a turnip. The turnip grew bigger and bigger. " +
                "Grandpa came to pick the turnip, pulled and pulled but couldn't pull it up!";

        Assertions.assertEquals(Arrays.stream(text.split(" ")).toList(), FileService.readFile(FileService.FILE_PATH));
    }

    @Test
    @Description("2. Метод, который записывает в файл строку, переданную параметром")
    public void addTextTest() {
        String text = "Grandpa planted a turnip. The turnip grew bigger and bigger. " +
                "Grandpa came to pick the turnip, pulled and pulled but couldn't pull it up!";
        String str = "Grandpa called Grandma.";
        String newText = text + " " + str;

        FileService.addText(FileService.FILE_PATH, "\n" + str);

        Assertions.assertEquals(Arrays.stream(newText.split(" ")).toList(), FileService.readFile(FileService.FILE_PATH));

        //Восстановление исходного состояния файла до добавления новой строки
        FileService.rewriteFile(FileService.FILE_PATH, text);
    }

    @Test
    @Description("Используя решение 1 и 2, написать метод, который склеивает два текстовых файла в один")
    public void mergeFilesTest() {
        String resultText = "This is the story of Little Red Riding Hood. She’s got a red coat with a hood. " +
                "She loves the coat. She wears it every day. She’s very happy today. It’s her birthday. " +
                "Little Red Riding Hood’s father is a woodcutter. He works in the forest every day. A lot of animals " +
                "live in the forest, and a wolf lives there too!";

        FileService.mergeFiles();

        Assertions.assertEquals(Arrays.stream(resultText.split(" ")).toList(), FileService.readFile(FileService.MERGED_FILE_PATH));

        //Очистка файла
        FileService.rewriteFile(FileService.MERGED_FILE_PATH, "");
    }

    @Test
    @Description("Написать метод который заменяет в файле все кроме букв и цифр на знак $")
    public void replaceTextBySymbolTest() {
        String textBeforeReplacing = "This text is for checking the replacement of spaces and special " +
                "characters (such as , . @, etc.) to the $ symbol.";
        String textAfterReplacing = "This$text$is$for$checking$the$replacement$of$spaces$and$special$" +
                "characters$$such$as$$$$$$$$etc$$$to$the$$$symbol$";

        FileService.replaceTextBySymbol('$');

        Assertions.assertEquals(Stream.of(textAfterReplacing).toList(),
                FileService.readFile(FileService.REPLACE_TEXT_BY_SYMBOL_FILE_PATH));

        //Восстановление исходного состояния файла до изменений
        FileService.rewriteFile(FileService.REPLACE_TEXT_BY_SYMBOL_FILE_PATH, textBeforeReplacing);
    }
}
