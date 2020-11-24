package com.hotmail.kalebmarc.textfighter.player;

import com.hotmail.kalebmarc.textfighter.main.Cheats;
import com.hotmail.kalebmarc.textfighter.main.Handle;
import com.hotmail.kalebmarc.textfighter.main.Ui;
import com.hotmail.kalebmarc.textfighter.main.saves.Mapper;
import com.hotmail.kalebmarc.textfighter.main.saves.Reader;

import javax.swing.*;

public class Xp {

	//Variables
    private static int xp;
    private static int battleXp;
    private static int xpNeeded;
    private static int level;
	public static int total;

    private Xp() {
    }

    private static void levelUp() {
        if (level == 9) {
            Ui.popup("You've reached level 10!\nYou have been rewarded 250 coins!", "Level Up!", JOptionPane.INFORMATION_MESSAGE);
            level = 10;
            Coins.set(250, true);

		}else if(level > 100){
			Handle.error("Error - Level is greater than 100");
            level = 100;
		}else if(level == 99){
            level = 100;
            xp = 49000;
        }else {

			Ui.popup("You've leveled up! You are now level " + (level + 1) + "!\nYou have been rewarded 100 coins!", "Level Up!", JOptionPane.INFORMATION_MESSAGE);
			xp = 0;
			xpNeeded += 500;
			level++;
            Coins.set(100, true);
		}
		Achievements.check();
	}

	public static void set(int amount, boolean add) {

        if((level == 100) || Cheats.enabled()){
            return;
        }
        if (add){
            xp += amount;
            total += amount;
        } else {
            xp = amount;
        }
		if ((xp >= xpNeeded) && (xpNeeded != 0)){// (xpNeeded != 0) is so it doesn't try and level up when cheats are enabled (Which sets xp and xpNeeded to 0)
			int leftOverXp = xp - xpNeeded;
            levelUp();
            set(leftOverXp, true);
		}

	}
	
	public static int getBattleXp() {
		return battleXp;
	}
	
	public static void setBattleXp(int amount, boolean add) {
		if(add) {
			battleXp += amount;
		} else {
			battleXp = amount;
		}
	}

	public static void setLevel(int lvl){
        level = lvl;
	}

	public static void setOutOf(int outOf){
		xpNeeded = outOf;
	}
	public static void setAll(int current, int outOf, int lvl){
        xp = current;
        xpNeeded = outOf;
        level = lvl;
    }

    public static int get(){
        return xp;
    }

    public static int getOutOf(){
        return xpNeeded;
    }

    public static int getLevel(){
        return level;
    }

    public static String getFull(){
        return xp + "/" + xpNeeded + " xp";
    }

    public static void save(){
        Mapper.set("User.XP.Level", Xp.getLevel());
        Mapper.set("User.XP.Needed", Xp.getOutOf());
        Mapper.set("User.XP.Amount", Xp.get());
        Mapper.set("User.XP.Total", Xp.total);
        Mapper.set("User.XP.battleXp", Xp.getBattleXp());

    }

    public static void load() {
        Xp.setLevel(Mapper.getInteger("User.XP.Level"));
        Xp.setOutOf(Mapper.getInteger("User.XP.Needed"));
        Xp.set(Mapper.getInteger("User.XP.Amount"), false);
        Xp.total = Mapper.getInteger("User.XP.Total");
        Xp.setBattleXp(Mapper.getInteger("User.XP.battleXp"), false);
    }

    public static void convert() {
        Xp.setLevel(Reader.readInt());
        Xp.setOutOf(Reader.readInt());
        Xp.set(Reader.readInt(), false);
        Xp.total = Reader.readInt();
        Xp.setBattleXp(Reader.readInt(), false);
    }
}