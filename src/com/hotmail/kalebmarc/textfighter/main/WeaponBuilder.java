package com.hotmail.kalebmarc.textfighter.main;


abstract public class WeaponBuilder{

    protected String name;
    protected boolean buyable;
    protected int price;
    protected int level;
    protected boolean firstInit;
    protected boolean changeDif;
    protected boolean startingWeapon;

    abstract public Weapon build();

    public void setBuyable(boolean buyable) {
        this.buyable = buyable;
    }

    public void setChangeDif(boolean changeDif) {
        this.changeDif = changeDif;
    }

    public void setFirstInit(boolean firstInit) {
        this.firstInit = firstInit;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setStartingWeapon(boolean startingWeapon) {
        this.startingWeapon = startingWeapon;
    }

}
