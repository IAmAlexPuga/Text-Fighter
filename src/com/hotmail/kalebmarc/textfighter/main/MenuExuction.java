package com.hotmail.kalebmarc.textfighter.main;

import com.hotmail.kalebmarc.textfighter.item.Armour;
import com.hotmail.kalebmarc.textfighter.item.Chest;
import com.hotmail.kalebmarc.textfighter.player.Achievements;
import com.hotmail.kalebmarc.textfighter.player.Settings;
import com.hotmail.kalebmarc.textfighter.player.Stats;

import java.util.HashMap;
import java.util.Map;

public class MenuExuction {
    Map<Integer, Command> commands = new HashMap<Integer, Command>();

    public MenuExuction(){
        setCommands();
    }
    public void setCommands() {
        commands.put(1, new Command() {
            @Override
            public void invoke() {
                Weapon.choose();
            }
        });

        commands.put(2, new Command() {
            @Override
            public void invoke() {
                Armour.choose();
            }
        });

        commands.put(3, new Command() {
            @Override
            public void invoke() {
                Chest.view();
            }
        });
        commands.put(4, new Command() {
            @Override
            public void invoke() {
                Achievements.view();
            }
        });

        commands.put(5, new Command() {
            @Override
            public void invoke() {
                Stats.view();
            }
        });

        commands.put(6, new Command() {
            @Override
            public void invoke() {
                About.view(true);
                Achievements.viewedAbout = true;
            }
        });
        commands.put(7, new Command() {
            @Override
            public void invoke() {
                Stats.view();
            }
        });
        commands.put(8, new Command() {
            @Override
            public void invoke() {
                Settings.menu();
            }
        });
        commands.put(9, new Command() {
            @Override
            public void invoke() {
                return;
            }
        });
    }


}


