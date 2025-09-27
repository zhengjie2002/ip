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

    public Task markTaskDone(int index) {
        Task task = tasks.get(index);
        task.markDone();
        return task;
    }

    public Task markTaskUndone(int index) {
        Task task = tasks.get(index);
        task.unmarkDone();
        return task;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public int getTaskCount() {
        return taskCount;
    }

    public Task removeTask(int index) {
        Task taskToRemove = tasks.get(index);
        tasks.remove(index);
        taskCount--;
        return taskToRemove;
    }

    public void clearTasks() {
        tasks.clear();
        taskCount = 0;
    }
}
