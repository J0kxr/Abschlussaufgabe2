package edu.kit.informatik.commands;

import edu.kit.informatik.logic.CommandManager;
import edu.kit.informatik.model.TaskManager;
import edu.kit.informatik.ui.GameManager;

/**
 * Class which represents the Quit command as itself.
 * @author uqkeq
 * @author Programmieren-Team
 * @version 1.0
 *
 */
public class QuitCommand extends ACommand {

    private static final String COMMAND_NAME = "quit";
    private static final String QUIT_COMMAND_ERROR_MESSAGE = "ERROR: Quit requires no command Arguments%n";
    private static final int QUIT_COMMAND_ARGUMENT_LENGTH = 0;


    /**
     * QuitCommand Constructor which initializes the Command-Name and the expected Number of Arguments.
     * @param commandManager - this current commandManager
     * @param taskManager - this current taskManager
     */
    public QuitCommand(CommandManager commandManager, GameManager taskManager) {
        super(COMMAND_NAME, commandManager);
    }

    @Override
    public void executeCommand(String[] commandArguments) {
        if (commandArguments.length != QUIT_COMMAND_ARGUMENT_LENGTH) {
            System.err.printf(QUIT_COMMAND_ERROR_MESSAGE);
        } else {
            this.commandManager.quit();
        }
    }

}
