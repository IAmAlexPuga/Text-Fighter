package com.hotmail.kalebmarc.textfighter.main;

import com.hotmail.kalebmarc.textfighter.player.Achievements;
import com.hotmail.kalebmarc.textfighter.player.Coins;
import com.hotmail.kalebmarc.textfighter.player.Stats;
import com.hotmail.kalebmarc.textfighter.player.Xp;

import javax.swing.*;
import java.util.ArrayList;

abstract public class Weapon {
    //Variables
    protected int price;
    protected int level;
    protected boolean owns;
    protected String name;
    protected boolean buyable;
    protected static int ammo;
    protected static int ammoPrice;
    public String getName() {
        return name;
    }
    public boolean owns() {
        return owns;
    }
    public boolean isBuyable() { return buyable; }

    abstract public void dealDam();
    abstract public String getType();
    abstract public void viewAbout();
    abstract public String getDamage();


    public Weapon(String name, boolean buyable, int price, int level, boolean firstInit, boolean changeDif)
    {
        this.name = name;
        this.buyable = buyable;
        this.price = price;
        this.level = level;

        if (!changeDif) {
            User.arrayWeapon.add(this);
        }

        if (firstInit) {
            this.owns = false;

        }
    }

    public void setAmmo(int amount, boolean add) { //gun related
        if (add) {
            ammo += amount;
        } else {
            ammo = amount;
        }
    }

    public static int getAmmo() {return ammo;}

    public static void displayAmmo() { //gun related
        Ui.println("     Ammo: " + getAmmo());
    }

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

    abstract public void buy();

    public int getAmmoPrice() {
        return ammoPrice;
    }

}