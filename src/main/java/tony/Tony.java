package tony;

import tony.command.Command;
import tony.command.ExitCommand;
import tony.command.Parser;

import tony.exceptions.EventEndBeforeStartException;
import tony.exceptions.InvalidDateFormatException;
import tony.exceptions.InvalidEventFormatException;
import tony.exceptions.NoDeadlineException;
import tony.exceptions.NoDescriptionException;
import tony.exceptions.NoEventStartException;
import tony.exceptions.NoEventEndException;
import tony.exceptions.NoSearchKeywordException;
import tony.task.TaskManager;

import tony.ui.Ui;

import tony.file.DataManager;

import java.util.Scanner;

public class Tony {

    private static final String FILE_PATH = "./data/tony.txt";

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        Scanner in = new Scanner(System.in);
        Ui ui = new Ui();
        DataManager dataManager = new DataManager(FILE_PATH);

        dataManager.loadData(taskManager);

        ui.printWelcome();

        while (true) {
            String userInput = ui.readCommand(in);
            Command command;
            try {
                command = Parser.parseCommand(userInput);
            } catch (NoDescriptionException e) {
                ui.printMissingDescriptionError();
                continue;
            } catch (NoDeadlineException e) {
                ui.printMissingDeadlineError();
                continue;
            } catch (NoEventStartException e) {
                ui.printMissingStartTimeError();
                continue;
            } catch (NoEventEndException e) {
                ui.printMissingEndTimeError();
                continue;
            } catch (NumberFormatException e) {
                ui.printInvalidTaskError();
                continue;
            } catch (NoSearchKeywordException e) {
                ui.printMissingSearchKeywordError();
                continue;
            } catch (InvalidDateFormatException e) {
                ui.printInvalidDateFormatError();
                continue;
            } catch (EventEndBeforeStartException e) {
                ui.printEventEndBeforeStartError();
                continue;
            } catch (InvalidEventFormatException e) {
                ui.printInvalidEventFormatError();
                continue;
            } catch (Exception e) {
                ui.printError(e.getMessage());
                continue;
            }
            if (command instanceof ExitCommand) {
                command.execute(taskManager, ui, dataManager);
                break;
            }
            command.execute(taskManager, ui, dataManager);
        }
    }
}