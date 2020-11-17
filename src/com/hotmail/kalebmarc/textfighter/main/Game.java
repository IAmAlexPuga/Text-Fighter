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
					}
					else {
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

			switch (Ui.getValidInt()) {
				case 1:
					battle();
					break;
				case 2:
					home();
					break;
				case 3:
					town();
					break;
				case 4:
					FirstAid.use();
					break;
				case 5:
					Menu.usePotionMenu();
					switch (Ui.getValidInt()) {
						case 1:
							Potion.use("survival");
							break;
						case 2:
							Potion.use("recovery");
							break;
						case 3:
							break;
						default:
							break;
					}
					break;
				case 6:
					Food.list();
					break;
				case 7:
					InstaHealth.use();
					break;
				case 8:
					Power.use();
					break;
				case 9:
					Ui.cls();
					Ui.popup("You ran away from the battle.", "Ran Away", JOptionPane.INFORMATION_MESSAGE);
					Enemy.encounterNew();
					break;
				case 10:
					Stats.timesQuit++;
					return;
				case 0:
					Cheats.cheatGateway();
					break;
				case 99:
					Debug.menu();
				default:
					break;
			}//Switch
		}//While loop
	}//Method

	private static void battle() {
		int fightPath = Random.RInt(100);

		if (Weapon.get().getName().equals("Sniper")) {
			if (fightPath <= 30) Enemy.get().dealDamage();
			if (fightPath > 30) sniper.dealDam();
		} else {
			if (fightPath <= 50) Enemy.get().dealDamage();
			if (fightPath > 50) Weapon.get().dealDam();
		}

		Ui.println("What would you like to do?");
		Ui.println("1) Continue Fighting");
		Ui.println("2) Return Home");
		Ui.println("----------------------------------------------------");

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

	private static void town() {

		int menuChoice;



		//TOWN MENU
		while (true) {

			Menu.welcomeTownMenu();

			menuChoice = Ui.getValidInt();

			switch (menuChoice) {
				case 1:
					Casino.menu();
					break;
				case 2:
					home();
					break;
				case 3:
					Bank.menu();
					break;
				case 4:
					Shop.menu();
					break;
				case 5:
					upgrade();
					break;
				case 6:
					return;
				default:
					break;
			}//Switch
		}//While Loop
	}//Method

	private static void home() {

		int menuChoice;

		//HOME MENU
		while (true) {

			Menu.welcomeHomeMenu();
			menuChoice = Ui.getValidInt();

			switch (menuChoice) {
				case 1:
					Weapon.choose();
					break;
				case 2:
					Armour.choose();
					break;
				case 3:
					Chest.view();
					break;
				case 4:
					Achievements.view();
					break;
				case 5:
					Stats.view();
					break;
				case 6:
					About.view(true);
					Achievements.viewedAbout = true;
					break;
				case 7:
					menu();
					break;
				case 8:
					Help.view();
				case 9:
					return;
				default:
					break;
			}//Switch
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