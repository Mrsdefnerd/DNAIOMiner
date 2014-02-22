package org.parabot.mrsdefnerd.aiominer.methods;

public class ExtraMethods {
	
	private static final String RUN_ENERGY_FIELD = "al";
	private static final String PASSWORD_FIELD = "hH";
	private static final String USERNAME_FIELD = "hG";
	private static final int NOTE_SETTING = 115;
	private static final int RUN_SETTING = 173;
	
	public static boolean isNotingEnabled() {
		return Settings.getSetting(NOTE_SETTING) == 1;
	}
	
	public static boolean isRunEnabled() {
		return Settings.getSetting(RUN_SETTING) == 0;
	}
	
	public static int getRunEnergy() {
		return (int) Reflection.getFieldValue(RUN_ENERGY_FIELD);
	}
	
	public static String getPassword() {
		return (String) Reflection.getFieldValue(PASSWORD_FIELD);
	}
	
	public static String getUsername() {
		return (String) Reflection.getFieldValue(USERNAME_FIELD);
	}

}
