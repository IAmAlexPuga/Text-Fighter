package com.hotmail.kalebmarc.textfighter.player;

import com.hotmail.kalebmarc.textfighter.main.saves.Mapper;
import com.hotmail.kalebmarc.textfighter.main.saves.Reader;

public class Coins {

    private static int coins;
    private static int bank;


    private Coins() {
    }

    public static int get() {
        return coins;
    }

    public static void set(int amount, boolean add) {
        if (!add) {
            coins = amount;
        } else {
            coins += amount;
            if (amount < 0) Stats.totalCoinsSpent += -amount;
            if (coins < 0) coins = 0;
        }
    }

    public static void save(){
        Mapper.set("User.Balance", coins);
    }

    public static void load() {
        Coins.set(Mapper.getInteger("User.Balance"), false);
    }

    public static void convert() {
        Coins.set(Reader.readInt(), false);
    }
}