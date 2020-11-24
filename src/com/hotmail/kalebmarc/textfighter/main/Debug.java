package com.hotmail.kalebmarc.textfighter.main;

import com.hotmail.kalebmarc.textfighter.item.FirstAid;
import com.hotmail.kalebmarc.textfighter.item.InstaHealth;
import com.hotmail.kalebmarc.textfighter.item.Power;
import com.hotmail.kalebmarc.textfighter.player.Coins;
import com.hotmail.kalebmarc.textfighter.player.Settings;
import com.hotmail.kalebmarc.textfighter.player.Xp;

class Debug {

    private static boolean enabled = false;

    Debug() {
    }

    public static void enable() {
        Ui.println("Enabling debug menu..");
        enabled = true;
    }

    public static boolean enabled() {
        return enabled;
    }

    public static void menu() {

        //Validate
        if (!enabled()) {
            return;
        }

        while (true) {
            Menu.debugMenu();
            switch (Ui.getValidInt()) {
                case 1:
                    debugCoins();
                    break;
                case 2:
                    debugXp();
                    break;
                case 3:
                    debugWeapon();
                    break;
                case 4:
                    debugFirstAid();
                    break;
                case 5:
                    debugInstaHealth();
                    break;
                case 6:
                    debugNewEncounter();
                    break;
                case 7:
                    debugGodMode();
                    break;
                case 8:
                    debugFood();
                    break;
                case 9:
                    return;
            }
        }
    }

    private static void debugCoins() {
        Ui.cls();
        Ui.println("How much?");
        Coins.set(Ui.getValidInt(), false);
    }

    private static void debugXp() {
        Ui.cls();
        Ui.println("How much?");
        Xp.set(Ui.getValidInt(), false);
    }

    private static void debugWeapon() {
        for (int i = 0; i < WeaponInventory.arrayWeapon.size(); i++) {
            WeaponInventory.arrayWeapon.get(i).owns = true;
        }
        Power.set(100, true);
        for (int i = 0; i < WeaponInventory.arrayWeapon.size(); i++) {
            WeaponInventory.arrayWeapon.get(i).setAmmo(10000, false);
        }
        Ui.println("You now have all weapons");
        Ui.pause();
    }

    private static void debugFirstAid() {
        Ui.cls();
        Ui.println("How much?");
        FirstAid.set(Ui.getValidInt(), false);
    }

    private static void debugInstaHealth() {
        Ui.cls();
        Ui.println("How much?");
        InstaHealth.set(Ui.getValidInt(), false);
    }

    private static void debugNewEncounter() {
        Enemy.encounterNew();
    }

    private static void debugGodMode() {
        Settings.toggleGodMode();
    }

    private static void debugFood() {
        Ui.cls();
        for (int i = 0; i < Food.arrayFood.size(); i++) {
            Ui.println(i + ") " + Food.arrayFood.get(i).getName());
        }
        Food.arrayFood.get(Ui.getValidInt()).setQuantity(10);
    }
}
