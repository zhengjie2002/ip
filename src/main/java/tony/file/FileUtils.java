package tony.file;

import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

import tony.task.Deadline;
import tony.task.Event;
import tony.task.Task;
import tony.task.TaskManager;
import tony.command.Command;
import tony.task.Todo;

public class FileUtils {
    private String filePath;

    public FileUtils(String filePath) {
        this.filePath = filePath;
    }

    private static void addTask(Command command, TaskManager taskManager) {
        switch (command.getType()) {
        case ADD_TODO:
            Task newTask = new Todo(command.getDescription());
            taskManager.addTask(newTask);
            if (command.isDone()) {
                newTask.markDone();
            }
            break;
        case ADD_DEADLINE:
            Task newDeadlineTask = new Deadline(command.getDescription(), command.getBy());
            taskManager.addTask(newDeadlineTask);
            if (command.isDone()) {
                newDeadlineTask.markDone();
            }
            break;
        case ADD_EVENT:
            Task newEventTask = new Event(command.getDescription(), command.getFrom(), command.getTo());
            taskManager.addTask(newEventTask);
            if (command.isDone()) {
                newEventTask.markDone();
            }
            break;
        }
    }

    public void loadFileContent(TaskManager taskManager) throws IOException {
        File f = new File(filePath);
        FileParser fileParser = new FileParser();

        if (!f.exists()) {
            f.getParentFile().mkdirs();
            f.createNewFile();
            return;
        }
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String line = s.nextLine();
            Command command = fileParser.parseFile(line);
            addTask(command, taskManager);
        }
        s.close();
    }

    public void appendToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAdd);
        fw.write(System.lineSeparator());
        fw.close();
    }
}
