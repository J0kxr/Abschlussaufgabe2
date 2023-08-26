package edu.kit.informatik.commands;

import edu.kit.informatik.logic.CommandManager;
import edu.kit.informatik.ui.GameManager;

public class AHexagonGameCommand extends ACommand {

    private final int EXPECTED_NUMBER_OF_ARGUMENTS;
    protected final GameManager gameManager;

    /**
     * Constructor for the abstract class which represents a Command.
     *
     * @param commandName    - given command Name of the specified command
     * @param commandManager - current CommandManager of the programm
     */
    protected AHexagonGameCommand(String commandName, CommandManager commandManager, GameManager gameManger,
                                  int validNumberOfArguments) {
        super(commandName, commandManager);
        EXPECTED_NUMBER_OF_ARGUMENTS = validNumberOfArguments;
        this.gameManager = gameManger;
    }

    @Override
    public void executeCommand(String[] commandArguments) {

    }
}
