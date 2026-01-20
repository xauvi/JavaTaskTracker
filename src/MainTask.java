
import java.util.Scanner;

public class MainTask {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskRepository repository = new TaskRepository();

        repository.loadFromFile("tasks.txt");

        TaskService service = new TaskService(repository);
        boolean running = true;

        while (running) {
            System.out.println("\n=== Welcome to the Tasks Management System ===");
            System.out.println("1. Add task");
            System.out.println("2. Show all tasks");
            System.out.println("3. Show high priority tasks");
            System.out.println("4. Remove task");
            System.out.println("5. Search tasks by title");
            System.out.println("6. Group tasks by priority");
            System.out.println("7. Exit");
            System.out.print("Please enter your choice: ");

            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter task name: ");
                    String title = sc.nextLine();

                    Priority priority = null;
                    while (priority == null) {
                        System.out.print("Enter priority (HIGH, MEDIUM, LOW): ");
                        String input = sc.nextLine();
                        try {
                            priority = Priority.valueOf(input.toUpperCase());
                        } catch (IllegalArgumentException e) {
                            System.out.println("Invalid priority! Try again.");
                        }
                    }

                    Task task = service.addTask(title, priority);
                    System.out.println("\nTask added successfully!");
                    break;

                case "2":
                    System.out.println("\nAll tasks:");
                    service.getAllSortedByPriority()
                            .forEach(System.out::println);
                    break;

                case "3":
                    System.out.println("\nHigh priority tasks:");
                    service.getHighestPriorityTasks()
                            .forEach(System.out::println);
                    break;

                case "4":
                    System.out.print("Enter task id to remove: ");
                    try {
                        int id = Integer.parseInt(sc.nextLine());
                        System.out.println(
                                service.removeTaskById(id)
                                ?"\nTask removed successfully!"
                                        : "\nTask not found!"
                        );
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid id!");
                    }
                    break;


                case "5": {
                    System.out.println("Enter keyword: ");
                    String keyword = sc.nextLine();
                    var results = service.searchByTitle(keyword);
                    if (results.isEmpty()) {
                        System.out.println("No such task found!");
                    } else {
                        results.forEach(System.out::println);
                    }
                    break;
                }

                case "6": {
                    var grouped = service.groupByPriority();
                    grouped.forEach((prio, taskList) -> {
                        System.out.println("\n " + prio + "+");
                        taskList.forEach(taskItem -> System.out.println(" - " + taskItem));
                    });
                    break;
                }

                case "7":
                    running = false;
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }

        repository.saveToFile("tasks.txt");

        sc.close();
    }
}