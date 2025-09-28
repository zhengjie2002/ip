package tony.command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import tony.exceptions.EventEndBeforeStartException;
import tony.exceptions.InvalidDateFormatException;
import tony.exceptions.InvalidEventFormatException;
import tony.exceptions.NoDeadlineException;
import tony.exceptions.NoDescriptionException;
import tony.exceptions.NoEventStartException;
import tony.exceptions.NoEventEndException;
import tony.exceptions.NoSearchKeywordException;

/**
 * Parses user input and converts it into executable commands.
 * The <code>Parser</code> class provides methods to interpret user input strings
 * and generate corresponding command objects.
 */
public class Parser {
    /** The date format expected for input dates. */
    private static final String INPUT_DATE_FORMAT = "dd-MM-yyyy";

    /**
     * Converts a string representation of a date to a LocalDate object.
     *
     * @param unformattedDateString The string representation of the date.
     * @return The parsed LocalDate object.
     * @throws InvalidDateFormatException If the date string cannot be parsed.
     */
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

    /**
     * Parses an event command from the user input.
     *
     * @param arguments A string of text that user input after the word event.
     * @return The parsed AddEventCommand object.
     * @throws NoEventStartException        If the start date is missing.
     * @throws NoEventEndException          If the end date is missing.
     * @throws InvalidEventFormatException  If the event format is invalid.
     * @throws NoDescriptionException       If the event description is missing.
     * @throws EventEndBeforeStartException If the end date is before the start date.
     */
    private static Command parseEventCommand(String arguments) {
        int indexOfFrom = arguments.indexOf("/from");
        if (indexOfFrom == -1) {
            throw new NoEventStartException();
        }

        int indexOfTo = arguments.indexOf("/to");
        if (indexOfTo == -1) {
            throw new NoEventEndException();
        }

        if (indexOfTo < indexOfFrom) {
            throw new InvalidEventFormatException();
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

        if (toDate.isBefore(fromDate)) {
            throw new EventEndBeforeStartException();
        }

        return new AddEventCommand(description, fromDate, toDate, false);
    }

    /**
     * Parses a deadline command from the user input.
     *
     * @param arguments A string of text that user input after the word deadline.
     * @return The parsed AddDeadlineCommand object.
     * @throws NoDeadlineException    If the deadline date is missing.
     * @throws NoDescriptionException If the deadline description is missing.
     */
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

    /**
     * Parses a to-do command from the user input.
     *
     * @param arguments A string of text that user input after the word todo.
     * @return The parsed AddToDoCommand object.
     * @throws NoDescriptionException If the to-do description is missing.
     */
    private static Command parseTodoCommand(String arguments) {
        if (arguments.isEmpty()) {
            throw new NoDescriptionException();
        }
        return new AddToDoCommand(arguments, false);
    }

    /**
     * Parses an unmark command from the user input.
     *
     * @param arguments The string that user input after the word unmark.
     * @return The parsed UnmarkCommand object.
     * @throws NumberFormatException If the task index is not a valid number.
     */
    private static Command parseUnmarkCommand(String arguments) {
        int taskIndex;
        try {
            taskIndex = Integer.parseInt(arguments.trim()) - 1;
        } catch (NumberFormatException e) {
            throw new NumberFormatException();
        }
        return new UnmarkCommand(taskIndex);
    }

    /**
     * Parses a mark command from the user input.
     *
     * @param arguments The string that user input after the word mark.
     * @return The parsed MarkCommand object.
     * @throws NumberFormatException If the task index is not a valid number.
     */
    private static Command parseMarkCommand(String arguments) {
        int taskIndex;
        try {
            taskIndex = Integer.parseInt(arguments.trim()) - 1;
        } catch (NumberFormatException e) {
            throw new NumberFormatException();
        }
        return new MarkCommand(taskIndex);
    }

    /**
     * Parses a delete command from the user input.
     *
     * @param arguments The string that user input after the word delete.
     * @return The parsed DeleteCommand object.
     * @throws NumberFormatException If the task index is not a valid number.
     */
    private static Command parseDeleteCommand(String arguments) {
        int taskIndex;
        try {
            taskIndex = Integer.parseInt(arguments.trim()) - 1;
        } catch (NumberFormatException e) {
            throw new NumberFormatException();
        }
        return new DeleteCommand(taskIndex);
    }

    /**
     * Parses a find command from the user input.
     *
     * @param arguments The string that user input after the word find.
     * @return The parsed FindCommand object.
     * @throws NoSearchKeywordException If the search keyword is missing.
     */
    private static Command parseFindCommand(String arguments) {
        if (arguments.isEmpty()) {
            throw new NoSearchKeywordException();
        }
        return new FindCommand(arguments);
    }

    /**
     * Parses the user input and determines the appropriate command to execute.
     *
     * @param userInput The full user input string.
     * @return The parsed Command object.
     */
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