import java.util.List;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;



public class TaskService {
    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public Task addTask(String title, Priority priority) {
        return repository.save(title, priority);
    }

    public List<Task> getAllSortedByPriority() {
        return repository.findAll().stream()
                .sorted(Comparator.comparing(Task::getPriority).reversed())
                .collect(Collectors.toList());
    }

    public List<Task> getHighestPriorityTasks() {
        return repository.findAll().stream()
                .filter(Task::isHighPriority)
                .collect(Collectors.toList());
    }

    public boolean removeTaskById(int id) {
        return repository.deleteById(id);
    }

    public List<Task> searchByTitle(String keyword) {
        String lowerCaseKeyword = keyword.toLowerCase();
        return repository.findAll().stream()
                .filter(task -> task.getTitle().toLowerCase().contains(lowerCaseKeyword))
                .collect(Collectors.toList());
    }

    public Map<Priority, List<Task>> groupByPriority() {
        return repository.findAll().stream()
                .collect(Collectors.groupingBy(Task::getPriority));
    }
}
