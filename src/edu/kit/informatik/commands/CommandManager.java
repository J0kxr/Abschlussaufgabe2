package edu.kit.informatik.logic;

import edu.kit.informatik.commands.ACommand;
import edu.kit.informatik.commands.HelpCommand;
import edu.kit.informatik.commands.HistoryCommand;
import edu.kit.informatik.commands.ListGamesCommand;
import edu.kit.informatik.commands.NewGameCommand;
import edu.kit.informatik.commands.PlaceCommand;
import edu.kit.informatik.commands.PrintCommand;
import edu.kit.informatik.commands.SwapCommand;
import edu.kit.informatik.commands.SwitchGamesCommand;
import edu.kit.informatik.commands.printcommands.daterelatedoutput.BeforeCommand;
import edu.kit.informatik.commands.printcommands.daterelatedoutput.BetweenCommand;
import edu.kit.informatik.commands.printcommands.daterelatedoutput.UpcomingCommand;
import edu.kit.informatik.commands.printcommands.daterelatedoutput.DuplicateCommand;
import edu.kit.informatik.commands.modifycommands.ChangeDateCommand;
import edu.kit.informatik.commands.modifycommands.ChangePriorityCommand;
import edu.kit.informatik.commands.QuitCommand;
import edu.kit.informatik.commands.modifycommands.AssignCommand;
import edu.kit.informatik.commands.modifycommands.list.AddListCommand;
import edu.kit.informatik.commands.modifycommands.task.AddCommand;
import edu.kit.informatik.commands.modifycommands.TagCommand;
import edu.kit.informatik.commands.printcommands.FindCommand;
import edu.kit.informatik.commands.printcommands.ListCommand;
import edu.kit.informatik.commands.printcommands.ShowCommand;
import edu.kit.informatik.commands.printcommands.TaggedWithCommand;
import edu.kit.informatik.commands.printcommands.TodoCommand;
import edu.kit.informatik.commands.statechangecommands.DeleteCommand;
import edu.kit.informatik.commands.statechangecommands.RestoreCommand;
import edu.kit.informatik.commands.statechangecommands.ToggleCommand;

import edu.kit.informatik.model.TaskManager;
import edu.kit.informatik.ui.GameManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Class to receive userInput and running the programm.
 * @author uqkeq
 * @author Programmieren-Team
 * @version 1.0
 */
public class CommandManager {
    private static final String INVALID_COMMAND_NAME_ERROR_MESSAGE = "ERROR: Your called command is invalid!%n";
    private static final String COMMAND_SEPARATOR = " ";
    private static final int COMMAND_NAME_INDEX = 0;
    private static final int FIRST_COMMAND_ARGUMENT_INDEX = 1;
    private boolean procrastinotIsRunning;
    private final Scanner scanner;
    private String currentCommand;
    private final GameManager gameManager;
    private final Map<String, ACommand> commands = new HashMap<>();

    /**
     * Constructor of the CommandManager.
     * @param gameManager - currentTaskMangerObject
     */
    public CommandManager(GameManager gameManager) {
        this.procrastinotIsRunning = true;
        this.gameManager = gameManager;
        this.scanner = new Scanner(System.in);
        initializeCommands();
        run();
    }

    /**
     * Methode which runs the programm and reads the lines.
     */
    public void run() {
        while (this.procrastinotIsRunning && this.scanner.hasNextLine()) {
            this.currentCommand = scanner.nextLine();

            analyseCommand(this.currentCommand);
        }
        this.quit();
    }

    private void analyseCommand(String currentCommand) {
        if (!currentCommand.isEmpty()) {
            String[] commandParts = trimCommandArguments(currentCommand);
            this.currentCommand = commandParts[COMMAND_NAME_INDEX];

            if (this.commands.containsKey(this.currentCommand)) {
                executeCommand(commandParts);
            } else {
                System.err.printf(INVALID_COMMAND_NAME_ERROR_MESSAGE);
            }
        } else {
            System.err.printf(INVALID_COMMAND_NAME_ERROR_MESSAGE);
        }
    }

    private String[] trimCommandArguments(String currentCommand) {
        String[] splitCommand = currentCommand.split(COMMAND_SEPARATOR);
        List<String> validCommand = new ArrayList<>();
        for (String commandArgument : splitCommand) {
            if (!commandArgument.isEmpty()) {
                validCommand.add(commandArgument);
            }
        }
        return validCommand.toArray(new String[0]);
    }

    private void executeCommand(String[] commandParts) {
        // new Array with command Arguments without the command name
        String[] commandArguments = new String[commandParts.length - 1];
        System.arraycopy(commandParts, FIRST_COMMAND_ARGUMENT_INDEX, commandArguments, 0,
            commandParts.length - 1);

        this.commands.get(this.currentCommand).executeCommand(commandArguments);
    }

    /**
     * Methode to represent the quit Methode which terminates the programm.
     */
    public void quit() {
        this.procrastinotIsRunning = false;
        this.scanner.close();
    }

    private void initializeCommands() {
        this.addNewCommand(new HelpCommand(this, gameManager));
        this.addNewCommand(new HistoryCommand(this, gameManager));
        this.addNewCommand(new ListGamesCommand(this, gameManager));
        this.addNewCommand(new NewGameCommand(this, gameManager));
        this.addNewCommand(new PlaceCommand(this, gameManager));
        this.addNewCommand(new PrintCommand(this, gameManager));
        this.addNewCommand(new SwapCommand(this, gameManager));
        this.addNewCommand(new SwitchGamesCommand(this, gameManager));
        this.addNewCommand(new QuitCommand(this, gameManager));
    }

    private void initializeFindCommands() {
        this.addNewCommand(new UpcomingCommand(this, gameManager));
        this.addNewCommand(new DuplicateCommand(this, gameManager));
        this.addNewCommand(new BetweenCommand(this, gameManager));
        this.addNewCommand(new BeforeCommand(this, gameManager));
    }

    private void initializeModificationCommands() {
        this.addNewCommand(new AddCommand(this, gameManager));
        this.addNewCommand(new AddListCommand(this, gameManager));
        this.addNewCommand(new AssignCommand(this, gameManager));
        this.addNewCommand(new ChangeDateCommand(this, gameManager));
        this.addNewCommand(new ChangePriorityCommand(this, gameManager));
        this.addNewCommand(new TagCommand(this, gameManager));
    }

    private void initializeSortCommands() {
        this.addNewCommand(new FindCommand(this, gameManager));
        this.addNewCommand(new ListCommand(this, gameManager));
        this.addNewCommand(new ShowCommand(this, gameManager));
        this.addNewCommand(new TodoCommand(this, gameManager));
        this.addNewCommand(new TaggedWithCommand(this, gameManager));
    }

    private void initializeStateChangingCommands() {
        this.addNewCommand(new DeleteCommand(this, gameManager));
        this.addNewCommand(new RestoreCommand(this, gameManager));
        this.addNewCommand(new ToggleCommand(this, gameManager));
    }
    private void addNewCommand(ACommand newCommand) {
        this.commands.put(newCommand.getCommandName(), newCommand);
    }
}
