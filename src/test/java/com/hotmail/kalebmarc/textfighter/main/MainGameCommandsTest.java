package com.hotmail.kalebmarc.textfighter.main;

import org.junit.Test;

import static org.junit.Assert.*;

public class MainGameCommandsTest {

    @Test
    public void setupTest() {
        MainGameCommands commands = new MainGameCommands();
        assertTrue(commands.mainGameCommands.isEmpty());

        // Sets up the commands
        commands.setup();
        // Checks the commands are no longer null
        assertNotNull(commands.mainGameCommands);
        // Checks the size is greater than 2
        assertTrue(commands.mainGameCommands.size() > 1);
    }

}