package com.hotmail.kalebmarc.textfighter.main;

import com.hotmail.kalebmarc.textfighter.main.saves.Mapper;
import com.hotmail.kalebmarc.textfighter.main.saves.Reader;
import com.hotmail.kalebmarc.textfighter.player.Coins;
import com.hotmail.kalebmarc.textfighter.player.Stats;
import com.hotmail.kalebmarc.textfighter.player.Xp;

public class Bank {

    private static double interest;
    private static int balance;
    private static int amount;

    public static void menu() {

        //Makes sure user level 2
        if (Xp.getLevel() < 2) {
            Ui.msg("You have to be at least level 2 to use the bank.");
            return;
        }

        while (true) {

            Menu.bankMenu(interest, get(), Coins.get());

            switch (Ui.getValidInt()) {
                case 1:
                    selectDeposit();
                    break;
                case 2:
                    selectWithdraw();
                    break;
                case 3:
                    Loan.menu();
                    break;
                case 4:
                    return;
            }
        }
    }

    public static int get() {
        return balance;
    }

    public static void set(int amount, boolean add) {
        if (!add) {
            balance = amount;
        } else {
            balance += amount;
            if (balance < 0) balance = 0;
        }
    }

    public static void setInterest(double price) {
        interest = price;
    }

    private static void selectWithdraw() {
        Ui.cls();

        //Input
        Ui.println("How much money would you like to withdraw?");
        Ui.println("You currently have " + get() + " coins in your bank.");
        do {
            amount = Ui.getValidInt();
            if (amount > get()) {
                Ui.println("You don't have enough coins in your bank. You only have " + get() + " coins.");
                amount = -1;
            }
        } while (amount < 0);

        //Withdraw
        withdraw(amount);
    }

    private static void withdraw(int amount) {
        //Calculation
        Coins.set(amount, true);
        set(-amount, true);

        //Result
        Ui.cls();
        Ui.println("Amount withdrawn: " + amount);
        Ui.println("Current Balance: " + get());
        Ui.pause();
    }

    private static void selectDeposit() {
        if (Loan.hasLoan()) {
            Ui.msg("You can not deposit coins until you pay off your loan!");
        } else {
            Ui.println("How much money would you like to deposit? (You will have to pay " + (interest * 100) + "% of this)");
            Ui.println("You currently have " + Coins.get() + " coins.");
            do {
                amount = Ui.getValidInt();
                if (amount > Coins.get()) {
                    Ui.println("You don't have enough coins. You only have " + Coins.get() + " coins.");
                    amount = -1;
                }
            } while (amount < 0);
            if (amount == 0) return;

            //Deposit
            deposit(amount, interest);
        }
    }

    private static void deposit(int amount, double interest) {

        //Get interest
        interest = interest * amount;
        if (amount < 10) interest = 1;

        //Take coins from player
        Coins.set(-amount, true);

        //Take away interest amount
        amount -= Math.round(interest);
        Stats.totalCoinsSpent += Math.round(interest);
        Stats.coinsSpentOnBankInterest += Math.round(interest);

        //Add remaining coins to bank account
        set(amount, true);

        //Display
        Ui.cls();
        Ui.println("Amount Deposited: " + amount + " coins");
        Ui.println("Interest Paid: " + Math.round(interest) + " coins");
        Ui.println("Current Balance: " + get() + " coins");
        Ui.pause();
    }

    public static void save() {
        Mapper.set("Bank.Balance",balance);
    }

    public static void load() {
        set(Mapper.getInteger("Bank.Balance"), false);
    }

    public static void convert() {
        set(Reader.readInt(), false);
    }
}
