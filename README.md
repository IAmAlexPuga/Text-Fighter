Text-Fighter
==========

Text Fighter is a text-based RPG game. It's still in early development (Alpha stage), so there may be a lot of bugs/missing features. There isn't really any set goal or missions yet, but there's still a lot of things you can do. Fight a variety of enemies to get coins, gain XP, and unlock achievements. You can spend money on more weapons/supplies, and unlock items by leveling up. There's even a casino where you can gamble your hard-earned coins. If you aren't one who likes playing by the rules, you can use the built-in cheats to your advantage (or disadvantage). More in-depth documentation/information is available via the in-game help menus.


This is my first (and currently only "big") Java project, so this is meant as a learning experience for me. I've been lightly developing this over the past couple of years while I learn Java. 

Contributing
-------------
I'm not actively developing this at the moment, but that doesn't mean the project is dead! Feel free to open PR's and I'll check them out! Whether you want to fix a small bug, or implement a new feature- all contributions are welcome and encouraged. Check out the [issues](https://github.com/hhaslam11/Text-Fighter/issues) if you're looking for some stuff to do!

This project is very begginer friendly; I especially encourage new developers, or people learning java to contribute. I learned a lot developing this project, and hopefully you can learn from it as well! I'm always happy to give code-reviews or answer any questions; about the project, or about programming/java in general. Please don't hesitate to email me at `kalebmarc@hotmail.com`.


Installation
----------------
First, make sure you have the latest version of [Java](https://www.java.com) installed on your computer. Download the jar file from the latest [release](https://github.com/hhaslam11/Text-Fighter/releases). 
Simply clicking on the .jar file won't work (..yet). You need to run it in the command line.
Open your command line/terminal, navigate to the downloaded .jar and run `java -jar Text.Fighter.jar`. If you want to run Text Fighter without the splash screen and popups (No GUI; true text-based), simply run it with the `nogui` option: `java -jar Text.Fighter.jar nogui`.
*Text-Fighter should work on Windows, Mac, and Linux.* 

Modifications
-------------------
I've made it easy to add your own weapons/enemy types into the game via modifying the code. With just a couple lines of code, you can fully implement a new weapon or enemy with your own properties. This will automatically setup achievements, and even create a full information page in the help menu for your modification. Instructions on how to do this can be found commented in-line at the bottom of [Start.java](https://github.com/hhaslam11/Text-Fighter/blob/master/src/com/hotmail/kalebmarc/textfighter/main/Start.java)
 Feel free to fork your own copy, add your own enemies/weapons and then send a pull-request for me to add it to the code.

Documentation
---------------------
Pretty much all the information you need about the game mechanics is available in-game via the help menus (Accessible through the *home* menu). I'm not sure if I will add documentation about the code itself yet, that will be determined at a later time.

Upcoming
--------------
I'm planning to continue developing Text-Fighter for a while, as it's proven to be a good resource to help teach me Java. I don't have an end-goal for the project, but I do have a lot of things I want to eventually add. Everything I plan for Text Fighter is posted in [issues.](https://github.com/hhaslam11/Text-Fighter/issues)


In the beta stage, I want to add a better UI while still keeping the game mostly text-based. For example, having buttons to click on instead of typing numbers in, but still having the output be in text form.

*If you want to request any feature, open an [issue](https://github.com/hhaslam11/Text-Fighter/issues), and there's a good chance I'll add it.*

License
--------------
Published under the [MIT license](https://github.com/hhaslam11/Text-Fighter/blob/master/LICENSE)

## Milestone 1
--------------
**Bug Fixes**
- JUnit no longer crashes when running tests
- When users choose to start a new game with a save file name that already exists, the user now can confirm the overwrite or go back

**Updates**
- Updated health information
- Battle screen Quality of Life update
- Potion stats broken down into specific stats for different potions
- Users can have multiple save files with different names
- Refactored weapons and enemies
  * Now utilizes builder pattern and inheritance
- Added critical hits to weapons
- WeaponInventory class created to manage weapons
- Weapons can be quickly initialized with original game constants using the WeaponInitalization class
- New testing directory (JUnit)
  * Version Test
  * Critical Hit Tests
  * Weapon Refactoring Tests

**Other Notes**
- Documentation folder was added and consists of the following:
  * UML Diagrams of the original repository organized by package
  * Control Flow Diagrams and Flow/Activity Diagram used for White-box testing overwriting saves and allowing multiple saves
  
  ## Milestone 2
--------------
**Updates**
- Moved all instances of menu prints to Menu.java
- Implemented Command Pattern for user actions
- Refactored Game.java
  * Reduced complexity, coupling, and utilize command pattern
- Refactored Bank.java, Cheats.java, and Debug.java
  * Reduced complexity, coupling
  * Improved changeability, readability
- Refactored Saves.java
  * Reduced complexity, cohesion, and lines of code
  * Removed methods related to reading the save file into Reader.java
  * Removed methods related to mapping into Mapping.java
  * All classes involved in saving, loading, and converting now have save, load, and convert methods
      - Some have multiple respective methods as files are organized in the order of "Health", "Coins", "Xp", "Potions", "Settings", "Combat", "Enemy", "Achievements" and "Other" therefore calling different members of the same class to save, load, or convert at different times
- Refactored DiceGame.java
  * Broke down play method god class into smaller functions with one responsibility
