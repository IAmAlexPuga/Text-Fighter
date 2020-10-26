package com.hotmail.kalebmarc.textfighter.main;
import com.hotmail.kalebmarc.textfighter.player.Health;
import org.junit.*;
import org.junit.experimental.theories.suppliers.TestedOn;

import static org.junit.Assert.*;

public class GeneralWeaponTest {
    Weapon fists;
    Weapon baseballBat;
    Weapon knife;
    Weapon pipe;
    Weapon pistol;
    Weapon smg;
    Weapon shotgun;
    Weapon rifle;
    Weapon sniper;
    Enemy enemy;
    Health myHealth;
    //Declare variables to make it easier to test with other values
    int fistPrice = 0;  int fistLevel = 0; int fistsDMin = 5;  int fistsDMax = 10;
    int baseballBatPrice = 120; int baseballBatLevel = 1; int baseballBatDMin = 10; int baseballBatDMax = 15;
    int knifePrice = 125; int knifeLevel = 2; int knifeDMin = 10; int knifeDMax = 20;
    int pipePrice = 0;  int pipeLevel = 0; int pipeDMin = 5; int pipeDMax = 20;
    int pistolPrice = 250; int pistolAmmoUsed = 1; int pistolLevel = 4; int pistolAmmoPrice = 1; int pistolAWP = 18; int pistolChance = 0;
    int smgPrice = 700; int smgAmmoUsed = 10; int smgLevel = 10; int smgAmmoPrice = 1; int smgAWP = 75;  int smgChance = 0;
    int shotgunPrice = 375; int shotgunAmmoUsed = 1; int shotgunLevel = 9; int shotgunAmmoPrice = 2;  int shotgunAWP = 12;  int shotgunChance = 0;
    int riflePrice = 275; int rifleAmmoUsed = 1; int rifleLevel = 5; int rifleAmmoPrice = 1; int rifleAWP = 18;  int rifleChance = 0;
    int sniperPrice = 700; int sniperAmmoUsed = 1; int sniperLevel = 7; int sniperAmmoPrice = 2; int sniperAWP = 10; int sniperChance = 0;
    int moreAmmo = 5;

    @Before
    public void setUp() throws Exception{
        boolean firstInit = true;
        boolean changeDif = false;
        Weapon.BULLET_DAMAGE = 10;
        int ammoToAdd = 1;
        Health.set(50,50);
        //Weapons
        //Gun:   (name, ammoUsed, ammoIncludedWithPurchase, buyable, price, ammoPrice, level, chanceOfMissing, firstInit, changeDif)
        //Melee: (name, startingWeapon, buyable, price, level, damageMin, damageMax, firstInit)
        fists = new Weapon("Fists", true, false, fistPrice, fistLevel, fistsDMin, fistsDMax, firstInit, changeDif);
        baseballBat = new Weapon("Baseball Bat", false, true, baseballBatPrice, baseballBatLevel, baseballBatDMin, fistsDMax, firstInit, changeDif);
        knife = new Weapon("Knife", false, true, knifePrice, knifeLevel, knifeDMin, knifeDMax, firstInit, changeDif);
        pipe = new Weapon("Pipe", false, false, pipePrice, pipeLevel, pipeDMin, pipeDMax, firstInit, changeDif);
        pistol = new Weapon("Pistol", pistolAmmoUsed, pistolAWP, true, pistolPrice, pistolAmmoPrice, pistolLevel, pistolChance, firstInit, changeDif);
        smg = new Weapon("Smg", smgAmmoUsed, smgAWP, true, smgPrice, smgAmmoPrice, smgLevel, smgChance, firstInit, changeDif);
        shotgun = new Weapon("Shotgun", shotgunAmmoUsed, shotgunAWP, true, shotgunPrice, shotgunAmmoPrice, shotgunLevel, shotgunChance, firstInit, changeDif);
        rifle = new Weapon("Rifle", rifleAmmoUsed, rifleAWP, true, riflePrice, rifleAmmoPrice, rifleLevel, rifleChance, firstInit, changeDif);
        sniper = new Weapon("Sniper", sniperAmmoUsed, sniperAWP, true, sniperPrice, sniperAmmoPrice, sniperLevel, sniperChance, firstInit, changeDif);

        pistol.setAmmo(pistolAWP, false);
        smg.setAmmo(smgAWP, false);
        shotgun.setAmmo(shotgunAWP, false);
        rifle.setAmmo(rifleAWP, false);;
        sniper.setAmmo(sniperAWP, false);

        //Test enemy
        enemy = new Enemy("Dark Elf", 55, 15, 20, 15, 20, 15, firstInit, changeDif);
    }


    @Test
    public void testGetAmmo(){
        assertEquals(pistolAWP, pistol.getAmmo());
        assertEquals(smgAWP, smg.getAmmo());
        assertEquals(shotgunAWP, shotgun.getAmmo());
        assertEquals(rifleAWP, rifle.getAmmo());
        assertEquals(sniperAWP, sniper.getAmmo());
    }

    @Test
    public void testAddAmmo(){
        pistol.setAmmo(moreAmmo, true);
        assertEquals(pistolAWP + moreAmmo, pistol.getAmmo());

        smg.setAmmo(moreAmmo, true);
        assertEquals(smgAWP + moreAmmo, smg.getAmmo());

        shotgun.setAmmo(moreAmmo, true);
        assertEquals(shotgunAWP + moreAmmo, shotgun.getAmmo());

        rifle.setAmmo(moreAmmo, true);
        assertEquals(rifleAWP + moreAmmo, rifle.getAmmo());

        sniper.setAmmo(moreAmmo, true);
        assertEquals(sniperAWP + moreAmmo, sniper.getAmmo());
    }

    @Test
    public void testFists(){
        Enemy.encounterNew();
        int enemyInitialHealth = enemy.getHealth();
        assertEquals(enemy.getHealth(), enemyInitialHealth);
        fists.dealDam();
        int enemyHealthDiff = enemyInitialHealth - enemy.getHealth();
        if(enemyHealthDiff != 0)
        {
            assertNotEquals(enemyInitialHealth, enemy.getHealth());
            assertTrue(enemyHealthDiff >= fistsDMin && enemyHealthDiff <= fistsDMax);
        }


    }

    @Test
    public void testBaseballBat(){
        Enemy.encounterNew();
        int enemyInitialHealth = enemy.getHealth();
        assertEquals(enemy.getHealth(), enemyInitialHealth);
        baseballBat.dealDam();
        int enemyHealthDiff = enemyInitialHealth - enemy.getHealth();
        if(enemyHealthDiff != 0)
        {
            assertNotEquals(enemyInitialHealth, enemy.getHealth());
            assertTrue(enemyHealthDiff >= baseballBatDMin && enemyHealthDiff <= baseballBatDMax);
        }

    }

    @Test
    public void testKnife(){
        Enemy.encounterNew();
        int enemyInitialHealth = enemy.getHealth();
        assertEquals(enemy.getHealth(), enemyInitialHealth);
        knife.dealDam();
        int enemyHealthDiff = enemyInitialHealth - enemy.getHealth();
        if(enemyHealthDiff != 0)
        {
            assertNotEquals(enemyInitialHealth, enemy.getHealth());
            assertTrue(enemyHealthDiff >= knifeDMin && enemyHealthDiff <= knifeDMax);
        }

    }

    @Test
    public void testPipe(){
        Enemy.encounterNew();
        int enemyInitialHealth = enemy.getHealth();
        assertEquals(enemy.getHealth(), enemyInitialHealth);
        pipe.dealDam();
        int enemyHealthDiff = enemyInitialHealth - enemy.getHealth();
        if(enemyHealthDiff != 0)
        {
            assertNotEquals(enemyInitialHealth, enemy.getHealth());
            assertTrue(enemyHealthDiff >= pipeDMin && enemyHealthDiff <=pipeDMax);
        }

    }

    @Test
    public void testPistol(){
        Enemy.encounterNew();
        int enemyInitialHealth = enemy.getHealth();
        assertEquals(enemy.getHealth(), enemyInitialHealth);
        pistol.dealDam();
        int enemyHealthDiff = enemyInitialHealth - enemy.getHealth();
        if(enemyHealthDiff != 0)
        {
            assertNotEquals(enemyInitialHealth, enemy.getHealth());
            assertEquals(enemyHealthDiff, Weapon.BULLET_DAMAGE);
            assertEquals((pistolAWP - pistolAmmoUsed), pistol.getAmmo());
        }
    }

    @Test
    public void testSmg(){
        Enemy.encounterNew();
        int enemyInitialHealth = enemy.getHealth();
        assertEquals(enemy.getHealth(), enemyInitialHealth);
        smg.dealDam();
        int enemyHealthDiff = enemyInitialHealth - enemy.getHealth();
        if(enemyHealthDiff != 0)
        {
            assertNotEquals(enemyInitialHealth, enemy.getHealth());
            assertEquals(enemyHealthDiff, Weapon.BULLET_DAMAGE);
            assertEquals((smgAWP - smgAmmoUsed), smg.getAmmo());
        }
    }

    @Test
    public void testShotgun(){
        Enemy.encounterNew();
        int enemyInitialHealth = enemy.getHealth();
        assertEquals(enemy.getHealth(), enemyInitialHealth);
        shotgun.dealDam();
        int enemyHealthDiff = enemyInitialHealth - enemy.getHealth();
        if(enemyHealthDiff != 0)
        {
            assertNotEquals(enemyInitialHealth, enemy.getHealth());
            assertEquals(enemyHealthDiff, Weapon.BULLET_DAMAGE);
            assertEquals((shotgunAWP - shotgunAmmoUsed), shotgun.getAmmo());
        }

    }

    @Test
    public void testRifle(){
        Enemy.encounterNew();
        int enemyInitialHealth = enemy.getHealth();
        assertEquals(enemy.getHealth(), enemyInitialHealth);
        rifle.dealDam();
        int enemyHealthDiff = enemyInitialHealth - enemy.getHealth();
        if(enemyHealthDiff != 0)
        {
            assertNotEquals(enemyInitialHealth, enemy.getHealth());
            assertEquals(enemyHealthDiff, Weapon.BULLET_DAMAGE);
            assertEquals((rifleAWP - rifleAmmoUsed), rifle.getAmmo());
        }

    }

    @Test
    public void testSniper(){
        Enemy.encounterNew();
        int enemyInitialHealth = enemy.getHealth();
        assertEquals(enemy.getHealth(), enemyInitialHealth);
        sniper.dealDam();
        int enemyHealthDiff = enemyInitialHealth - enemy.getHealth();
        if(enemyHealthDiff != 0)
        {
            assertNotEquals(enemyInitialHealth, enemy.getHealth());
            assertEquals(enemyHealthDiff, Weapon.BULLET_DAMAGE);
            assertEquals((sniperAWP - sniperAmmoUsed), sniper.getAmmo());
        }

    }


}
