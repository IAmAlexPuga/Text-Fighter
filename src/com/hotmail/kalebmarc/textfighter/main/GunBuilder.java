package com.hotmail.kalebmarc.textfighter.main;


public class GunBuilder extends WeaponBuilder {

    protected double criticalChance = .01;  // % chance of critical hit
    protected double chanceOfMissing;

    //Ammo
    protected int ammoUsed;
    protected int ammoPrice;//Per 1
    protected int ammoIncludedWithPurchase;

    @Override
    public Weapon build()
    {
        return new Gun(name, ammoUsed, ammoIncludedWithPurchase, buyable, price, ammoPrice, level, chanceOfMissing, firstInit, changeDif, criticalChance);
    }

    public void setChanceOfMissing(double chanceOfMissing) {
        this.chanceOfMissing = chanceOfMissing;
    }

    public void setAmmoUsed(int ammoUsed) {
        this.ammoUsed = ammoUsed;
    }

    public void setAmmoPrice(int ammoPrice) {
        this.ammoPrice = ammoPrice;
    }

    public void setAmmoIncludedWithPurchase(int ammoIncludedWithPurchase) {
        this.ammoIncludedWithPurchase = ammoIncludedWithPurchase;
    }

}
