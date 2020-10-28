package com.hotmail.kalebmarc.textfighter.main;

import com.hotmail.kalebmarc.textfighter.main.Enemy;
import com.hotmail.kalebmarc.textfighter.main.Random;
import com.hotmail.kalebmarc.textfighter.main.Ui;
import com.hotmail.kalebmarc.textfighter.player.Achievements;
import com.hotmail.kalebmarc.textfighter.player.Coins;
import com.hotmail.kalebmarc.textfighter.player.Stats;

public class Melee extends Weapon {

    protected int damageMin; //exclusive to melee
    protected int damageMax; //exclusive to melee


    public Melee(String name, boolean startingWeapon, boolean buyable, int price, int level,//For Melee
                 int damageMin, int damageMax, boolean firstInit, boolean changeDif) {

        super(name, buyable, price, level, firstInit, changeDif);
        this.damageMin = damageMin;
        this.damageMax = damageMax;


        if (!changeDif) {
            User.arrayWeapon.add(this);
        }

        if (firstInit) {
            if (startingWeapon) {//If first init, see if player starts with this or not.
                this.owns = true;
                User.currentWeapon = this;
                User.starting = this;
            } else {
                this.owns = false;
            }
        }
    }

    @Override
    public String getType()
    {
        return "MELEE";
    }

    @Override
    public void dealDam() { //varies between melee and gun
        int damageDealt = 0;
        boolean didCritical = false;

        damageDealt = Random.RInt(this.damageMin, this.damageMax);
        displayDamageDealt(damageDealt);

    }

    // Displays the damage dealt to console
    public void displayDamageDealt(int damageDealt){
        //Display stuff
        com.hotmail.kalebmarc.textfighter.player.Stats.totalDamageDealt += damageDealt;
        com.hotmail.kalebmarc.textfighter.player.Xp.setBattleXp(damageDealt, true);
        if(!Enemy.get().takeDamage(damageDealt)) { // !dead
            Ui.cls();
            Ui.println("----------------------------------------------------");
            Ui.println("You have attacked a " + Enemy.get().getName() + "!");
            Ui.println("You dealt " + damageDealt + " damage with a " + name);
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

    @Override
    public void viewAbout() { //varies between melee and gun

        final int BORDER_LENGTH = 39;

        //Start of weapon Info
        Ui.cls();
        for (int i = 0; i < BORDER_LENGTH; i++) Ui.print("-");//Make line
        Ui.println();
        for (int i = 0; i < ((BORDER_LENGTH / 2) - (this.getName().length() / 2)); i++)
            Ui.print(" ");//Set correct spacing to get name in middle of box
        Ui.println(getName());
        Ui.println("Price: " + price + " coins");
        Ui.println("Damage: " + this.getDamage());
        for (int i = 0; i < BORDER_LENGTH; i++) Ui.print("-");//Make line
        Ui.pause();
        Ui.cls();
        //End of weapon Info
    }

    @Override
    public String getDamage() { //varies between melee and gun
        return (this.damageMin + " - " + this.damageMax);
    }

    @Override
    public void buy() { //varies between melee and gun due to ammo included with purchase
        if(verifyPurchase()) {
            //Buy
            Achievements.boughtItem = true;
            Coins.set(-price, true);
            Stats.coinsSpentOnWeapons += price;
            owns = true;
            //current = this;
            Ui.println("You have bought a " + getName() + " for " + price + " coins.");
            Ui.println("Coins: " + Coins.get());
            Ui.pause();
        }

    }

}
