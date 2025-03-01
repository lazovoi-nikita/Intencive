package by.aston.lazovoi_na.task5;

import jdk.jfr.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamAPITest {
    @Test
    @Description("Вывести все четные числа в диапазоне от 1 до 100")
    public void test1() {
        List<Integer> list = IntStream.rangeClosed(1, 100).filter(i -> i % 2 == 0).boxed().toList();
        Assertions.assertEquals(IntStream.iterate(2, n -> n <= 100, i -> i + 2).boxed().toList(), list);
    }

    @Test
    @Description("Умножить каждое число в массиве [1, 2, 3, 4, 5] на 2")
    public void test2() {
        int[] array = Arrays.stream(new int[] {1, 2, 3, 4, 5}).map(i -> i * 2).toArray();
        Assertions.assertArrayEquals(new int[]{2, 4, 6, 8, 10}, array);
    }

    @Test
    @Description("Посчитать сумму чисел в массиве [1, 2, 3, 4, 5], используя reduce()")
    public void test3() {
        int sum = Arrays.stream(new int[] {1, 2, 3, 4, 5}).reduce(0, Integer::sum);
        Assertions.assertEquals(15, sum);
    }

    @Test
    @Description("Вывести числа в диапазоне от 1 до 50 с шагом 2")
    public void test4() {
        List<Integer> list = IntStream.iterate(1, n -> n <= 50, i -> i + 2).boxed().toList();
        Assertions.assertEquals(List.of(
                1, 3, 5, 7, 9, 11,
                13, 15, 17, 19, 21, 23, 25,
                27, 29, 31, 33, 35, 37, 39,
                41, 43, 45, 47, 49), list);
    }

    @Test
    @Description("Найти первый четный элемент в списке [1, 2, 3, 4, 5]")
    public void test5() {
        List<Integer> list = List.of(1, 2, 3, 4, 5);
        int number = list.stream().filter(i -> i % 2 == 0).findFirst().orElse(-1);
        Assertions.assertEquals(2, number);
    }

    @Test
    @Description("Отсортировать элементы массива [1, 3, 5, 7, 9] по возрастанию")
    public void test6() {
        int[] array = Arrays.stream(new int[] {3, 1, 7, 9, 5}).sorted().toArray();
        Assertions.assertArrayEquals(new int[] {1, 3, 5, 7, 9}, array);
    }

    @Test
    @Description("Вывести первые 10 чисел в списке [0, 1, 2, … 99]")
    public void test7() {
        List<Integer> list = IntStream.rangeClosed(0, 99).limit(10).boxed().toList();
        Assertions.assertEquals(List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9), list);
    }

    @Test
    @Description("Пропустить первые 10 элементов списка [0, 1, 2,.., 99] и начать выводить с 11-го элемента, выводя каждый 10-й элемент")
    public void test8() {
        List<Integer> list = IntStream.rangeClosed(0, 99).skip(10).filter(i -> (i - 10) % 10 == 0).boxed().toList();
        Assertions.assertEquals(List.of(10, 20, 30, 40, 50, 60, 70, 80, 90), list);
    }

    @Test
    @Description("Выведите на экран все числа в диапазоне от 1 до 100, которые делятся на 3")
    public void test9() {
        List<Integer> list = IntStream.rangeClosed(1, 100).filter(i -> i % 3 == 0).boxed().toList();
        Assertions.assertEquals(IntStream.iterate(3, n -> n <= 100, i -> i + 3).boxed().toList(), list);
    }

    @Test
    @Description("Выведите все нечетные числа в заданном массиве")
    public void test10() {
        int[] array = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        List<Integer> list = Arrays.stream(array).filter(i -> i % 2 != 0).boxed().toList();
        Assertions.assertEquals(List.of(1, 3, 5, 7, 9), list);
    }

    @Test
    @Description("Для любого набора случайно-сгенерированных чисел нужно определить количество парных." +
            "Для решения задачи использовать средства программного интерфейса Stream API.")
    public void test11() {
        List<Integer> list = IntStream.generate(() -> new Random().nextInt(1,10))
                .limit(10).boxed().toList();
        long count = list.stream().collect(Collectors.groupingBy(i -> i, Collectors.counting()))
                .values().stream().mapToLong(i -> i / 2).sum();
        System.out.println("Test 11 (list): " + list);
        System.out.println("Test 11 (count): " + count);
    }

    @Test
    @Description("Задан массив строк. Используя средства StreamAPI отсортировать строки в лексикографическом порядке")
    public void test12() {
        String[] array = new String[]{"go", "javascript", "c++", "java", "c"};
        String[] result = Arrays.stream(array).sorted().toArray(String[]::new);
        Assertions.assertArrayEquals(new String[] {"c", "c++", "go", "java", "javascript"}, result);
    }

    @Test
    @Description("Соберите все элементы Stream в одну строку через пробел и выведите результат")
    public void test13() {
        List<String> list = List.of("Minsk", "is", "the", "capital", "of", "Belarus");
        String str = list.stream().collect(Collectors.joining(" "));
        Assertions.assertEquals("Minsk is the capital of Belarus", str);
    }

    @Test
    @Description("Соберите слова в Stream в один текст, где каждое слово начинается с большой буквы и выведите результат")
    public void test14() {
        List<String> list = List.of("Minsk", "is", "the", "capital", "of", "Belarus");
        String str = list.stream().filter(i -> i.charAt(0) == Character.toUpperCase(i.charAt(0)))
                .collect(Collectors.joining(" "));
        Assertions.assertEquals("Minsk Belarus", str);
    }

    @Test
    @Description("Соберите числа в Stream в одно число, перемножив их между собой и выведите результат")
    public void test15() {
        List<Integer> list = IntStream.rangeClosed(1, 10).boxed().toList();
        long number = list.stream().reduce(1, (i, j) -> i * j);
        Assertions.assertEquals(2 * 3 * 4 * 5 * 6 * 7 * 8 * 9 * 10, number);
    }

    @Test
    @Description("Соберите даты в Stream в одну дату, сложив дни, месяцы и годы и выведите результат")
    public void test16() {
        List<LocalDate> list = List.of(
                LocalDate.of(2025, 2, 28),
                LocalDate.of(2024, 6, 1),
                LocalDate.of(2001, 4, 2));
        LocalDate date = list.stream()
                .reduce(LocalDate.of(0, 1, 1), (i, j) -> i
                        .plusYears(j.getYear())
                        .plusMonths(j.getMonthValue())
                        .plusDays(j.getDayOfMonth()))
                .minusMonths(1)
                .minusDays(1);
        Assertions.assertEquals(LocalDate.of(6050, 12, 31), date);
    }

    @Test
    @Description("Создать три стрима из массивов чисел от 1 до 10, от 10 до 20 и от 20 до 30 соответственно." +
            "Объединить их в один стрим и вывести числа, которые кратны 5")
    public void test17() {
        Stream<Integer> stream1 = IntStream.rangeClosed(1, 10).boxed();
        Stream<Integer> stream2 = IntStream.rangeClosed(10, 20).boxed();
        Stream<Integer> stream3 = IntStream.rangeClosed(20, 30).boxed();

        List<Integer> list = Stream.of(stream1, stream2, stream3).flatMap(stream -> stream)
                .filter(i -> i % 5 == 0).distinct().toList();
        Assertions.assertEquals(IntStream.iterate(5, n -> n <= 30, i -> i + 5).boxed().toList(), list);
    }
}
