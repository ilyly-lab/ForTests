package task4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MinimizeMoves {
    //javac task4\MinimizeMoves.java
    //java task4.MinimizeMoves task4\test.txt
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java MinimizeMoves <path_to_input_file>");
            return;
        }

        String filePath = args[0];
        List<Integer> numbers = new ArrayList<>();

        // Чтение чисел из файла
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                numbers.add(Integer.parseInt(line.trim()));
            }
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
            return;
        }

        // Вычисление медианы
        Collections.sort(numbers);
        int median = numbers.get(numbers.size() / 2);

        // Подсчет необходимого количества ходов
        int moves = 0;
        for (int num : numbers) {
            moves += Math.abs(num - median);
        }

        // Вывод результата
        System.out.println(moves);
    }
}
