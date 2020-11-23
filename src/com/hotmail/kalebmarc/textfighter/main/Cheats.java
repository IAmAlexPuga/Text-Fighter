package com.hotmail.kalebmarc.textfighter.main;

import com.hotmail.kalebmarc.textfighter.item.FirstAid;
import com.hotmail.kalebmarc.textfighter.item.InstaHealth;
import com.hotmail.kalebmarc.textfighter.item.Power;
import com.hotmail.kalebmarc.textfighter.player.*;

import java.util.Scanner;

public class Cheats {

    //Variables
    private static Scanner cheat = new Scanner(System.in);
    private static boolean enabled = false;
    private static boolean locked = false;

    public static void cheatGateway() {

        //Makes sure cheats aren't locked
        if (locked()) {
            Ui.msg("Cheats are locked off- You cannot use cheats!");
            return;
        }

        if (!enabled()) {
            int confirm = Ui.confirmPopup("If you enable cheats, achievements and xp will be disabled. Are you sure you want to continue?", "Warning");
            if (confirm == 0) {
                Xp.setAll(0, 0, 10);
                enable();
            }
        }
        if (enabled()) Cheats.cheatSelect();
    }

    private static void cheatSelect() {
        Ui.println("*");

        switch (cheat.nextLine()) {
            case "moneylover":
                moneylover();
                break;
            case "givemeitall":
                givemeitall();
                break;
            case "weaponstash":
                weaponstash();
                break;
            case "nomorepain":
                nomorepain();
                break;
            case "healme":
                healme();
                break;
            case "givemeachallenge":
                givemeachallenge();
                break;
            case "lotsofkills":
                lotsofkills();
                break;
            case "suicide":
                suicide();
                break;
            case "godmode":
                godmode();
                break;
            case "loanshark":
                loanshark();
                break;
            case "thirstforfood":
                thirstforfood();
                break;

        }
    }

    public static boolean enabled() {
        return enabled;
    }

    public static boolean locked() {
        return locked;
    }

    public static void enable() {
        enabled = true;
    }

    public static void lock() {
        locked = true;
    }

    private static void moneylover() {
        Coins.set(1000, false);
        Stats.timesCheated++;
    }

    private static void givemeitall() {
        Coins.set(5000, false);
        FirstAid.set(5000, false);
        InstaHealth.set(5000, false);
        for (int i = 0; i < WeaponInventory.arrayWeapon.size(); i++) {
            WeaponInventory.arrayWeapon.get(i).setAmmo(5000, false);
        }
        Power.set(5000, false);
        for (int i = 0; i < WeaponInventory.arrayWeapon.size(); i++) {
            WeaponInventory.arrayWeapon.get(i).owns = true;
        }
        Stats.timesCheated++;
        for (int i = 0; i < Food.arrayFood.size(); i++)
            Food.arrayFood.get(i).setQuantity(5000);
        Potion.set("Survival", 5000, false);
        Potion.set("Recovery", 5000, false);
    }

    private static void weaponstash() {
        for (int i = 0; i <= WeaponInventory.arrayWeapon.size(); i++) {
            WeaponInventory.arrayWeapon.get(i).setAmmo(5000, false);
        }

        Power.set(5000, false);
        for (int i = 0; i < WeaponInventory.arrayWeapon.size(); i++) {
            WeaponInventory.arrayWeapon.get(i).owns = true;
        }
        Stats.timesCheated++;
    }

    private static void nomorepain() {
        FirstAid.set(1000, false);
        InstaHealth.set(500, false);
        Potion.set("Survival", 500, false);
        Potion.set("Recovery", 500, false);
        for (int i = 0; i < Food.arrayFood.size(); i++)
            Food.arrayFood.get(i).setQuantity(100);
        Stats.timesCheated++;
    }

    private static void healme() {
        Health.set(Health.getOutOf());
        Stats.timesCheated++;
    }

    private static void givemeachallenge() {
        Enemy.get().setHealth(1000, 1000);
        Stats.timesCheated++;
    }

    private static void lotsofkills() {
        Stats.kills = 5000;
        Stats.timesCheated++;
    }

    private static void suicide() {
        Health.die();
        Stats.timesCheated++;
    }

    private static void godmode() {
        Settings.toggleGodMode();
        Stats.timesCheated++;
    }

    private static void loanshark() {
        Loan.setCurrentLoan(0);
        Loan.setNetDue(0);
        Stats.timesCheated++;
    }

    private static void thirstforfood() {
        for (int i = 0; i < Food.arrayFood.size(); i++)
            Food.arrayFood.get(i).setQuantity(10);
        Stats.timesCheated++;
    }
}