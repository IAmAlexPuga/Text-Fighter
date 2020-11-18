package com.hotmail.kalebmarc.textfighter.main;

import com.hotmail.kalebmarc.textfighter.item.Armour;
import com.hotmail.kalebmarc.textfighter.item.Chest;
import com.hotmail.kalebmarc.textfighter.player.Achievements;
import com.hotmail.kalebmarc.textfighter.player.Settings;
import com.hotmail.kalebmarc.textfighter.player.Stats;

import java.util.HashMap;
import java.util.Map;

import static com.hotmail.kalebmarc.textfighter.player.Health.upgrade;

public class MenuExuction {
    Map<Integer, Command> homeCommands = new HashMap<Integer, Command>();
    Map<Integer, Command> townCommands = new HashMap<Integer, Command>();
    public MenuExuction(){
        setHomeCommands();
        setTownCommands();
    }

    public void setHomeCommands() {
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

    public void setTownCommands() {
        townCommands.put(1, new Command() {
            @Override
            public void invoke() {
                Casino.menu();
            }
        });
        townCommands.put(2, new Command() {
            @Override
            public void invoke() {
                Game.home();
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


}


