package edu.kit.informatik.ui;

public class GameManager {

    private static final int MINIMUM_EXPECTED_NUMBER_OF_ARGUMENTS = 3;
    private static final int MAXIMUM_EXPECTED_NUMBER_OF_ARGUMENTS = 4;
    private final boolean autoPrint;

    public GameManager(String[] commandArguments) {
        if (commandArguments.length == MINIMUM_EXPECTED_NUMBER_OF_ARGUMENTS) {
            this.autoPrint = false;
        } else {

        }
    }
}
