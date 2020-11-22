package com.hotmail.kalebmarc.textfighter.main;

public class WeaponInitialization {

    public static void setAllGameWeapons(String dif, boolean firstInit, boolean changeDif)
    {
        Game.fists = buildGameFists(firstInit, changeDif);
        Game.baseballBat = buildGameBaseballBat(dif, firstInit, changeDif);
        Game.knife = buildGameKnife(dif, firstInit, changeDif);
        Game.pipe = buildGamePipe(firstInit, changeDif);
        Game.pistol = buildGamePistol(dif, firstInit, changeDif);
        Game.smg = buildGameSmg(dif, firstInit, changeDif);
        Game.shotgun = buildGameShotgun(dif, firstInit, changeDif);
        Game.rifle = buildGameRifle(dif, firstInit, changeDif);
        Game.sniper = buildGameSniper(dif, firstInit, changeDif);
    }

    public static Weapon buildGameFists(boolean firstInit, boolean changeDif)
    {
        MeleeBuilder fists = new MeleeBuilder();
        fists.setName("Fists");
        fists.setStartingWeapon(true);
        fists.setBuyable(false);
        fists.setPrice(0);
        fists.setLevel(0);
        fists.setDamageMin(5);
        fists.setDamageMax(10);
        fists.setStartingWeapon(true);
        fists.setFirstInit((firstInit));
        fists.setChangeDif(changeDif);

        return fists.build();
    }

    public static Weapon buildGameBaseballBat(String dif, boolean firstInit, boolean changeDif)
    {
        MeleeBuilder baseballBat = new MeleeBuilder();
        baseballBat.setName("BaseballBat");
        baseballBat.setStartingWeapon(false);
        baseballBat.setBuyable(true);
        baseballBat.setLevel(1);
        baseballBat.setBuyable(true);
        baseballBat.setDamageMin(10);
        baseballBat.setDamageMax(15);
        baseballBat.setStartingWeapon(false);
        baseballBat.setFirstInit((firstInit));
        baseballBat.setChangeDif(changeDif);

        if(dif.equals("Easy"))
        {
            baseballBat.setPrice(120);
        }
        else
        {
            baseballBat.setPrice(170);
        }

        return baseballBat.build();
    }

    public static Weapon buildGameKnife(String dif, boolean firstInit, boolean changeDif)
    {
        MeleeBuilder knife = new MeleeBuilder();
        //Game.knife = new Weapon("Knife", false, true, 125, 2, 10, 20, firstInit, changeDif);
        knife.setName("Knife");
        knife.setStartingWeapon(false);
        knife.setBuyable(true);
        knife.setLevel(2);
        knife.setDamageMin(10);
        knife.setDamageMax(20);
        knife.setStartingWeapon(false);
        knife.setFirstInit((firstInit));
        knife.setChangeDif(changeDif);

        if(dif.equals("Easy"))
        {
            knife.setPrice(125);
        }
        else
        {
            knife.setPrice(175);
        }

        return knife.build();
    }

    public static Weapon buildGamePipe(boolean firstInit, boolean changeDif)
    {
        MeleeBuilder pipe = new MeleeBuilder();
        //Game.pipe = new Weapon("Pipe", false, false, 0, 0, 5, 20, firstInit, changeDif);
        pipe.setName("Pipe");
        pipe.setStartingWeapon(false);
        pipe.setBuyable(false);
        pipe.setPrice(0);
        pipe.setLevel(0);
        pipe.setDamageMin(5);
        pipe.setDamageMax(20);
        pipe.setStartingWeapon(false);
        pipe.setFirstInit((firstInit));
        pipe.setChangeDif(changeDif);
        return pipe.build();
    }

    public static Weapon buildGamePistol(String dif, boolean firstInit, boolean changeDif)
    {
        GunBuilder pistol = new GunBuilder();
        pistol.setName("Pistol");
        pistol.setAmmoUsed(1);
        pistol.setAmmoIncludedWithPurchase(18);
        pistol.setBuyable(true);
        pistol.setAmmoPrice(1);
        pistol.setLevel(4);
        pistol.setChanceOfMissing(15);
        pistol.setFirstInit(firstInit);
        pistol.setChangeDif(changeDif);

        if(dif.equals("Easy"))
        {
            pistol.setPrice(250);
        }
        else
        {
            pistol.setPrice(275);
        }
        return pistol.build();
    }

    public static Weapon buildGameSmg(String dif, boolean firstInit, boolean changeDif)
    {
        GunBuilder smg = new GunBuilder();
        smg.setName("Smg");
        smg.setAmmoUsed(10);
        smg.setAmmoIncludedWithPurchase(75);
        smg.setBuyable(true);
        smg.setAmmoPrice(1);
        smg.setLevel(10);
        smg.setChanceOfMissing(75);
        smg.setFirstInit(firstInit);
        smg.setChangeDif(changeDif);

        if(dif.equals("Easy"))
        {
            smg.setPrice(700);
        }
        else
        {
            smg.setPrice(800);
        }

        return smg.build();
    }

    public static Weapon buildGameShotgun(String dif, boolean firstInit, boolean changeDif)
    {
        GunBuilder shotgun = new GunBuilder();
        shotgun.setName("Shotgun");
        shotgun.setAmmoUsed(1);
        shotgun.setAmmoIncludedWithPurchase(12);
        shotgun.setBuyable(true);
        shotgun.setAmmoPrice(2);
        shotgun.setLevel(9);
        shotgun.setChanceOfMissing(60);
        shotgun.setFirstInit(firstInit);
        shotgun.setChangeDif(changeDif);

        if(dif.equals("Easy"))
        {
            shotgun.setPrice(375);
        }
        else
        {
            shotgun.setPrice(415);
        }

        return shotgun.build();
    }

    public static Weapon buildGameRifle(String dif, boolean firstInit, boolean changeDif)
    {
        GunBuilder rifle = new GunBuilder();
        rifle.setName("Rifle");
        rifle.setAmmoUsed(1);
        rifle.setAmmoIncludedWithPurchase(18);
        rifle.setBuyable(true);
        rifle.setAmmoPrice(1);
        rifle.setLevel(5);
        rifle.setChanceOfMissing(10);
        rifle.setFirstInit(firstInit);
        rifle.setChangeDif(changeDif);
        if(dif.equals("Easy"))
        {
            rifle.setPrice(275);
        }
        else
        {
            rifle.setPrice(300);
        }

        return rifle.build();
    }

    public static Weapon buildGameSniper(String dif, boolean firstInit, boolean changeDif)
    {
        GunBuilder sniper = new GunBuilder();
        sniper.setName("Sniper");
        sniper.setAmmoUsed(1);
        sniper.setAmmoIncludedWithPurchase(10);
        sniper.setBuyable(true);
        sniper.setAmmoPrice(2);
        sniper.setLevel(7);
        sniper.setChanceOfMissing(0);
        sniper.setFirstInit(firstInit);
        sniper.setChangeDif(changeDif);

        if(dif.equals("Easy"))
        {
            sniper.setPrice(700);
        }
        else
        {
            sniper.setPrice(750);
        }
        return sniper.build();
    }
}
