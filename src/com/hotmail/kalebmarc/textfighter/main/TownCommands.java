package com.hotmail.kalebmarc.textfighter.main;

import java.util.HashMap;
import java.util.Map;

import static com.hotmail.kalebmarc.textfighter.main.Game.home;
import static com.hotmail.kalebmarc.textfighter.player.Health.upgrade;

public class TownCommands implements MenuInvoker{

    Map<Integer, Command> townCommands;

    /*
    Stores the command selection from user as an int and
    the execution function to be performed for Town Commands.
    Removes the complexity of Game by removing
    all of the switch cases when asking input for user
    More commands can be added.
    */
    public TownCommands() {
        townCommands = new HashMap<Integer, Command>();
        setup();
    }

    /*
    Setups the commands
     */
    @Override
    public void setup() {
        townCommands.put(1, new Command() {
            @Override
            public void invoke() {
                Casino.menu();
            }
        });
        townCommands.put(2, new Command() {
            @Override
            public void invoke() {
                home();
            }
        });
        townCommands.put(3, new Command() {
            @Override
            public void invoke() {
                Bank.menu();
            }
        });
        townCommands.put(4, new Command() {
            @Override
            public void invoke() {
                Shop.menu();
            }
        });
        townCommands.put(5, new Command() {
            @Override
            public void invoke() {
                upgrade();
            }
        });
    }

    /*
    Executes a requested choice
    */
    @Override
    public void execute(int choice) {
        if(townCommands.containsKey(choice)) {
            townCommands.get(choice).invoke();
        }
    }
}
