package com.hotmail.kalebmarc.textfighter.main;

import com.hotmail.kalebmarc.textfighter.item.*;
import com.hotmail.kalebmarc.textfighter.player.*;

import java.util.Scanner;

import javax.swing.JOptionPane;

import static com.hotmail.kalebmarc.textfighter.player.Health.getStr;
import static com.hotmail.kalebmarc.textfighter.player.Health.upgrade;
import static com.hotmail.kalebmarc.textfighter.player.Settings.menu;
import static com.hotmail.kalebmarc.textfighter.player.Settings.setDif;

public class Game {
	private Game() {
	}
	public static MenuExuction menuExec = new MenuExuction();
	//Enemies
	public static Enemy darkElf;
	public static Enemy ninja;
	public static Enemy giantSpider;
	public static Enemy zombie;
	public static Enemy goblin;
	public static Enemy ghost;
	public static Enemy barbarian;
	public static Enemy giantAnt;
	public static Enemy evilUnicorn;
	public static Enemy ogre;

	//Weapons
	public static Weapon fists;
	public static Weapon baseballBat;
	public static Weapon knife;
	public static Weapon pipe;
	public static Weapon pistol;
	public static Weapon smg;
	public static Weapon shotgun;
	public static Weapon rifle;
	public static Weapon sniper;

	//Amours
	public static Armour none = new Armour("None", 0, 0, 1);//DO NOT REMOVE
	public static Armour basic = new Armour("Basic", 400, 15, 5);
	public static Armour advanced = new Armour("Advanced", 750, 30, 7);

	//Food
	//TODO when the StatusEffect system is implemented, change effect types
	public static Food apple       = new Food("Apple",         "A boring 'ol apple.",                StatusEffect.type.HEALTH, Food.type.FRUIT,      5);
	public static Food orange      = new Food("Orange",        "Sort of like an apple, but orange.", StatusEffect.type.HEALTH, Food.type.FRUIT,      5);
	public static Food dragonfruit = new Food("Dragon Fruit",  "Unfortunately, not a real dragon.",  StatusEffect.type.HEALTH, Food.type.FRUIT,      10);
	public static Food meat        = new Food("Chunk of meat", "Probably not rotten.",               StatusEffect.type.HEALTH, Food.type.MEAT_OTHER, 15);
	public static Food mushroom    = new Food("Mushroom",      "The good kind!",                     StatusEffect.type.HEALTH, Food.type.OTHER,      5);
	public static Food fish        = new Food("Fish",          "Found in rivers and lakes.",         StatusEffect.type.HEALTH, Food.type.MEAT_FISH,  15);

	private static Scanner scan = new Scanner(System.in);

	public static void start() {
		// loads the users game. If no user save data was found
		// create a new one
		loadGame();

		// main game loop
		int userInput = 0;
		while (true) {

			//Runs all the tests and clears the screen
			if (Stats.kills > Stats.highScore) Stats.highScore = Stats.kills;
			Achievements.check();
			Saves.save();
			Ui.cls();

			/*
			 * MAIN GAME MENU
			 * Able to fight and go to other places from here
			 */
			Menu.mainGameMenu();
			userInput = Ui.getValidInt();
			menuExec.mainGameCommands.get(userInput).invoke();
			if(userInput == 10) {
				return;
			}

		}//While loop
	}//Method

	private static void loadGame() {
		boolean loadedSuccessfully = false;

		do {
			/*
			 * Asks if the user wants to load from the save file
			 */
			Menu.loadGameMenu();

			int choice = Ui.getValidInt();

			switch (choice) {
				case 1:
					if (Saves.savesPrompt()) {
						loadedSuccessfully = true;
						break;
					}
					else if (Saves.getNeedForReprompt()) {//User exits load saves screen
						loadedSuccessfully = false;
						break;
					} else {
						setDif(getDifficulty(), true, false);
						Health.set(100, 100);
						Enemy.encounterNew();
						Saves.save();
						loadedSuccessfully = true;
						break;
					}
				default:
					setDif(getDifficulty(), true, false);
					Health.set(100, 100);
					Enemy.encounterNew();
					Saves.createSavePath();
					if(Saves.checkExistingSaves())
					{
						loadedSuccessfully = true;
					}
					break;
			}
		}while(!loadedSuccessfully);
	}


	public static void battle() {
		int fightPath = Random.RInt(100);

		if (Weapon.get().getName().equals("Sniper")) {
			if (fightPath <= 30) Enemy.get().dealDamage();
			if (fightPath > 30) sniper.dealDam();
		} else {
			if (fightPath <= 50) Enemy.get().dealDamage();
			if (fightPath > 50) Weapon.get().dealDam();
		}

		Menu.contFightingMenu();

		switch (Ui.getValidInt()) {
			case 1:
				battle();
				break;
			case 2:
				return;
			default:
				break;
		}
	}

	public static void town() {

		int menuChoice;

		//TOWN MENU
		while (true) {

			Menu.welcomeTownMenu();
			menuChoice = Ui.getValidInt();
			if(menuChoice == 6) {
				return;
			}
			menuExec.townCommands.get(menuChoice).invoke();

		}//While Loop
	}//Method

	public static void home() {

		int menuChoice;

		//HOME MENU
		while (true) {
			Menu.welcomeHomeMenu();
			menuChoice = Ui.getValidInt();
			if(menuChoice == 9)
			{
				return;
			}
			menuExec.homeCommands.get(menuChoice).invoke();

		}//While loop
	}//Method

	private static String getDifficulty() {
		
		/*
		 * DIFFICULTY SELECTION
		 * Prompts user to get what difficulty
		 * they want to play on. Sets variables
		 * according.
		 */
		Menu.difficultyMenu();

		if (!scan.hasNextInt()) {
			Ui.cls();
			return "Easy";
		} else {
			int difficultyChoice = scan.nextInt();
			if (difficultyChoice == 2) {
				Ui.cls();
				return "Hard";
			} else {
				Ui.cls();
				return "Easy";
			}
		}
	}


}