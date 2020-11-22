package com.hotmail.kalebmarc.textfighter.main;

import com.hotmail.kalebmarc.textfighter.item.Armour;
import com.hotmail.kalebmarc.textfighter.item.FirstAid;
import com.hotmail.kalebmarc.textfighter.player.*;

import static com.hotmail.kalebmarc.textfighter.player.Health.getStr;

class Menu {
	
	private Menu(){}

	public static void contFightingMenu() {
        Ui.println("What would you like to do?");
        Ui.println("1) Continue Fighting");
        Ui.println("2) Return Home");
        Ui.println("----------------------------------------------------");
    }
	public static void usePotionMenu() {
        Ui.cls();
        Ui.println("Which potion would you like to use?");
        Ui.println("1) Survival Potion");
        Ui.println("2) Recovery Potion");
        Ui.println("3) Back");
    }

	public static void loadGameMenu() {
        Ui.cls();
        Ui.println("____________________________________________");
        Ui.println("|                                           |");
        Ui.println("|       Do you want to load your game       |");
        Ui.println("|            from save file?                |");
        Ui.println("|                                           |");
        Ui.println("| 1) Yes                                    |");
        Ui.println("| 2) No, Start a new game                   |");
        Ui.println("|___________________________________________|");
    }
	public static void mainGameMenu() {
        Ui.println("Text-Fighter " + Version.getFull());
        Ui.println("------------------------------------------------------------------");
        //Displays only if cheats are activated
        if (Cheats.enabled()) {
            Ui.println("CHEATS ACTIVATED");
        }
        Ui.println(Settings.godModeMsg());
        //------------------
        Ui.println("--Score Info--");
        Ui.println("     Level " + Xp.getLevel() + "      " + Xp.getFull());
        Ui.println("     Kill Streak: " + Stats.kills);
        Ui.println("     Highest Kill Streak: " + Stats.highScore);
        Ui.println("--" + User.name() + "--");
        Ui.println("     Health: " + getStr());
        Ui.println("     Coins: " + Coins.get());
        Ui.println("     First-Aid kits: " + FirstAid.get());
        Ui.println("     Potions: ");
        Ui.println("          Survival: " + Potion.get("survival"));
        Ui.println("          Recovery: " + Potion.get("recovery"));
        Ui.println("     Equipped armour: " + Armour.getEquipped().toString());
        Ui.println("     Equipped Weapon: " + WeaponInventory.get().getName());
        //Displays ammo only if a weapon is equipped
        WeaponInventory.get().displayAmmo();
        //--------------------
        Ui.println("--Enemy Info--");
        Ui.println("     Enemy: " + Enemy.get().getName());
        Ui.println("     Enemy Health: " + Enemy.get().getHeathStr());
        Ui.println("     Enemy's First Aid Kit's: " + Enemy.get().getFirstAidKit());
        Ui.println("------------------------------------------------------------------");
        Ui.println("1) Go to battle");
        Ui.println("2) Go Home");
        Ui.println("3) Go to the town");
        Ui.println("4) Use First-Aid kit");
        Ui.println("5) Use Potion");
        Ui.println("6) Eat Food");
        Ui.println("7) Use Insta-Health");
        Ui.println("8) Use POWER");
        Ui.println("9) Run From Battle (You will lose any XP earned)");
        Ui.println("10) Quit Game (Game will automatically be saved)");
        Ui.println("------------------------------------------------------------------");
    }
	public static void difficultyMenu() {
        Ui.cls();
        Ui.println("_____________________________________________");
        Ui.println("|                                           |");
        Ui.println("|       What difficulty would you           |");
        Ui.println("|            like to play on?               |");
        Ui.println("|                                           |");
        Ui.println("| 1) Easy                                   |");
        Ui.println("| 2) Hard                                   |");
        Ui.println("|___________________________________________|");
    }

	public static void welcomeHomeMenu() {
        Ui.cls();
        Ui.println("------------------------------------------------------------------");
        Ui.println("                          WELCOME HOME                            ");
        Ui.println("--Score Info--");
        Ui.println("     Kill Streak: " + Stats.kills);
        Ui.println("     Highest Kill Streak: " + Stats.highScore);
        Ui.println("--Player Info--");
        Ui.println("     Health: " + getStr());
        Ui.println("     Coins: " + Coins.get());
        Ui.println("     First-Aid kits: " + FirstAid.get());
        Ui.println("     Potions: " + (Potion.get("survival") + Potion.get("recovery")));
        Ui.println("     Equipped Weapon: " + WeaponInventory.get().getName());
        Ui.println("------------------------------------------------------------------");
        Ui.println("1) Equip weapon");
        Ui.println("2) Equip Armour");
        Ui.println("3) View Item Chest");
        Ui.println("4) Achievements");
        Ui.println("5) Stats");
        Ui.println("6) About");
        Ui.println("7) Settings");
        Ui.println("8) Help");
        Ui.println("9) Back");
        Ui.println("------------------------------------------------------------------");
    }

	public static void welcomeTownMenu() {
        Ui.cls();
        Ui.println("------------------------------------------------------------------");
        Ui.println("                      WELCOME TO THE TOWN                         ");
        Ui.println("--Score Info--");
        Ui.println("     Kill Streak: " + Stats.kills);
        Ui.println("     Highest Kill Streak: " + Stats.highScore);
        Ui.println("--Player Info--");
        Ui.println("     Health: " + getStr());
        Ui.println("     Coins: " + Coins.get());
        Ui.println("     First-Aid kits: " + FirstAid.get());
        Ui.println("     Potions: ");
        Ui.println("          Survival: " + Potion.get("survival"));
        Ui.println("          Recovery: " + Potion.get("recovery"));
        Ui.println("     Equipped Weapon: " + WeaponInventory.get().getName());
        Ui.println("------------------------------------------------------------------");
        Ui.println("1) Casino");
        Ui.println("2) Home");
        Ui.println("3) Bank");
        Ui.println("4) Shop");
        Ui.println("5) Upgrade Health");
        Ui.println("6) Back");
        Ui.println("------------------------------------------------------------------");
    }

	public static void load(){
		while(true){
			
			Ui.cls();
			//Menu Screen
			Ui.println("_____________________________________________");
			Ui.println("|          WELCOME TO TEXT FIGHTER          |");
			Ui.println("|        A Text-Based Fighting Game         |");
			Ui.println("|*******************************************|");
			Ui.println("|                                           |");
			Ui.println("|   To get started, Type in a number below  |");
			Ui.println("|             and press enter.              |");
			Ui.println("|                                           |");
			Ui.println("| 1) Start Game                             |");
			Ui.println("| 2) About Game                             |");
			Ui.println("| 3) Exit                                   |");
			Ui.println("|             www.TextFighter.tk            |");
			Ui.println("|___________________________________________|");

            switch (Ui.getValidInt()) {
                case 1:
                    Ui.cls();
                    Ui.guiEnabled = false;
                    Game.start();

                    //Saves the game before exiting
                    Saves.save();
                    return;
                case 2:
                    About.view(false);
                    break;
                case 3:
                    return;
                default:
                    break;
            }
        }//Loop
    }//Method
}//Class
