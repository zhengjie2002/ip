package tony.command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import tony.exceptions.InvalidDateFormatException;
import tony.exceptions.NoDeadlineException;
import tony.exceptions.NoDescriptionException;
import tony.exceptions.NoEventStartException;
import tony.exceptions.NoEventEndException;
import tony.exceptions.NoSearchKeywordException;

public class Parser {
    private static final String INPUT_DATE_FORMAT = "dd-MM-yyyy";

    private static LocalDate convertStringToLocalDate(String unformattedDateString) {
        LocalDate formattedDate = null;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(INPUT_DATE_FORMAT);
            formattedDate = LocalDate.parse(unformattedDateString, formatter);
        } catch (DateTimeParseException e) {
            throw new InvalidDateFormatException();
        }
        return formattedDate;
    }

    private static Command parseEventCommand(String arguments) {
        int indexOfFrom = arguments.indexOf("/from");
        int indexOfTo = arguments.indexOf("/to");
        if (indexOfFrom == -1) {
            throw new NoEventStartException();
        }
        if (indexOfTo == -1) {
            throw new NoEventEndException();
        }

        String description = arguments.substring(0, indexOfFrom).trim();
        if (description.isEmpty()) {
            throw new NoDescriptionException();
        }

        String from = arguments.substring(indexOfFrom + 5, indexOfTo).trim();
        if (from.isEmpty()) {
            throw new NoEventStartException();
        }
        LocalDate fromDate = convertStringToLocalDate(from);

        String to = arguments.substring(indexOfTo + 3).trim();
        if (to.isEmpty()) {
            throw new NoEventEndException();
        }
        LocalDate toDate = convertStringToLocalDate(to);

        return new AddEventCommand(description, fromDate, toDate, false);
    }

    private static Command parseDeadlineCommand(String arguments) {
        int indexOfBy = arguments.indexOf("/by");
        if (indexOfBy == -1) {
            throw new NoDeadlineException();
        }

        String description = arguments.substring(0, indexOfBy).trim();
        if (description.isEmpty()) {
            throw new NoDescriptionException();
        }

        String by = arguments.substring(indexOfBy + 3).trim();
        if (by.isEmpty()) {
            throw new NoDeadlineException();
        }
        LocalDate deadlineDate = convertStringToLocalDate(by);

        return new AddDeadlineCommand(description, deadlineDate, false);
    }

    private static Command parseTodoCommand(String arguments) {
        if (arguments.isEmpty()) {
            throw new NoDescriptionException();
        }
        return new AddToDoCommand(arguments, false);
    }

    private static Command parseUnmarkCommand(String arguments) {
        // Convert to a zero-based index in integer for us to use
        int taskIndex;
        try {
            taskIndex = Integer.parseInt(arguments.trim()) - 1;
        } catch (NumberFormatException e) {
            throw new NumberFormatException();
        }
        return new UnmarkCommand(taskIndex);
    }

    private static Command parseMarkCommand(String arguments) {
        // Convert to a zero-based index in integer for us to use
        int taskIndex;
        try {
            taskIndex = Integer.parseInt(arguments.trim()) - 1;
        } catch (NumberFormatException e) {
            throw new NumberFormatException();
        }
        return new MarkCommand(taskIndex);
    }


    private static Command parseDeleteCommand(String arguments) {
        // Convert to a zero-based index in integer for us to use
        int taskIndex;
        try {
            taskIndex = Integer.parseInt(arguments.trim()) - 1;
        } catch (NumberFormatException e) {
            throw new NumberFormatException();
        }
        return new DeleteCommand(taskIndex);
    }

    private static Command parseFindCommand(String arguments) {
        if (arguments.isEmpty()) {
            throw new NoSearchKeywordException();
        }
        return new FindCommand(arguments);
    }

    public static Command parseCommand(String userInput) {
        String[] words = userInput.split(" ", 2);
        String commandWord = words[0];
        String arguments = words.length > 1 ? words[1] : "";

        switch (commandWord) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "todo":
            return parseTodoCommand(arguments.trim());
        case "deadline":
            return parseDeadlineCommand(arguments.trim());
        case "event":
            return parseEventCommand(arguments.trim());
        case "mark":
            return parseMarkCommand(arguments);
        case "unmark":
            return parseUnmarkCommand(arguments);
        case "delete":
            return parseDeleteCommand(arguments);
        case "find":
            return parseFindCommand(arguments.trim());
        default:
            return new UnknownCommand();
        }
    }
}
