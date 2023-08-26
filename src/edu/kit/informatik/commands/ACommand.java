package edu.kit.informatik.commands;

import edu.kit.informatik.logic.CommandManager;
import edu.kit.informatik.ui.GameManager;
import java.util.Objects;

/**
 * Abstract Class which is united the common elements of a command-class.
 * @author uqkeq
 * @author Programmieren-Team
 * @version 1.0
 */
public abstract class ACommand {

    protected final GameManager commandManager;
    private final String commandName;

    /**
     * Constructor for the abstract class which represents a Command.
     * @param commandName - given command Name of the specified command
     * @param commandManager - current CommandManager of the programm
     */
    protected ACommand(String commandName, CommandManager commandManager) {
        this.commandName = Objects.requireNonNull(commandName);
        this.commandManager = Objects.requireNonNull(commandManager);
    }

    /**
     * Getter for the attributed of the commandName.
     * @return - string of the specified commandName
     */
    public String getCommandName() {
        return this.commandName;
    }

    /**
     * Execute Methode which executes every command, catches the exceptions while executing the command and printing the
     * result of the specified command.
     * @param commandArguments gets the arguments of the Command without the command-name
     */
    public abstract void executeCommand(String[] commandArguments);
}
