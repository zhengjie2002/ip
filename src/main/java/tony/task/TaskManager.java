package tony.task;

public class TaskManager {
    private Task[] tasks;
    private int taskCount;

    public TaskManager(int MAX_TASKS) {
        this.tasks = new Task[MAX_TASKS];
        this.taskCount = 0;
    }

    public void addTask(Task task) {
        tasks[taskCount] = task;
        taskCount++;
    }

    public void markTaskDone(int index) {
        tasks[index].markDone();
    }

    public void markTaskUndone(int index) {
        tasks[index].unmarkDone();
    }

    public Task[] getTasks() {
        return tasks;
    }

    public int getTaskCount() {
        return taskCount;
    }
}
