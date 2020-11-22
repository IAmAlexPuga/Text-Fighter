package com.hotmail.kalebmarc.textfighter.main;

public class MeleeBuilder extends WeaponBuilder {

    protected int damageMin; //exclusive to melee
    protected int damageMax; //exclusive to melee

    @Override
    public Weapon build()
    {
        return new Melee(name, buyable, price, level, damageMin, damageMax, firstInit, changeDif, startingWeapon);
    }

    public void setDamageMin(int damageMin) {
        this.damageMin = damageMin;
    }

    public void setDamageMax(int damageMax) {
        this.damageMax = damageMax;
    }
}
