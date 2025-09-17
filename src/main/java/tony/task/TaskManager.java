package tony.task;

import java.util.ArrayList;

public class TaskManager {
    ArrayList<Task> tasks;
    private int taskCount;

    public TaskManager() {
        this.tasks = new ArrayList<>();
        this.taskCount = 0;
    }

    public void addTask(Task task) {
        tasks.add(task);
        taskCount++;
    }

    public void markTaskDone(int index) {
        Task task = tasks.get(index);
        task.markDone();
    }

    public void markTaskUndone(int index) {
        Task task = tasks.get(index);
        task.unmarkDone();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public int getTaskCount() {
        return taskCount;
    }
}
