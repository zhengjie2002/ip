public class Parser {
    private static Command parseEventCommand(String arguments) {
        int indexOfFrom = arguments.indexOf("/from");
        int indexOfTo = arguments.indexOf("/to");
        String description = arguments.substring(0, indexOfFrom).trim();
        String from = arguments.substring(indexOfFrom + 5, indexOfTo).trim();
        String to = arguments.substring(indexOfTo + 3).trim();
        return new Command(CommandType.ADD_EVENT, description, from, to);
    }

    private static Command parseDeadlineCommand(String arguments) {
        int indexOfBy = arguments.indexOf("/by");
        String description = arguments.substring(0, indexOfBy).trim();
        String by = arguments.substring(indexOfBy + 3).trim();
        return new Command(CommandType.ADD_DEADLINE, description, by);
    }

    private static Command parseMarkUnmarkCommand(CommandType type, String arguments) {
        // Convert to a zero-based index in integer for us to use
        int taskIndex = Integer.parseInt(arguments.trim()) - 1;
        return new Command(type, taskIndex);
    }

    public static Command parseCommand(String userInput) {
        String[] words = userInput.split(" ", 2);
        String commandWord = words[0];
        String arguments = words.length > 1 ? words[1] : "";

        switch (commandWord) {
        case "bye":
            return new Command(CommandType.EXIT);
        case "list":
            return new Command(CommandType.LIST);
        case "todo":
            return new Command(CommandType.ADD_TODO, arguments);
        case "deadline":
            return parseDeadlineCommand(arguments);
        case "event":
            return parseEventCommand(arguments);
        case "mark":
            return parseMarkUnmarkCommand(CommandType.MARK, arguments);
        case "unmark":
            return parseMarkUnmarkCommand(CommandType.UNMARK, arguments);
        default:
            return new Command(CommandType.UNKNOWN);
        }

    }
}
