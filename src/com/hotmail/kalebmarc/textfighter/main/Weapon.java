package com.hotmail.kalebmarc.textfighter.main;

import com.hotmail.kalebmarc.textfighter.player.Coins;
import com.hotmail.kalebmarc.textfighter.player.Stats;
import com.hotmail.kalebmarc.textfighter.player.Xp;

abstract public class Weapon {

    //Variables
    protected int price;
    protected int level;
    protected boolean owns;
    protected String name;
    protected boolean buyable;
    protected String type;
    protected boolean startingWeapon;
    protected double criticalChance;
    protected int damageDealt;

    protected int ammo;
    protected int ammoUsed;
    protected int ammoPrice;//Per 1
    protected int ammoIncludedWithPurchase;

    public Weapon(String name, boolean buyable, int price, int level, boolean firstInit, boolean changeDif, boolean startingWeapon)
    {
        this.name = name;
        this.buyable = buyable;
        this.price = price;
        this.level = level;
        this.startingWeapon = startingWeapon;
        this.criticalChance =.01;
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


    public int getPrice() { return price; }
    public int getLevel() { return level; }
    public String getName() {
        return name;
    }
    public boolean owns() {
        return owns;
    }
    public boolean isBuyable() {
        return this.buyable;
    }

    public String getType()
    {
        return type;
    }

    abstract public void dealDam();
    abstract public void viewAbout();
    abstract public String getDamage();

    protected boolean verifyPurchase()
    {
        boolean canBuy = true;
        if (!buyable) {
            Ui.msg("Sorry, this item is no longer in stock.");
            canBuy = false;
        }
        if (owns) {
            Ui.msg("You already own this weapon.");
            canBuy = false;
        }
        if (level > Xp.getLevel()) {
            Ui.msg("You are not a high enough level to buy this item.");
            canBuy = false;
        }
        if (price > Coins.get()) {
            Ui.msg("You do not have enough coins to buy this item.");
            canBuy = false;
        }

        return canBuy;
    }

    public void displayAmmo() {
        Ui.println("     Ammo: " + ammo);
    }

    public int getAmmoPrice() {
        return ammoPrice;
    }

    public int getAmmo() {
        return ammo;
    }

    public void setAmmo(int amount, boolean add) { }

    public abstract void buy();

    public void buyAmmo() {
        Ui.cls();

        //Make sure player is a high enough level
        if (Xp.getLevel() < level) {
            Ui.println("You are not a high enough level. You need to be at least level " + this.level + ".");
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

    abstract public void setCriticalChance(double chance);
    // Standard getter for criticalChance
    public double getCriticalChance(){
        return criticalChance;
    }

    abstract public boolean didCriticalHit();

    public int getDamageDealt()
    {
        return damageDealt;
    }

}