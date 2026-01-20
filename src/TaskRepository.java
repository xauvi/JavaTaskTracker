import java.io.*;
import java.util.*;

public class TaskRepository {

    private final Map<Integer, Task> tasks = new HashMap<>();
    private int nextId = 1;

    public Task save(String title, Priority priority) {
        Task task = new Task(nextId++, title, priority);
        tasks.put(task.getId(), task);
        return task;
    }

    public Collection<Task> findAll() {
        return tasks.values();
    }

    public boolean deleteById(int id) {
        return tasks.remove(id) != null;
    }

    // 🔹 Сохранение задач в файл
    public void saveToFile(String filename) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
            for (Task task : tasks.values()) {
                pw.println(task.getId() + "," + task.getTitle().replace(",", "\\,") + "," + task.getPriority());
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    // 🔹 Загрузка задач из файла
    public void loadFromFile(String filename) {
        File file = new File(filename);
        if (!file.exists()) return;

        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] parts = line.split("(?<!\\\\),"); // разделяем по запятым, игнорируя экранированные
                if (parts.length != 3) continue;

                int id = Integer.parseInt(parts[0]);
                String title = parts[1].replace("\\,", ",");
                Priority priority = Priority.valueOf(parts[2]);

                Task task = new Task(id, title, priority);
                tasks.put(id, task);
                nextId = Math.max(nextId, id + 1);
            }
        } catch (Exception e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
    }
}

