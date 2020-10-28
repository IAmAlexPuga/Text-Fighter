package com.hotmail.kalebmarc.textfighter.main;

import com.hotmail.kalebmarc.textfighter.main.*;
import com.hotmail.kalebmarc.textfighter.player.Achievements;
import com.hotmail.kalebmarc.textfighter.player.Coins;
import com.hotmail.kalebmarc.textfighter.player.Stats;
import com.hotmail.kalebmarc.textfighter.player.Xp;

import javax.swing.*;

public class Gun extends Weapon{

    //Properties
    public static int BULLET_DAMAGE;

    protected double criticalChance = .01;  // % chance of critical hit
    protected double chanceOfMissing;

    //Ammo
    protected int ammo; //exclusive to guns
    protected int ammoUsed;
    protected int ammoPrice;//Per 1
    protected int ammoIncludedWithPurchase;


    public Gun(String name, int ammoUsed, int ammoIncludedWithPurchase, boolean buyable, int price,
               int ammoPrice, int level, double chanceOfMissing, boolean firstInit, boolean changeDif) {

        super(name, buyable, price, level, firstInit, changeDif);

        this.ammoUsed = ammoUsed;
        this.ammoIncludedWithPurchase = ammoIncludedWithPurchase;
        this.ammoPrice = ammoPrice;
        this.chanceOfMissing = chanceOfMissing;


        if (!changeDif) {
            User.arrayWeapon.add(this);
        }

        if (firstInit) {
            this.owns = false;
        }

    }

    // used to set the criticalChance to non default value of .01
    public Gun(String name, int ammoUsed, int ammoIncludedWithPurchase, boolean buyable, int price, //For guns
                  int ammoPrice, int level, double chanceOfMissing, boolean firstInit, boolean changeDif, double criticalChance) {

        super(name, buyable, price, level, firstInit, changeDif);

        this.ammoUsed = ammoUsed;
        this.ammoIncludedWithPurchase = ammoIncludedWithPurchase;
        this.ammoPrice = ammoPrice;
        this.chanceOfMissing = chanceOfMissing;
        this.criticalChance = criticalChance;

        /*
        if (!changeDif) {
            arrayWeapon.add(this);
        }

        if (firstInit) {
            this.owns = false;

        }*/

    }



    @Override
    public String getType() {
        return "GUN";
    }


    private static void noAmmo() { //gun related
        Ui.popup("You've run out of ammo!", "Warning", JOptionPane.WARNING_MESSAGE);
        //Weapon.current = Weapon.starting;
    }

    public void setAmmo(int amount, boolean add) { //gun related
        if (add) {
            ammo += amount;
        } else {
           ammo = amount;
        }
    }

    @Override
    public void dealDam() { //varies between melee and gun
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
        displayDamageDealt(damageDealt, didCritical);
    }

    // Standard setter for criticalChance
    // Can be used for future enhancements like weapon upgrades
    public void setCriticalChance(double chance){
        // Set a default to not go under 0
        if(chance < 0)
        {
            criticalChance = .01;
        }else{
            criticalChance = chance;
        }
    }

    // Standard getter for criticalChance
    public double getCriticalChance(){
        return criticalChance;
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
                Ui.println("You dealt " + damageDealt + " damage with a " + name);
            }else{
                Ui.println("You dealt " + damageDealt + " damage with a " + name);
            }
            Ui.println("----------------------------------------------------");
            Ui.println("Your health: " + com.hotmail.kalebmarc.textfighter.player.Health.getStr());
            Ui.println("Enemy health: " + Enemy.get().getHeathStr());
            Ui.println("----------------------------------------------------");
            Ui.pause();

            if (Enemy.get().getHealth() <= Enemy.get().getHealthMax() / 3){
                Enemy.get().useFirstAidKit();
            }
        }
    }


    // Checks whether to perform critical hit or not
    public boolean didCriticalHit()
    {
        // For other different critical chance hits, must be >= 1.
        // Assuming min critical chance hits is .01
        return Random.RInt(100) * criticalChance >= 1;
    }

    @Override
    public void viewAbout() { //varies between melee and gun

        final int BORDER_LENGTH = 39;

        //Start of weapon Info
        Ui.cls();
        for (int i = 0; i < BORDER_LENGTH; i++) Ui.print("-");//Make line
        Ui.println();
        for (int i = 0; i < ((BORDER_LENGTH / 2) - (getName().length() / 2)); i++)
            Ui.print(" ");//Set correct spacing to get name in middle of box
        Ui.println(getName());
        Ui.println("Price: " + price + " coins");
        Ui.println("Chance of missing: " + chanceOfMissing + "%");
        Ui.println("Ammo Used: " + ammoUsed);
        Ui.println("Damage: " + getDamage());
        for (int i = 0; i < BORDER_LENGTH; i++) Ui.print("-");//Make line
        Ui.pause();
        Ui.cls();
        //End of weapon Info
    }

    @Override
    public String getDamage() { //varies between melee and gun
        if (chanceOfMissing == 0) {
            return String.valueOf((BULLET_DAMAGE * ammoUsed));
        } else {
            return ("0 - " + String.valueOf((BULLET_DAMAGE * ammoUsed)));
        }

    }

    public boolean isBuyable() {
        return buyable;
    }

    @Override
    public void buy() { //varies between melee and gun due to ammo included with purchase
        if(verifyPurchase())
        {
            //Buy
            Achievements.boughtItem = true;
            Coins.set(-price, true);
            Stats.coinsSpentOnWeapons += price;
            owns = true;
            //current = this;
            Ui.println("You have bought a " + getName() + " for " + price + " coins.");
            Ui.println("Coins: " + Coins.get());
            Ui.pause();

            //Give ammo
            ammo += ammoIncludedWithPurchase;
        }

    }

    public void buyAmmo() { //Weapon related

        Ui.cls();

        //Make sure player is a high enough level
        if (Xp.getLevel() < level) {
            Ui.println("You are not a high enough level. You need to be at least level " + level + ".");
            Ui.pause();
            return;
        }

        //Get amount of ammo user wants
        Ui.println("How much ammo would you like to buy?");
        Ui.println("1 ammo cost " + ammoPrice + " coins.");
        Ui.println("You have " + Coins.get() + " coins.");
        int ammoToBuy = Ui.getValidInt();
        int cost = ammoToBuy * ammoPrice;

        //Make sure player has enough coins
        if (Coins.get() < (cost)) {
            Ui.println("You don't have enough coins. You need " + (cost - Coins.get()) + " more coins.");
            Ui.pause();
            return;
        }

        ammo += ammoToBuy;
        Coins.set(-cost, true);
        Stats.coinsSpentOnWeapons += cost;

        Ui.println("You have bought " + ammoToBuy + " ammo.");
        Ui.pause();
    }

    public int getAmmoPrice() {
        return ammoPrice;
    } //gun related
}
