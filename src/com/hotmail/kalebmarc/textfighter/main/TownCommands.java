package com.hotmail.kalebmarc.textfighter.main;

import java.util.HashMap;
import java.util.Map;

import static com.hotmail.kalebmarc.textfighter.main.Game.home;
import static com.hotmail.kalebmarc.textfighter.player.Health.upgrade;

public class TownCommands implements MenuInvoker{

    Map<Integer, Command> townCommands;

    public TownCommands() {
        townCommands = new HashMap<Integer, Command>();
        setup();
    }

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

    @Override
    public void execute(int choice) {
        if(townCommands.containsKey(choice)) {
            townCommands.get(choice).invoke();
        }
    }
}
