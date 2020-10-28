package com.hotmail.kalebmarc.textfighter.main;

import java.util.ArrayList;

/**
 * TODO make more use of this class. Move settings/user prefs here maybe?
 */
public class User {

    private static String playerName = "Player";

    public static final ArrayList<Weapon> arrayWeapon = new ArrayList<>();
    public static Weapon starting;
    public static Weapon currentWeapon = null;

    public static String name() {
        return playerName;
    }

    public static void setName(String name) {
        playerName = name;
    }

    public static void promptNameSelection() {

        Ui.cls();
        Ui.println("Please enter your username.");
        String name = Ui.getValidString();

        //Validate
        name = name.trim();
        if (name.equals("")) {
            Ui.println("Name cannot be blank. Using default name.");
            name = "Player";
        }

        playerName = name;

    }

    public static void chooseWeapon() { //Weapon array related
        while (true) {
            Ui.cls();
            Ui.println("----------------------------");
            Ui.println("Equip new weapon");
            Ui.println();

            if("GUN".equals(currentWeapon.getType()))
            {
                Ui.println("Ammo: " + currentWeapon.getAmmo());
            }
            Ui.println("Equipped weapon: " + currentWeapon.getName());
            Ui.println("----------------------------");
            int j = 0;
            int[] offset = new int[arrayWeapon.size()];
            for (int i = 0; i < arrayWeapon.size(); i++) {
                if (arrayWeapon.get(i).owns()) {
                    Ui.println((j + 1) + ") " + arrayWeapon.get(i).getName());
                    offset[j] = i - j;
                    j++;
                }
            }
            Ui.println((j + 1) + ") Back");

            while (true) {

                int menuItem = Ui.getValidInt();

                try {

                    //choices other than options in the array go here:
                    if (menuItem == (j + 1) || menuItem > j)
                        return;

                    //reverts back to Weapon indexing
                    menuItem--;
                    menuItem = menuItem + offset[menuItem];

                    //Testing to make sure the option is valid goes here:
                    if (!arrayWeapon.get(menuItem).owns) {
                        Ui.msg("You do not own this weapon!");
                        return;
                    }

                    currentWeapon = arrayWeapon.get(menuItem);
                    Ui.msg("You have equipped a " + arrayWeapon.get(menuItem).getName());
                    return;

                } catch (Exception e) {
                    Ui.println();
                    Ui.println(menuItem + " is not an option.");
                }
            }
        }
    }

    public static Weapon getCurrentWeapon() {
        if(currentWeapon == null)
        {

            currentWeapon = Game.fists;
        }
        return currentWeapon;
    } //Weapon array related

    static int getIndex(Weapon i) {
        return arrayWeapon.indexOf(i);
    } //Weapon array related

    public static void set(Weapon x) {
        currentWeapon = x;
    } //Weapon array related

    public static void set(int i) { //Weapon array related
        currentWeapon = arrayWeapon.get(i);
    }
}
