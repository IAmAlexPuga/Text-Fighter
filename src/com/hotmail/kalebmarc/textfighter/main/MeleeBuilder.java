package com.hotmail.kalebmarc.textfighter.main;


public class MeleeBuilder extends WeaponBuilder {

    protected int damageMin; //exclusive to melee
    protected int damageMax; //exclusive to melee
    protected boolean startingWeapon;

    @Override
    public Weapon build()
    {
        return new Melee(name, startingWeapon, buyable, price, level, damageMin, damageMax, firstInit, changeDif);
    }

    public void setDamageMin(int damageMin) {
        this.damageMin = damageMin;
    }

    public void setDamageMax(int damageMax) {
        this.damageMax = damageMax;
    }

    public void setStartingWeapon(boolean startingWeapon){
        this.startingWeapon = startingWeapon;
    }
}
