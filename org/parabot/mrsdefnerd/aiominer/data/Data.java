package org.parabot.mrsdefnerd.aiominer.data;

import org.parabot.mrsdefnerd.aiominer.constants.MiningType;
import org.parabot.mrsdefnerd.aiominer.constants.Rocks;

public class Data {
	
	public static Rocks rocks;
	public static MiningType type;
	public static int oresMined = 0;
	
	public static String botState = "Starting up..";
	
	public static long timeRan;
	public static int startExp;
	public static int gainedExp = 0;
	
	public static boolean shouldEscape = false;
	public static boolean shouldRemove = false;

}
