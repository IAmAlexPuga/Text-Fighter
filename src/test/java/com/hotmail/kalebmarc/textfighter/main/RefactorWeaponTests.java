package com.hotmail.kalebmarc.textfighter.main;

import com.hotmail.kalebmarc.textfighter.player.Health;
import com.hotmail.kalebmarc.textfighter.player.Settings;
import org.junit.*;

import static org.junit.Assert.*;

public class RefactorWeaponTests {
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
        Settings.setDif("Easy", true, false);
        boolean firstInit = true;
        boolean changeDif = false;
        Gun.BULLET_DAMAGE = 10;
        int ammoToAdd = 1;
        Health.set(50,50);
        //Weapons
        //Gun:   (name, ammoUsed, ammoIncludedWithPurchase, buyable, price, ammoPrice, level, chanceOfMissing, firstInit, changeDif)
        //Melee: (name, startingWeapon, buyable, price, level, damageMin, damageMax, firstInit)
        fists = WeaponInitialization.buildGameFists(firstInit, changeDif);
        baseballBat = WeaponInitialization.buildGameBaseballBat("Easy", firstInit, changeDif);
        knife = WeaponInitialization.buildGameKnife("Easy", firstInit, changeDif);
        pipe = WeaponInitialization.buildGamePipe(firstInit, changeDif);
        pistol = WeaponInitialization.buildGamePistol("Easy", firstInit, changeDif);
        smg = WeaponInitialization.buildGameSmg("Easy", firstInit, changeDif);
        shotgun = WeaponInitialization.buildGameShotgun("Easy", firstInit, changeDif);
        rifle = WeaponInitialization.buildGameRifle("Easy", firstInit, changeDif);
        sniper = WeaponInitialization.buildGameSniper("Easy", firstInit, changeDif);

        pistol.setAmmo(pistolAWP, false);
        smg.setAmmo(smgAWP, false);
        shotgun.setAmmo(shotgunAWP, false);
        rifle.setAmmo(rifleAWP, false);;
        sniper.setAmmo(sniperAWP, false);

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
        int enemyInitialHealth = Enemy.get().getHealth();
        assertEquals(Enemy.get().getHealth(), enemyInitialHealth);
        fists.dealDam();
        int enemyHealthDiff = enemyInitialHealth - Enemy.get().getHealth();
        if(fists.getDamageDealt() != 0) //the hit didn't miss
        {
            assertNotEquals(enemyInitialHealth, Enemy.get().getHealth());
            assertTrue(enemyHealthDiff >= fistsDMin && enemyHealthDiff <= fistsDMax);
        }
        else
        {
            assertEquals(enemyInitialHealth, Enemy.get().getHealth());
        }


    }

    @Test
    public void testBaseballBat(){
        Enemy.encounterNew();
        int enemyInitialHealth = Enemy.get().getHealth();
        assertEquals(Enemy.get().getHealth(), enemyInitialHealth);
        baseballBat.dealDam();
        int enemyHealthDiff = enemyInitialHealth - Enemy.get().getHealth();
        if(baseballBat.getDamageDealt() != 0)
        {
            assertNotEquals(enemyInitialHealth, Enemy.get().getHealth());
            assertTrue(enemyHealthDiff >= baseballBatDMin && enemyHealthDiff <= baseballBatDMax);
        }
        else
        {
            assertEquals(enemyInitialHealth, Enemy.get().getHealth());
        }


    }

    @Test
    public void testKnife(){
        Enemy.encounterNew();
        int enemyInitialHealth = Enemy.get().getHealth();
        assertEquals(Enemy.get().getHealth(), enemyInitialHealth);
        knife.dealDam();
        int enemyHealthDiff = enemyInitialHealth - Enemy.get().getHealth();
        if(knife.getDamageDealt() != 0)
        {
            assertNotEquals(enemyInitialHealth, Enemy.get().getHealth());
            assertTrue(enemyHealthDiff >= knifeDMin && enemyHealthDiff <= knifeDMax);
        }
        else
        {
            assertEquals(enemyInitialHealth, Enemy.get().getHealth());
        }


    }

    @Test
    public void testPipe(){
        Enemy.encounterNew();
        int enemyInitialHealth = Enemy.get().getHealth();
        assertEquals(Enemy.get().getHealth(), enemyInitialHealth);
        pipe.dealDam();
        int enemyHealthDiff = enemyInitialHealth - Enemy.get().getHealth();
        if(pipe.getDamageDealt() != 0)
        {
            assertNotEquals(enemyInitialHealth, Enemy.get().getHealth());
            assertTrue(enemyHealthDiff >= pipeDMin && enemyHealthDiff <=pipeDMax);
        }
        else
        {
            assertEquals(enemyInitialHealth, Enemy.get().getHealth());
        }


    }

    @Test
    public void testPistol(){
        pistol.ammoUsed = 1;
        Enemy.encounterNew();
        int enemyInitialHealth = Enemy.get().getHealth();
        assertEquals(Enemy.get().getHealth(), enemyInitialHealth);
        pistol.dealDam();
        int enemyHealthDiff = enemyInitialHealth - Enemy.get().getHealth();
        if(pistol.getDamageDealt() != 0)
        {
            assertNotEquals(enemyInitialHealth, Enemy.get().getHealth());
            assertEquals(enemyHealthDiff, Gun.BULLET_DAMAGE);
            assertEquals((pistolAWP - pistolAmmoUsed), pistol.getAmmo());
        }
        else
        {
            assertEquals(enemyInitialHealth, Enemy.get().getHealth());
        }

    }

    @Test
    public void testSmg(){ //multiple shots fired
        smg.ammoUsed = 1; //make sure one shot works
        Enemy.encounterNew();
        int enemyInitialHealth = Enemy.get().getHealth();
        assertEquals(Enemy.get().getHealth(), enemyInitialHealth);
        smg.dealDam();
        int enemyHealthDiff = enemyInitialHealth - Enemy.get().getHealth();
        if(smg.getDamageDealt() != 0)
        {
            assertNotEquals(enemyInitialHealth, Enemy.get().getHealth());
            assertEquals(enemyHealthDiff, Gun.BULLET_DAMAGE);
            assertEquals((smgAWP - smgAmmoUsed), smg.getAmmo());
        }
        else
        {
            assertEquals(enemyInitialHealth, Enemy.get().getHealth());
        }

    }

    @Test
    public void testShotgun(){
        shotgun.ammoUsed = 1;
        Enemy.encounterNew();
        int enemyInitialHealth = Enemy.get().getHealth();
        assertEquals(Enemy.get().getHealth(), enemyInitialHealth);
        shotgun.dealDam();
        int enemyHealthDiff = enemyInitialHealth - Enemy.get().getHealth();
        if(shotgun.getDamageDealt() != 0)
        {
            assertNotEquals(enemyInitialHealth, Enemy.get().getHealth());
            assertEquals(enemyHealthDiff, Gun.BULLET_DAMAGE);
            assertEquals((shotgunAWP - shotgunAmmoUsed), shotgun.getAmmo());
        }
        else
        {
            assertEquals(enemyInitialHealth, Enemy.get().getHealth());
        }

    }

    @Test
    public void testRifle(){
        rifle.ammoUsed = 1;
        Enemy.encounterNew();
        int enemyInitialHealth = Enemy.get().getHealth();
        assertEquals(Enemy.get().getHealth(), enemyInitialHealth);
        rifle.dealDam();
        int enemyHealthDiff = enemyInitialHealth - Enemy.get().getHealth();
        if(rifle.getDamageDealt() != 0)
        {
            assertNotEquals(enemyInitialHealth, Enemy.get().getHealth());
            assertEquals(enemyHealthDiff, Gun.BULLET_DAMAGE);
            assertEquals((rifleAWP - rifleAmmoUsed), rifle.getAmmo());
        }
        else
        {
            assertEquals(enemyInitialHealth, Enemy.get().getHealth());
        }


    }

    @Test
    public void testSniper(){
        sniper.ammoUsed = 1;
        Enemy.encounterNew();
        int enemyInitialHealth = Enemy.get().getHealth();
        assertEquals(Enemy.get().getHealth(), enemyInitialHealth);
        sniper.dealDam();
        int enemyHealthDiff = enemyInitialHealth - Enemy.get().getHealth();
        if(sniper.getDamageDealt()!= 0)
        {
            assertNotEquals(enemyInitialHealth, Enemy.get().getHealth());
            assertEquals(enemyHealthDiff, Gun.BULLET_DAMAGE);
            assertEquals((sniperAWP - sniperAmmoUsed), sniper.getAmmo());
        }
        else
        {
            assertEquals(enemyInitialHealth, Enemy.get().getHealth());
        }

    }

}
