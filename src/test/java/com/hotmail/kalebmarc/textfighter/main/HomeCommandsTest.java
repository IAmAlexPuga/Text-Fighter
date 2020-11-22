package com.hotmail.kalebmarc.textfighter.main;

import org.junit.Test;

import static org.junit.Assert.*;

public class HomeCommandsTest {

    @Test
    public void setupTest() {
        HomeCommands commands = new HomeCommands();
        assertTrue(commands.homeCommands.isEmpty());

        // Sets up the commands
        commands.setup();
        // Checks the commands are no longer null
        assertNotNull(commands.homeCommands);
        // Checks the size is greater than 2
        assertTrue(commands.homeCommands.size() > 1);
    }

}