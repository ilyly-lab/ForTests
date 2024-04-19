package task1;

public class CircularArrayPath {
    //javac task1\CircularArrayPath.java
    //java task1.CircularArrayPath 5 4
    //java task1.CircularArrayPath 4 3
    public static void main(String[] args) {

        if (args.length != 2) {
            System.out.println("Usage: java CircularArrayPath <n> <m>");
            return;
        }

        int n = Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]);


        StringBuilder path = new StringBuilder(); // Для сохранения пути
        boolean[] visited = new boolean[n]; // Для отслеживания посещенных позиций
        int currentPosition = 0; // Начинаем с первой позиции (0 в Java, но выводим +1)

        do {
            path.append(currentPosition + 1); // Добавляем 1 для соответствия выводу
            visited[currentPosition] = true; // Отмечаем позицию как посещенную
            currentPosition = (currentPosition + m - 1) % n; // Вычисляем следующую позицию
        } while (!visited[currentPosition]); // Продолжаем, пока не попадем на уже посещенную позицию

        System.out.println(path.toString()); // Выводим путь
    }
}
