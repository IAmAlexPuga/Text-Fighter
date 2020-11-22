package com.hotmail.kalebmarc.textfighter.main;

import com.hotmail.kalebmarc.textfighter.item.Armour;
import com.hotmail.kalebmarc.textfighter.item.Chest;
import com.hotmail.kalebmarc.textfighter.player.Achievements;
import com.hotmail.kalebmarc.textfighter.player.Settings;
import com.hotmail.kalebmarc.textfighter.player.Stats;

import java.util.HashMap;
import java.util.Map;

public class HomeCommands implements MenuInvoker {
    Map<Integer, Command> homeCommands = new HashMap<Integer, Command>();

    public HomeCommands() {
        Map<Integer, Command> homeCommands = new HashMap<Integer, Command>();
        setup();
    }

    @Override
    public void setup() {
        homeCommands.put(1, new Command() {
            @Override
            public void invoke() {
                Weapon.choose();
            }
        });

        homeCommands.put(2, new Command() {
            @Override
            public void invoke() {
                Armour.choose();
            }
        });

        homeCommands.put(3, new Command() {
            @Override
            public void invoke() {
                Chest.view();
            }
        });
        homeCommands.put(4, new Command() {
            @Override
            public void invoke() {
                Achievements.view();
            }
        });

        homeCommands.put(5, new Command() {
            @Override
            public void invoke() {
                Stats.view();
            }
        });

        homeCommands.put(6, new Command() {
            @Override
            public void invoke() {
                About.view(true);
                Achievements.viewedAbout = true;
            }
        });
        homeCommands.put(7, new Command() {
            @Override
            public void invoke() {
                Stats.view();
            }
        });
        homeCommands.put(8, new Command() {
            @Override
            public void invoke() {
                Settings.menu();
            }
        });
        homeCommands.put(9, new Command() {
            @Override
            public void invoke() {
                return;
            }
        });
    }

    @Override
    public void execute(int choice) {
        if(homeCommands.containsKey(choice)) {
            homeCommands.get(choice).invoke();
        }
    }
}
