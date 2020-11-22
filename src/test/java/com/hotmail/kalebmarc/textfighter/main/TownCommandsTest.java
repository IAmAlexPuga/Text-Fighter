package com.hotmail.kalebmarc.textfighter.main;

import org.junit.Test;

import static org.junit.Assert.*;

public class TownCommandsTest {

    @Test
    public void setupTest() {
        TownCommands commands = new TownCommands();

        assertTrue(commands.townCommands.isEmpty());

        // Sets up the commands
        commands.setup();
        // Checks the commands are no longer null
        assertNotNull(commands.townCommands);
        // Checks the size is greater than 2
        assertTrue(commands.townCommands.size() > 1);
    }

}