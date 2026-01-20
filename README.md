Java Task Tracker

Project Description
A console-based Java application for managing tasks with support for priorities, search, grouping, and saving tasks to a file.

The project follows clean architecture:
UI → Service → Repository → Model
Stream API for filtering, sorting, and grouping tasks
Tasks are saved/loaded from tasks.txt
Unique id for each task
Priority enum ensures strict priority values

Features
1. Add a task with a title and priority (HIGH, MEDIUM, LOW)
2. View all tasks sorted by priority
3. View only high-priority tasks
4. Remove a task by ID
5. Search tasks by part of the title (case-insensitive)
6. Group tasks by priority
7. Save tasks to a file on exit and automatically load tasks on start

Project Structure

src/
 
 ├─ MainTask.java       // Console UI
 
 ├─ TaskService.java    // Application logic
 
 ├─ TaskRepository.java // Data storage + file save/load
 
 ├─ Task.java           // Task model
 
 ├─ Priority.java       // Priority enum

tasks.txt               // File to store tasks

How to Run

1. Compile the project:
javac *.java

2. Run the application:
java MainTask

3. Follow the console instructions. Tasks will be saved to tasks.txt after exit.

Notes
tasks.txt is automatically created if it doesn’t exist
When entering priority, use HIGH, MEDIUM, or LOW (no quotes)
Task IDs for deletion or search must be numeric
Filters and search are case-insensitive
