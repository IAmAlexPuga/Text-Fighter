package com.hotmail.kalebmarc.textfighter.main;

import com.hotmail.kalebmarc.textfighter.player.Settings;
import com.hotmail.kalebmarc.textfighter.player.Stats;
import com.hotmail.kalebmarc.textfighter.player.Xp;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class WeaponTest {
    Weapon rifle;
    Weapon pistol;
    Enemy enemy;

    @Before
    public void setUp() throws Exception {
        Settings.setDif("Easy", true, false); //Due to new enemy refactoring it is necessary to set the difficulty this way

       //rifle = new Weapon("Rifle", 1, 18, true, 275, 1, 5, 0, true, true , 1); Had to change due to refactoring
        rifle = WeaponInitialization.buildGameRifle("Easy", true, true); //Testing with default settings
        rifle.setAmmo(10, true);//rifle.setAmmo(10,true);
        rifle.setCriticalChance(1);

        //pistol = new Weapon("Pistol", 1, 18, true, 250, 1, 4, 0, true, true);
        GunBuilder pistol = new GunBuilder();
        pistol.setName("Pistol");
        pistol.setAmmoUsed(1);
        pistol.setAmmoIncludedWithPurchase(18);
        pistol.setBuyable(true);
        pistol.setPrice(250);
        pistol.setAmmoPrice(1);
        pistol.setLevel(4);
        pistol.setChanceOfMissing(0);
        pistol.setFirstInit(true);
        pistol.setChangeDif(true);
        this.pistol = pistol.build();

        //pistol.setAmmo(10,true);
        this.pistol.setAmmo(10,true);
        //enemy = new Enemy("Dark Elf", 45, 10, 15, 10, 15, 15, true, false);
        Enemy.setCurrentEnemy(EnemyBuilder.buildDarkElf());
        Gun.BULLET_DAMAGE = 10;

    }

    @Test
    public void dealDam() {
        //Enemy.encounterNew();
        /*
        * Make sure to click ok pop ups
        * */
        // Non critical
        Enemy.setCurrentEnemy(EnemyBuilder.buildDarkElf());
        int enemyStartHealth = Enemy.get().getHealth();
        assertEquals(Enemy.get().getHealth(), enemyStartHealth);
        // Make sure there is 0 chance of critical damage
        pistol.setCriticalChance(0);
        pistol.dealDam();
        assertNotEquals(enemyStartHealth, Enemy.get().getHealth());
        assertEquals(enemyStartHealth - Gun.BULLET_DAMAGE , Enemy.get().getHealth());
        int enemyAfterHealth = Enemy.get().getHealth();
        // Reset pistol's criticalChance back to default
        pistol.setCriticalChance(.01);

        //Build new enemy
        Enemy.setCurrentEnemy(EnemyBuilder.buildDarkElf());
        // Critical
        rifle.dealDam();
        if(rifle.getDamageDealt() != 0)
        {
            Enemy.setCurrentEnemy(EnemyBuilder.buildDarkElf());
            assertNotEquals(enemyAfterHealth, Enemy.get().getHealth());
            // Enemy died and gets respawned immediately. Health should be 45 or whatever test enemy health set to.
            assertEquals(enemyStartHealth , Enemy.get().getHealth()); //cannot be equal because an enemy of a different type respawns
        }

    }

    @Test
    public void setCriticalChance() {
        // Check the Rifle critical chance is set to 1
        assertTrue(rifle.getCriticalChance() == 1);
        rifle.setCriticalChance(-200);
        assertTrue(rifle.getCriticalChance() == .01);
        rifle.setCriticalChance(1);
        assertTrue(rifle.getCriticalChance() == 1);

        // Check Pistol set to default .01
        assertTrue(pistol.getCriticalChance() == .01);
        pistol.setCriticalChance(0);
        assertTrue(pistol.getCriticalChance() == 0);
        pistol.setCriticalChance(.01);
        assertTrue(pistol.getCriticalChance() == .01);
    }

    @Test
    public void getCriticalChance() {
        //Weapon rifle2 = new Weapon("Rifle", 1, 18, true, 275, 1, 5, 10, true, true , .06);
        //Gun:   (name, ammoUsed, ammoIncludedWithPurchase, buyable, price, ammoPrice, level, chanceOfMissing, firstInit, changeDif)
        GunBuilder tempRifle = new GunBuilder();
        tempRifle.setName("Rifle");
        tempRifle.setAmmoUsed(1);
        tempRifle.setAmmoIncludedWithPurchase(18);
        tempRifle.setBuyable(true);
        tempRifle.setPrice(275);
        tempRifle.setAmmoPrice(1);
        tempRifle.setLevel(5);
        tempRifle.setChanceOfMissing(10);
        tempRifle.setFirstInit(true);
        tempRifle.setChangeDif(true);
        Weapon rifle2 = tempRifle.build();
        rifle2.setCriticalChance(.06);

        assertTrue(rifle2.getCriticalChance() == .06);

        // Check the Rifle critical chance is set to 1
        assertTrue(rifle.getCriticalChance() == 1);

        // Check Pistol set to default .01
        assertTrue(pistol.getCriticalChance() == .01);
    }

    @Test
    public void displayDamageDealt() {
        //Enemy.encounterNew();
        int nDealt = Stats.totalDamageDealt;
        int xp = Xp.getBattleXp();

        assertEquals(nDealt,Stats.totalDamageDealt);
        assertEquals(xp, Xp.getBattleXp());

        pistol.dealDam();

        assertNotEquals(nDealt,Stats.totalDamageDealt);
        assertNotEquals(xp, Xp.getBattleXp());

        // Set to current values
        nDealt = Stats.totalDamageDealt;
        xp = Xp.getBattleXp();

        // Sets pistols ammo to 0 so cant do damage
        pistol.setAmmo(-pistol.getAmmo(), true);
        assertEquals(nDealt,Stats.totalDamageDealt);
        assertEquals(xp, Xp.getBattleXp());

    }

    @Test
    public void didCriticalHit() {
        assertEquals(rifle.didCriticalHit(), true);
        rifle.setCriticalChance(0);
        assertFalse(rifle.didCriticalHit());
        rifle.setCriticalChance(1);
        assertTrue(rifle.didCriticalHit());

    }


}