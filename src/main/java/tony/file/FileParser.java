package tony.file;

import tony.command.Command;
import tony.command.CommandType;

public class FileParser {
    public Command parseFile(String fileLine) {
        String[] words = fileLine.split("\\|");
        String commandWord = words[0];

        switch (commandWord) {
        case "todo":
            return new Command(CommandType.ADD_TODO, words[2]);
        case "deadline":
            return new Command(CommandType.ADD_DEADLINE, words[2], words[3]);
        case "event":
            return new Command(CommandType.ADD_EVENT, words[2], words[3], words[4]);
        default:
            return new Command(CommandType.UNKNOWN);
        }
    }
}
