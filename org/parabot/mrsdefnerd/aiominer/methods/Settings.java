package org.parabot.mrsdefnerd.aiominer.methods;


public class Settings {
	
	private static final String FIELD_NAME = "aa";
	
	public static int[] getSettings() {
		return (int[]) Reflection.getFieldValue(FIELD_NAME);
	}
	
	public static int getSetting(int setting) {
		return getSettings()[setting];
	}

}
