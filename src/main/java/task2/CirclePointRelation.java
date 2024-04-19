package task2;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CirclePointRelation {
    //javac task2\CirclePointRelation.java
    //java task2.CirclePointRelation task2\path_to_circle_data.txt task2\path_to_points_data.txt

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java CirclePointRelation <circle_data_file> <points_data_file>");
            return;
        }
        String circleDataFile = args[0];
        String pointsDataFile = args[1];

        try {
            // Чтение данных окружности
            List<String> circleData = Files.readAllLines(Paths.get(circleDataFile));
            String[] center = circleData.get(0).split(" ");
            double centerX = Double.parseDouble(center[0]);
            double centerY = Double.parseDouble(center[1]);
            double radius = Double.parseDouble(circleData.get(1));

            // Чтение данных точек
            List<String> pointsData = Files.readAllLines(Paths.get(pointsDataFile));

            // Расчет и вывод положения каждой точки
            for (String pointLine : pointsData) {
                String[] point = pointLine.split(" ");
                double pointX = Double.parseDouble(point[0]);
                double pointY = Double.parseDouble(point[1]);

                double distance = Math.sqrt(Math.pow(pointX - centerX, 2) + Math.pow(pointY - centerY, 2));

                // Определение положения точки относительно окружности
                if (Math.abs(distance - radius) < 1e-12) { // Проверка на лежание на окружности с учетом погрешности
                    System.out.println("0");
                } else if (distance < radius) {
                    System.out.println("1");
                } else {
                    System.out.println("2");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

