package tony.task;

import tony.exceptions.TaskAlreadyMarkedException;

import java.util.ArrayList;

public class TaskManager {
    /** The list of tasks managed by this TaskManager. */
    private ArrayList<Task> tasks;

    private int taskCount;

    /**
     * Constructs a new TaskManager with an empty task list.
     */
    public TaskManager() {
        this.tasks = new ArrayList<>();
        this.taskCount = 0;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
        taskCount++;
    }

    /**
     * Marks a task as done based on its index.
     *
     * @param index The index of the task to be marked as done.
     * @return The task that was marked as done.
     * @throws TaskAlreadyMarkedException If the task is already marked as done.
     */
    public Task markTaskDone(int index) {
        Task task = tasks.get(index);
        if (task.isDone()) {
            throw new TaskAlreadyMarkedException();
        }
        task.markDone();
        return task;
    }

    /**
     * Marks a task as not done based on its index.
     *
     * @param index The index of the task to be marked as not done.
     * @return The task that was marked as not done.
     * @throws TaskAlreadyMarkedException If the task is not marked as not done.
     */
    public Task markTaskUndone(int index) {
        Task task = tasks.get(index);
        if (!task.isDone()) {
            throw new TaskAlreadyMarkedException();
        }
        task.unmarkDone();
        return task;
    }

    /**
     * Retrieves the list of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public int getTaskCount() {
        return taskCount;
    }

    /**
     * Removes a task from the task list based on its index.
     *
     * @param index The index of the task to be removed.
     * @return The task that was removed.
     */
    public Task removeTask(int index) {
        Task taskToRemove = tasks.get(index);
        tasks.remove(index);
        taskCount--;
        return taskToRemove;
    }

    /**
     * Clears all tasks from the task list.
     */
    public void clearTasks() {
        tasks.clear();
        taskCount = 0;
    }

    /**
     * Finds tasks that contain the specified keyword in their description.
     *
     * @param keyword The keyword to search for in task descriptions.
     * @return A list of tasks that match the keyword.
     */
    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}
