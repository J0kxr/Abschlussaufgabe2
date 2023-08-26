package edu.kit.informatik.commands;

import edu.kit.informatik.logic.CommandManager;
import edu.kit.informatik.ui.GameManager;

public class HelpCommand extends AHexagonGameCommand{

    private static final  String COMMAND_NAME = "help";
    private static final int VALID_ARGUMENTS_NUMBER = 0;

    /**
     * Constructor for the abstract class which represents a Command.
     *
     * @param commandManager         - current CommandManager of the programm
     * @param gameManger
     */
    public HelpCommand(CommandManager commandManager, GameManager gameManger) {
        super(COMMAND_NAME, commandManager, gameManger, VALID_ARGUMENTS_NUMBER);
    }
}
