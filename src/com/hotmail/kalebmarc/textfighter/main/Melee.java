package com.hotmail.kalebmarc.textfighter.main;

import com.hotmail.kalebmarc.textfighter.player.Achievements;
import com.hotmail.kalebmarc.textfighter.player.Coins;
import com.hotmail.kalebmarc.textfighter.player.Stats;

public class Melee extends Weapon {

    protected int damageMin;
    protected int damageMax;
    protected int ammoUsed = 0;
    protected static int ammoPrice = 0;
    protected int ammoIncludedWithPurchase= 0;
    protected double criticalChance = 0;

    public Melee(String name, boolean buyable, int price, int level,//For Melee
                 int damageMin, int damageMax, boolean firstInit, boolean changeDif,  boolean startingWeapon) {

        super(name, buyable, price, level, firstInit, changeDif, startingWeapon);
        this.damageMin = damageMin;
        this.damageMax = damageMax;
        type = "MELEE";

        if (!changeDif) {
            WeaponInventory.arrayWeapon.add(this);
        }

        if (firstInit) {
            if (startingWeapon) {//If first init, see if player starts with this or not.
                this.owns = true;
                WeaponInventory.set(this);
                WeaponInventory.starting = this;
            } else {
                this.owns = false;
            }
        }
    }

    @Override
    public void dealDam() {
        int damageDealt = 0;
        boolean didCritical = false;

        damageDealt = Random.RInt(this.damageMin, this.damageMax);

        this.damageDealt = damageDealt;
        displayDamageDealt(damageDealt, didCritical);
    }

    @Override
    public String getDamage() {
        return (damageMin + " - " + damageMax);
    }

    @Override
    public void viewAbout() {

        final int BORDER_LENGTH = 39;

        //Start of weapon Info
        Ui.cls();
        for (int i = 0; i < BORDER_LENGTH; i++) Ui.print("-");//Make line
        Ui.println();
        for (int i = 0; i < ((BORDER_LENGTH / 2) - (this.getName().length() / 2)); i++)
            Ui.print(" ");//Set correct spacing to get name in middle of box
        Ui.println(this.getName());
        Ui.println("Price: " + this.price + " coins");
        Ui.println("Damage: " + this.getDamage());
        for (int i = 0; i < BORDER_LENGTH; i++) Ui.print("-");//Make line
        Ui.pause();
        Ui.cls();
        //End of weapon Info
    }

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
            Ui.println("You dealt " + damageDealt + " damage with a " + this.name);

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
    @Override
     public boolean didCriticalHit()
    {
        return false;
    }

    @Override
    public void setCriticalChance(double chance){ criticalChance = chance; }


}

