package com.hotmail.kalebmarc.textfighter.main;

import com.hotmail.kalebmarc.textfighter.player.Achievements;
import com.hotmail.kalebmarc.textfighter.player.Coins;
import com.hotmail.kalebmarc.textfighter.player.Stats;
import com.hotmail.kalebmarc.textfighter.player.Xp;

import javax.swing.*;

public class Gun extends Weapon {

    public static int BULLET_DAMAGE;
    //protected double criticalChance = .01;  // % chance of critical hit
    protected double chanceOfMissing;

    // used to set the criticalChance to non default value of .01
    public Gun(String name, int ammoUsed, int ammoIncludedWithPurchase, boolean buyable, int price, //For guns
               int ammoPrice, int level, double chanceOfMissing, boolean firstInit, boolean changeDif, double criticalChance, boolean startingWeapon) {

        super(name, buyable, price, level, firstInit, changeDif, startingWeapon);

        this.ammoUsed = ammoUsed;
        this.ammoIncludedWithPurchase = ammoIncludedWithPurchase;
        this.ammoPrice = ammoPrice;
        this.chanceOfMissing = chanceOfMissing;
        this.criticalChance = criticalChance;
        type = "GUN";
        startingWeapon = false;
        if (!changeDif) {
            WeaponInventory.arrayWeapon.add(this);
        }

        if (firstInit) {
            this.owns = false;
        }
    }

    @Override
    public void setAmmo(int amount, boolean add) {
        if (add) {
            ammo += amount; //should this be static
        } else {
            ammo = amount;
        }
    }

    @Override
    public void dealDam() {
        int damageDealt = 0;
        boolean didCritical = false;

        if (getAmmo() >= ammoUsed) {

            for (int i = 1; i <= ammoUsed; i++) {
                if (Random.RInt(100) > chanceOfMissing) {
                    // Check if its a critical hit
                    didCritical = didCriticalHit();
                    if (didCritical) {
                        damageDealt += BULLET_DAMAGE * 10;
                    } else {
                        damageDealt += BULLET_DAMAGE;
                    }

                    Stats.bulletsThatHit++;
                }

                //Results
                setAmmo(-1, true);
                Stats.bulletsFired += 1;
            }

        } else {
            noAmmo();
            damageDealt = 0;
        }
        this.damageDealt = damageDealt;
        displayDamageDealt(damageDealt, didCritical);
    }

    @Override
    public String getDamage() {
        if (chanceOfMissing == 0) {
            return String.valueOf((BULLET_DAMAGE * ammoUsed));
        } else {
            return ("0 - " + String.valueOf((BULLET_DAMAGE * ammoUsed)));
        }
    }

    private static void noAmmo() {
        Ui.popup("You've run out of ammo!", "Warning", JOptionPane.WARNING_MESSAGE);
        WeaponInventory.set(WeaponInventory.starting);
    }

    // Standard setter for criticalChance
    // Can be used for future enhancements like weapon upgrades
    @Override
    public void setCriticalChance(double chance){
        // Set a default to not go under 0
        if(chance < 0)
        {
            criticalChance = .01;
        }else{
            criticalChance = chance;
        }
    }


    // Checks whether to perform critical hit or not
    @Override
    public boolean didCriticalHit()
    {
        // For other different critical chance hits, must be >= 1.
        // Assuming min critical chance hits is .01
        return Random.RInt(100) * criticalChance >= 1;
    }
    @Override
    public void viewAbout() {

        final int BORDER_LENGTH = 39;

        //Start of weapon Info
        Ui.cls();
        for (int i = 0; i < BORDER_LENGTH; i++) Ui.print("-");//Make line
        Ui.println();
        for (int i = 0; i < ((BORDER_LENGTH / 2) - (name.length() / 2)); i++)
            Ui.print(" ");//Set correct spacing to get name in middle of box
        Ui.println(name);
        Ui.println("Price: " + price + " coins");
        Ui.println("Chance of missing: " + chanceOfMissing + "%");
        Ui.println("Ammo Used: " + ammoUsed);
        Ui.println("Damage: " + getDamage());
        for (int i = 0; i < BORDER_LENGTH; i++) Ui.print("-");//Make line
        Ui.pause();
        Ui.cls();
        //End of weapon Info
    }

    public void buy() { //varies between melee and gun due to ammo included with purchase
        if(verifyPurchase())
        {
            //Buy
            Achievements.boughtItem = true;
            Coins.set(-price, true);
            Stats.coinsSpentOnWeapons += price;
            owns = true;
            WeaponInventory.set(this);
            Ui.println("You have bought a " + name + " for " + price + " coins.");
            Ui.println("Coins: " + Coins.get());
            Ui.pause();

            //Give ammo
            ammo += ammoIncludedWithPurchase;
        }
    }



    // Displays the damage dealt to console
    public void displayDamageDealt(int damageDealt, boolean didCritical){
        //Display stuff
        com.hotmail.kalebmarc.textfighter.player.Stats.totalDamageDealt += damageDealt;
        com.hotmail.kalebmarc.textfighter.player.Xp.setBattleXp(damageDealt, true);
        if(!Enemy.get().takeDamage(damageDealt)) { // !dead
            Ui.cls();
            Ui.println("----------------------------------------------------");
            Ui.println("You have attacked a " + Enemy.get().getName() + "!");

            // Conditional to check whether critical hit or not to display correct message
            if(didCritical)
            {
                Ui.print("Critical hit!");
                Ui.println("You dealt " + damageDealt + " damage with a " + this.name);
            }else{
                Ui.println("You dealt " + damageDealt + " damage with a " + this.name);
            }
            Ui.println("----------------------------------------------------");
            Ui.println("Your health: " + com.hotmail.kalebmarc.textfighter.player.Health.getStr());
            Ui.println("Enemy health: " + Enemy.get().getHeathStr());
            Ui.println("----------------------------------------------------");
            // Remove pause as Game now asks if you want to continue fighting before returning to menu
            //Ui.pause();

            if (Enemy.get().getHealth() <= Enemy.get().getHealthMax() / 3){
                Enemy.get().useFirstAidKit();
            }
        }
    }
}
