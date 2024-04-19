package task3;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JsonReportBuilder {

    public static void main(String[] args) {
        // Задаем пути к файлам по умолчанию, если аргументы не предоставлены
        String testsFilePath = "C:/Users/FX/untitled/src/main/java/task3/tests.json";
        String valuesFilePath = "C:/Users/FX/untitled/src/main/java/task3/values.json";
        String reportFilePath = "C:/Users/FX/untitled/src/main/java/task3/report.json";

        // Проверка наличия аргументов и их использование, если они есть
        if (args.length == 3) {
            testsFilePath = args[0];
            valuesFilePath = args[1];
            reportFilePath = args[2];
        } else {
            System.out.println("No command line arguments provided. Using default file paths.");
        }

        try {
            // Загрузка JSON объектов из файлов
            JSONObject testsJson = new JSONObject(new JSONTokener(new FileInputStream(testsFilePath)));
            JSONObject valuesJson = new JSONObject(new JSONTokener(new FileInputStream(valuesFilePath)));

            // Создание карты для хранения значений тестов
            Map<Integer, String> valuesMap = new HashMap<>();
            JSONArray valuesArray = valuesJson.getJSONArray("values");
            for (int i = 0; i < valuesArray.length(); i++) {
                JSONObject valueObj = valuesArray.getJSONObject(i);
                valuesMap.put(valueObj.getInt("id"), valueObj.getString("value"));
            }

            // Обновление testsJson с использованием значений из valuesMap
            updateWithValues(testsJson, valuesMap);

            // Запись результата в файл report.json
            try (FileWriter file = new FileWriter(reportFilePath)) {
                file.write(testsJson.toString(2)); // Pretty print JSON
                file.flush();
            }

            System.out.println("Report generated successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void updateWithValues(JSONObject tests, Map<Integer, String> valuesMap) {
        // Рекурсивно обновляем значение, если найден соответствующий id в valuesMap
        if (tests.has("id") && valuesMap.containsKey(tests.getInt("id"))) {
            tests.put("value", valuesMap.get(tests.getInt("id")));
        }

        // Обработка дочерних элементов если они есть
        if (tests.has("values")) {
            JSONArray children = tests.getJSONArray("values");
            for (int i = 0; i < children.length(); i++) {
                updateWithValues(children.getJSONObject(i), valuesMap);
            }
        }
    }
}
