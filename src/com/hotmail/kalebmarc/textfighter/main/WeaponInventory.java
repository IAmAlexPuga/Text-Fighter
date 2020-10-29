package com.hotmail.kalebmarc.textfighter.main;

import java.util.ArrayList;

public class WeaponInventory {

    public static final ArrayList<Weapon> arrayWeapon = new ArrayList<>();
    public static Weapon starting;
    private static Weapon current = null;

    public static Weapon get() {
        return current;
    }

    static int getIndex(Weapon i) {
        return arrayWeapon.indexOf(i);
    }

    public static void set(Weapon x) {
        current = x;
    }

    public static void set(int i) {
        current = arrayWeapon.get(i);
    }

    public static void choose() {
        while (true) {
            Ui.cls();
            Ui.println("----------------------------");
            Ui.println("Equip new weapon");
            Ui.println();
            Ui.println("Ammo: " + current.getAmmo());
            Ui.println("Equipped weapon: " + current.getName());
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

                    current = arrayWeapon.get(menuItem);
                    Ui.msg("You have equipped a " + arrayWeapon.get(menuItem).getName());
                    return;

                } catch (Exception e) {
                    Ui.println();
                    Ui.println(menuItem + " is not an option.");
                }
            }
        }
    }

}
