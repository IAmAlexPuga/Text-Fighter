package com.hotmail.kalebmarc.textfighter.main;

import com.hotmail.kalebmarc.textfighter.item.*;
import com.hotmail.kalebmarc.textfighter.player.Achievements;
import com.hotmail.kalebmarc.textfighter.player.Potion;
import com.hotmail.kalebmarc.textfighter.player.Settings;
import com.hotmail.kalebmarc.textfighter.player.Stats;
import com.hotmail.kalebmarc.textfighter.main.Game;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

import static com.hotmail.kalebmarc.textfighter.main.Game.battle;
import static com.hotmail.kalebmarc.textfighter.main.Game.home;
import static com.hotmail.kalebmarc.textfighter.player.Health.upgrade;

/*
    Stores the command from user as an int and
    the execution function to be performed.
    Removes the complexity of Game by removing
    all of the switch cases when asking input for user
    More commands can be added.
 */
public class MenuExuction {
    Map<Integer, Command> homeCommands = new HashMap<Integer, Command>();
    Map<Integer, Command> townCommands = new HashMap<Integer, Command>();
    Map<Integer, Command> mainGameCommands = new HashMap<Integer, Command>();

    public MenuExuction(){
        setHomeCommands();
        setTownCommands();
        setMainGameCommands();
    }

    public void executeHomeCommand(int i) {
        if(homeCommands.containsKey(i)) {
            homeCommands.get(i).invoke();
        }
    }

    public void executeTownCommand(int i) {
        if(townCommands.containsKey(i)) {
            townCommands.get(i).invoke();
        }
    }

    public void executeMainGameCommand(Integer i) {
        if(mainGameCommands.containsKey(i)) {
            mainGameCommands.get(i).invoke();
        }
    }

    // Initializes all home commands
    private void setHomeCommands() {
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

    // Initializes all town commands
    private void setTownCommands() {
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

    // Initializes all main game commands
    private void setMainGameCommands() {
        mainGameCommands.put(1, new Command() {
            @Override
            public void invoke() {
                Game.battle();
            }
        });

        mainGameCommands.put(2, new Command() {
            @Override
            public void invoke() {
                Game.home();
            }
        });

        mainGameCommands.put(3, new Command() {
            @Override
            public void invoke() {
                Game.town();
            }
        });

        mainGameCommands.put(4, new Command() {
            @Override
            public void invoke() {
                FirstAid.use();
            }
        });

        mainGameCommands.put(5, new Command() {
            @Override
            public void invoke() {
                Menu.usePotionMenu();
                switch (Ui.getValidInt()) {
                    case 1:
                        Potion.use("survival");
                        break;
                    case 2:
                        Potion.use("recovery");
                        break;
                    case 3:
                        break;
                    default:
                        break;
                }
            }
        });

        mainGameCommands.put(6, new Command() {
            @Override
            public void invoke() {
                Food.list();
            }
        });

        mainGameCommands.put(7, new Command() {
            @Override
            public void invoke() {
                InstaHealth.use();
            }
        });

        mainGameCommands.put(8, new Command() {
            @Override
            public void invoke() {
                Power.use();
            }
        });

        mainGameCommands.put(9, new Command() {
            @Override
            public void invoke() {
                Ui.cls();
                Ui.popup("You ran away from the battle.", "Ran Away", JOptionPane.INFORMATION_MESSAGE);
                Enemy.encounterNew();
            }
        });

        mainGameCommands.put(10, new Command() {
            @Override
            public void invoke() {
                Stats.timesQuit++;
            }
        });

        mainGameCommands.put(0, new Command() {
            @Override
            public void invoke() {
                Cheats.cheatGateway();
            }
        });

        mainGameCommands.put(99, new Command() {
            @Override
            public void invoke() {
                Debug.menu();
            }
        });

    }

}


