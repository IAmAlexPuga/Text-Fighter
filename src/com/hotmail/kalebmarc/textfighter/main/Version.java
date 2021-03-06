package com.hotmail.kalebmarc.textfighter.main;

class Version {
    private static final String VERSION = "4.9DEV";
    private static final String STAGE = "Alpha";
    private static final String DESC = "" //Award for "worse game description" goes to:
            //But seriously; add information that the user actually cares about
            + "Text-Fighter is a Text-Based\n"
            + "Fighter RPG game, completely\n"
            + "written in Java.\n\n"
            + "Text-Fighter is currently in Alpha stage\n"
            + "which means it's still in early development,\n"
            + "and will contain lots of bugs and missing features.";
    private static final String CHANGE_LOG = ""
            + "(Not compatible with previous saves)\n\n"

            + "New Stuff:\n"
            + "- Added new casino game: Lottery\n"
            + "- Added new casino game: Blackjack\n"
            + "- Updated information: Health\n"
            + "- Refractor weapons\n"
            + "- Refractor enemies\n"
            + "- Added critical hits\n"
            + "- Testing Directory\n"
            + "- Weapon Upgrade System\n"
            + "- Organize Classes/packages\n"
            + "- Separate battle menu "
            + "- Updated potion stats"
            + "- Multiple save files"
            +
            "\n"
            + "-\n\n"

            + "Bug Fixes:\n"
            + "- Fixed JUnit. Tests are now operable \n"
            + "- Confirm overwriting save\n"
            + " ";

    private Version() {
    }

    public static String get() {
        return VERSION;
    }

    public static String getStage() {
        return STAGE;
    }

    public static String getFull() {
        return STAGE + " " + VERSION;
    }

    public static String getDesc() {
        return DESC;
    }

    public static String getChange() {
        return CHANGE_LOG;
    }
}
