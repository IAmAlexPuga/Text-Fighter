package com.hotmail.kalebmarc.textfighter.player;

import com.hotmail.kalebmarc.textfighter.item.FirstAid;
import com.hotmail.kalebmarc.textfighter.item.InstaHealth;
import com.hotmail.kalebmarc.textfighter.item.Power;
import com.hotmail.kalebmarc.textfighter.main.*;
import com.hotmail.kalebmarc.textfighter.main.saves.Mapper;
import com.hotmail.kalebmarc.textfighter.main.saves.Reader;

public class Stats {
    //Battle Stats
    public static int highScore;
    public static int kills;
    public static int totalDamageDealt;
    public static int totalKills;
    public static int bulletsFired;
    public static int bulletsThatHit;
    //Coins
    public static int totalCoinsSpent;
    public static int coinsSpentOnWeapons;
    public static int coinsSpentOnHealth;
    public static int coinsSpentOnBankInterest;
    public static int xpBought;
    //Other
    public static int timesCheated;
    public static int timesQuit;
    public static int itemsCrafted;
    public static int diceGamesPlayed;
    public static int slotGamesPlayed;
    public static int lotteryTicketsBought;
    public static int lotteryWon;
    public static int blackjackGamesPlayed;
    private static String killDeathRatio;

    private Stats() {
    }

    public static void view() {

        updateKillDeathRatio();

        Ui.cls();
        Ui.println("-------------------------------------------------");
        Ui.println("                   PLAYER STATS                  ");
        Ui.println();
        Ui.println("Battle stats:");
        Ui.println("   High Score - " + highScore);
        Ui.println("   Current Kill Streak - " + kills);
        Ui.println("   Total POWER's Used - " + Power.used);
        Ui.println("   Current Weapon - " + WeaponInventory.get().getName());
        Ui.println("   Current Enemy - " + com.hotmail.kalebmarc.textfighter.main.Enemy.get().getName());
        Ui.println("   Total Damage Dealt - " + totalDamageDealt);
        Ui.println("   Total Kills - " + totalKills);
        Ui.println("   Bullets Fired - " + bulletsFired);
        Ui.println("   Bullets that hit - " + bulletsThatHit);
        Ui.println("   K:D - " + killDeathRatio);
        Ui.println();
        Ui.println("Coins:");
        Ui.println("   Coins - " + Coins.get());
        Ui.println("   Coins in bank - " + Bank.get());
        Ui.println("   Total Coins Won in Casino - " + Casino.totalCoinsWon);
        Ui.println("   Total Games Played in Casino - " + Casino.gamesPlayed);
        Ui.println("   Total coins spent - " + totalCoinsSpent);
        Ui.println("   Coins spent on bank interest - " + coinsSpentOnBankInterest);
        Ui.println("   Coins spent on weapons - " + coinsSpentOnWeapons);
        Ui.println("   Coins spent on health - " + coinsSpentOnHealth);
        Ui.println("   XP bought - " + xpBought);
        Ui.println();
        Ui.println("Health:");
        Ui.println("   Health - " + Health.getStr());
        Ui.println("   Insta-Healths used - " + InstaHealth.used);
        Ui.println("   First-Aid kits used - " + FirstAid.used);
        Ui.println("   Total Potions used - " + (Potion.spUsed + Potion.rpUsed));
        Ui.println("      Survival Potions used - " + Potion.spUsed);
        Ui.println("      Recovery Potions used - " + Potion.rpUsed);
        Ui.println("   Times Died - " + Health.timesDied);
        Ui.println("   Food items eaten - " + Food.totalEaten);
        Ui.println();
        Ui.println("Other: ");
        Ui.println("   Cheats Enabled? - " + Cheats.enabled());
        Ui.println("   Level - " + Xp.getLevel());
        Ui.println("   Xp - " + Xp.getFull());
        Ui.println("   Total Xp gained - " + Xp.total);
        Ui.println("   Times cheated - " + timesCheated);
        Ui.println("   Times quit - " + timesQuit);
        Ui.println("   Items Crafted - " + itemsCrafted);
        Ui.println("   Dice Games Played - " + diceGamesPlayed);
        Ui.println("   Slot Games Played - " + slotGamesPlayed);
        Ui.println("   Blackjack Games Played - " + blackjackGamesPlayed);
        Ui.println("   Lottery Tickets Bought - " + lotteryTicketsBought);
        Ui.println("   Lotteries Won - " + lotteryWon);
        Ui.println();
        Ui.println("-------------------------------------------------");
        Ui.pause();
    }

    private static void updateKillDeathRatio() {
        int i, gcm = 1, first = totalKills, second = Health.timesDied;

        i = (first >= second) ? first : second;

        while (i != 0) {
            if (first % i == 0 && second % i == 0) {
                gcm = i;
                break;
            }
            i--;
        }

        killDeathRatio = first / gcm + ":" + second / gcm;
    }

    public static void saveCoins(){
        Mapper.set("Stats.Money_Spent.Coins", Stats.totalCoinsSpent);
        Mapper.set("Stats.Money_Spent.Interest", Stats.coinsSpentOnBankInterest);
        Mapper.set("Stats.Money_Spent.Weapons", Stats.coinsSpentOnWeapons);
        Mapper.set("Stats.Money_Spent.Health", Stats.coinsSpentOnHealth);
        Mapper.set("Stats.Money_Spent.XP", Stats.xpBought);
        Mapper.set("Stats.Blackjack_Games_Played", Stats.blackjackGamesPlayed);
        Mapper.set("Stats.Lottery_Tickets_Bought", Stats.lotteryTicketsBought);
        Mapper.set("Stats.Lottery_Won", Stats.lotteryWon);
    }
    
    public static void saveKills(){
        Mapper.set("Stats.Kills", Stats.kills);
        Mapper.set("Stats.Total_Kills", Stats.totalKills);
        Mapper.set("Stats.High_Score", Stats.highScore);
        Mapper.set("User.Weapons.Current", WeaponInventory.arrayWeapon.indexOf(WeaponInventory.get()));
    }

    public static void saveDamage() {
        Mapper.set("Stats.Damage_Dealt", Stats.totalDamageDealt);
        Mapper.set("Stats.Bullets_Fired", Stats.bulletsFired);
        Mapper.set("Stats.Bullets_Hit", Stats.bulletsThatHit);
    }

    public static void saveOther() {
        Mapper.set("Stats.Times_Cheated", Stats.timesCheated);
        Mapper.set("Stats.Times_Quit", Stats.timesQuit);
        Mapper.set("Stats.Items_Crafted", Stats.itemsCrafted);
        Mapper.set("Stats.Games_Played.Dice", Stats.diceGamesPlayed);
        Mapper.set("Stats.Games_Played.Slots", Stats.slotGamesPlayed);
    }

    public static void loadCoins() {
        Stats.totalCoinsSpent = Mapper.getInteger("Stats.Money_Spent.Coins");
        Stats.coinsSpentOnBankInterest = Mapper.getInteger("Stats.Money_Spent.Interest");
        Stats.coinsSpentOnWeapons = Mapper.getInteger("Stats.Money_Spent.Weapons");
        Stats.coinsSpentOnHealth = Mapper.getInteger("Stats.Money_Spent.Health");
        Stats.xpBought = Mapper.getInteger("Stats.Money_Spent.XP");
        Stats.blackjackGamesPlayed = Mapper.getInteger("Stats.Blackjack_Games_Played");
        Stats.lotteryTicketsBought = Mapper.getInteger("Stats.Lottery_Tickets_Bought");
        Stats.lotteryWon = Mapper.getInteger("Stats.Lottery_Won");
    }

    public static void loadKills() {
        Stats.kills = Mapper.getInteger("Stats.Kills");
        Stats.highScore = Mapper.getInteger("Stats.High_Score");
        Stats.totalKills = Mapper.getInteger("Stats.Total_Kills");
    }

    public static void loadDamage() {
        Stats.totalDamageDealt = Mapper.getInteger("Stats.Damage_Dealt");
        Stats.bulletsFired = Mapper.getInteger("Stats.Bullets_Fired");
        Stats.bulletsThatHit = Mapper.getInteger("Stats.Bullets_Hit");
    }

    public static void loadOther() {
        Stats.timesCheated = Mapper.getInteger("Stats.Times_Cheated");
        Stats.timesQuit = Mapper.getInteger("Stats.Times_Quit");
        Stats.itemsCrafted = Mapper.getInteger("Stats.Items_Crafted");
        Stats.diceGamesPlayed = Mapper.getInteger("Stats.Games_Played.Dice");
        Stats.slotGamesPlayed = Mapper.getInteger("Stats.Games_Played.Slots");
    }

    public static void convertCoins() {
        Stats.totalCoinsSpent = Reader.readInt();
        Stats.coinsSpentOnBankInterest = Reader.readInt();
        Stats.coinsSpentOnWeapons = Reader.readInt();
        Stats.coinsSpentOnHealth = Reader.readInt();
        Stats.xpBought = Reader.readInt();
    }

    public static void convertKills() {
        Stats.kills = Reader.readInt();
        Stats.highScore = Reader.readInt();
        Stats.totalKills = Reader.readInt();
    }

    public static void convertDamage() {
        Stats.totalDamageDealt = Reader.readInt();
        Stats.bulletsFired = Reader.readInt();
        Stats.bulletsThatHit = Reader.readInt();
    }

    public static void convertOther() {
        Stats.timesCheated = Reader.readInt();
        Stats.timesQuit = Reader.readInt();
        Stats.itemsCrafted = Reader.readInt();
        Stats.diceGamesPlayed = Reader.readInt();
        Stats.slotGamesPlayed = Reader.readInt();
    }
}
