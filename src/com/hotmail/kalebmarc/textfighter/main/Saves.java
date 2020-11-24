package com.hotmail.kalebmarc.textfighter.main;

import com.hotmail.kalebmarc.textfighter.item.Armour;
import com.hotmail.kalebmarc.textfighter.item.FirstAid;
import com.hotmail.kalebmarc.textfighter.item.InstaHealth;
import com.hotmail.kalebmarc.textfighter.item.Power;
import com.hotmail.kalebmarc.textfighter.player.*;
import com.hotmail.kalebmarc.textfighter.main.saves.Mapper;
import com.hotmail.kalebmarc.textfighter.main.saves.Reader;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.representer.Representer;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;
import java.io.File;

/**
 * Created by Brendon Butler on 7/27/2016.
 */
public class Saves {

	private static DumperOptions options;
	private static File saveLocation;
	private static Representer representer;
	private static String path;
	private static Yaml yaml;
	private static boolean needOriginalReprompt;

	public static void createSavePath() {
		User.promptNameSelection();
		path = Saves.class.getProtectionDomain().getCodeSource().getLocation().getPath() + User.name() + ".TFsave";
		path = path.replace(".jar", "_" + User.name());
		path = path.replaceAll("%20", " ");
	}

	public static boolean checkExistingSaves() {
		setup();
		boolean addedSuccessfully = false;

		if(checkUserExists()) {
			addedSuccessfully = overwritePrompt();
		} else {
			Saves.save();
			addedSuccessfully = true;
		}

		return addedSuccessfully;
	}

	public static boolean checkUserExists() {
		boolean userExists = false;
		long fileSize = 0;
		FileReader reader = Reader.read(saveLocation);
		File potentialSaveFile = new File(String.valueOf(saveLocation));
		boolean fileExists = potentialSaveFile.exists();

		//Check if the file created is empty
		if(fileExists) {
			fileSize = potentialSaveFile.length();
		}
		if(fileSize != 0) {
			userExists = true;
		}

		return userExists;
	}

	public static boolean overwritePrompt() {
		boolean overwriteStatus = false;

			//Confirmation of overwrite
			Menu.saveOverwriteMenu(User.name());

			switch(Ui.getValidInt()){
				case 1:
					save();
					overwriteStatus = true;
					break;
				default:
					break;
			}

			return overwriteStatus;
	}

	public static void save() {
		setup();
		/*
		 * TODO: make a version checker that checks each part of a version ex: 1.4.1DEV
		 * then determine whether or not it's older, current or newer.
		 */
		Mapper.set("Version", Version.getFull());

		Health.saveHealth(); //Health------------------------
		FirstAid.save();
		InstaHealth.save();
		Health.saveTimesDied();
		Coins.save();//Coins---------------------------------
		Bank.save();
		Casino.save();
		Achievements.saveBought();
		Stats.saveCoins();
		Loan.save();
		Xp.save();//Xp---------------------------------------
		Potion.save();//Potions------------------------------
		Settings.saveDiff();//Settings-----------------------
		Cheats.saveEnableStatus();
		Ui.save();
		Stats.saveKills(); //Combat-------------------------
		WeaponInventory.save();
		Power.save();
		Stats.saveDamage();
		Armour.save();
		Enemy.save(); //Enemy-------------------------------
		Achievements.saveMain(); //Achs --------------------
		About.save(); //Other Stuff-------------------------
		Stats.saveOther();

		try {
			if (!saveLocation.exists())
				saveLocation.createNewFile();

			FileWriter writer = new FileWriter(saveLocation);

			writer.write(yaml.dump(Mapper.data));
			writer.flush();
			writer.close();
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	public static boolean load() {
		setup();

		FileReader reader = Reader.read(saveLocation);

		if (!checkUserExists()) {
			Ui.cls();
			Ui.println("------------------------------");
			Ui.println("Cannot find save file.  ");
			Ui.println("Starting a new game...  ");
			Ui.println("------------------------------");
			Ui.pause();

			Mapper.data = Collections.synchronizedMap(new LinkedHashMap<String, Object>());
			return false;
		}

		Mapper.data = Collections.synchronizedMap((Map<String, Object>) yaml.load(reader));

		Health.loadHealth(); //Health------------------------
		FirstAid.load();
		InstaHealth.load();
		Health.loadTimesDied();
		Coins.load();//Coins---------------------------------
		Bank.load();
		Casino.load();
		Achievements.loadBoughts();
		Stats.loadCoins();
		Loan.load();
		Xp.load();//Xp---------------------------------------
		Potion.load(); //Potions-----------------------------
		Settings.loadDiff(); //Settings----------------------
		Cheats.loadEnableStatus();
		Ui.load();
		Stats.loadKills(); //Combat-------------------------
		WeaponInventory.load();
		Power.load();
		Stats.loadDamage();
		Armour.load();
		Enemy.load(); //Enemy--------------------------------
		Achievements.loadMain(); //Achs----------------------
		About.load(); //Other Stuff--------------------------
		Stats.loadOther();

		return true;
	}

	private static void setup() {
		saveLocation = new File(path);

		if (!saveLocation.exists())
			try {
				saveLocation.createNewFile();
			} catch (IOException exception) {
				exception.printStackTrace();
			}

		setupDumper();

		yaml = new Yaml(representer, options);
		Mapper.data = Collections.synchronizedMap(new LinkedHashMap<String, Object>());
	}

	private static void setupDumper() {
		options = new DumperOptions();
		representer = new Representer();

		options.setIndent(2);
		options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
		options.setAllowUnicode(Charset.defaultCharset().name().contains("UTF"));
		representer.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
	}

	public static  boolean getNeedForReprompt() {
		return needOriginalReprompt;
	}

	public static boolean savesPrompt() {
		boolean loadSuccess = false;
		boolean reloadSavePrompt;
		needOriginalReprompt = true;
		createSavePath();
		do {
			reloadSavePrompt = false;
			needOriginalReprompt = false;
			Menu.savePromptMenu();

			switch (Ui.getValidInt()) {
				case 1:
					if (!load()) { //user does not exist
						loadSuccess = false; //loading was not successful
					}
					else {
						loadSuccess = true;  //loading was successful
					}
					break;
				case 2:
					if (!convert()) { //user changed mind
						reloadSavePrompt = true; //user decided not to convert save
						loadSuccess = false; //load is currently in limbo
					}
					break;
				default:
					needOriginalReprompt = true; //User exited without loading a file
					loadSuccess = false;
					break;
			}
		}while(reloadSavePrompt);

		return loadSuccess;
	}

	public static boolean convert() {
			Menu.convertSaveFileMenu();
			switch(Ui.getValidInt()){
				case 1:
					return false;
				case 2:
					break;
				default:
					return false;
			}


		try {
			File file = new File(path);

			if (!file.exists()) {
				Ui.println("File not found. Please put an \"_\" before your username in the save file.");
				System.exit(0); //TODO shouldn't just exit like this.... Go back to main menu
			}

			Reader.input = new Scanner(file);
			setup();
			Reader.readString();

			Health.convertHealth(); //Health------------------------
			FirstAid.convert();
			InstaHealth.convert();
			Health.convertTimesDied();
			Coins.convert(); //Coins--------------------------------
			Bank.convert();
			Casino.convert();
			Achievements.convertBoughts();
			Stats.convertCoins();
			Loan.convert();
			Xp.convert(); //Xp--------------------------------------
			Potion.convert();//Potions------------------------------
			Settings.convertDiff();//Settings-----------------------
			Cheats.convertEnableStatus();
			Ui.convert();
			Stats.convertKills(); //Combat--------------------------
			WeaponInventory.convert();
			Power.convert();
			Stats.convertDamage();
			Armour.convert();
			Enemy.convert(); //Enemy--------------------------------
			Achievements.convertMain(); //Achs----------------------
			About.convert(); //Other Stuff--------------------------
			Stats.convertOther();
			save();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return true;
	}
}