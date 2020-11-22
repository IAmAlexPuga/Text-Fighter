package com.hotmail.kalebmarc.textfighter.main;

import com.hotmail.kalebmarc.textfighter.item.FirstAid;
import com.hotmail.kalebmarc.textfighter.item.InstaHealth;
import com.hotmail.kalebmarc.textfighter.item.Power;
import com.hotmail.kalebmarc.textfighter.player.Potion;
import com.hotmail.kalebmarc.textfighter.player.Stats;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class MainGameCommands implements MenuInvoker{
    Map<Integer, Command> mainGameCommands;

    public MainGameCommands() {
        mainGameCommands = new HashMap<Integer, Command>();
        setup();
    }

    @Override
    public void setup() {
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


    @Override
    public void execute(int choice) {
        if(mainGameCommands.containsKey(choice)) {
            mainGameCommands.get(choice).invoke();
        }
    }
}
