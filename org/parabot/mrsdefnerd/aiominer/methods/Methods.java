package org.parabot.mrsdefnerd.aiominer.methods;

import java.awt.Point;
import java.text.DecimalFormat;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.input.Mouse;
import org.parabot.mrsdefnerd.aiominer.constants.Tab;
import org.parabot.mrsdefnerd.aiominer.constants.Teleports;
import org.parabot.mrsdefnerd.aiominer.data.Data;
import org.parabot.mrsdefnerd.aiominer.interfaces.Condition;
import org.rev317.api.methods.Interfaces;
import org.rev317.api.methods.Npcs;
import org.rev317.api.methods.Players;
import org.rev317.api.methods.SceneObjects;
import org.rev317.api.wrappers.hud.Interface;
import org.rev317.api.wrappers.hud.Region;
import org.rev317.api.wrappers.interactive.Npc;
import org.rev317.api.wrappers.scene.SceneObject;

public class Methods {
	
	
	public static void teleport(Teleports teleport) {
		if (teleport.getRegion() != null && teleport.getRegion().equals(Region.TAB)) {
			Tab.MAGIC.open();
			Time.sleep(100);
			teleport.clickTeleport();
			Time.sleep(750);
			Point p = teleport.getInterfacePoint();
			if (p != null) {
				Mouse.getInstance().click(p);
				Time.sleep(750);
				Sleep.sleep(new Condition() {
	
					@Override
					public boolean validate() {
						return Players.getLocal().getAnimation() != -1;
					}
					
				}, 5000);
			}
		} else {
			Point p = teleport.getInterfacePoint();
			if (p != null) {
				Mouse.getInstance().click(p);
				Time.sleep(750);
				Sleep.sleep(new Condition() {
	
					@Override
					public boolean validate() {
						return Players.getLocal().getAnimation() != -1;
					}
					
				}, 5000);
			} else {
				Tab.MAGIC.open();
				Mouse.getInstance().click(new Point(684, 428));
				Time.sleep(100);
				teleport.clickTeleport();
				Time.sleep(750);
				Sleep.sleep(new Condition() {
					
					@Override
					public boolean validate() {
						return Players.getLocal().getAnimation() != -1;
					}
					
				}, 5000);
			}
		}
	}
	
	public static final Interface getInterfaceByText(boolean visible, String ... texts) {
		for (String text : texts)
			for (Interface i : Interfaces.getParentInterfaces())
				if (i != null && i.getChildren() != null)
					for (Interface c : i.getChildren())
						if (c != null && c.getText() != null && c.getText().toLowerCase().contains(text.toLowerCase()) && (visible ? c.isVisible() : true))
							return c;
		return null;
	}
	
	public static Npc getNearestNpc(int ... ids) {
		for (Npc n : Npcs.getNearest(ids))
			if (n != null)
				return n;
		return null;
	}
	
	public static Npc getNearestNpc(String ... names) {
		for (Npc n : Npcs.getNearest(names))
			if (n != null)
				return n;
		return null;
	}
	
	public static SceneObject getNearestObject(int ... ids) {
		for (SceneObject ob : SceneObjects.getNearest(ids))
			if (ob != null)
				return ob;
		return null;
	}
	
	public static boolean isAnimating() {
		return Players.getLocal().getAnimation() != -1 && Players.getLocal().getAnimation() != 1353;
	}
	
	public static String runTime(long i) {
	    DecimalFormat nf = new DecimalFormat("00");
	    long millis = System.currentTimeMillis() - i;
	    long hours = millis / (1000 * 60 * 60);
	    millis -= hours * (1000 * 60 * 60);
	    long minutes = millis / (1000 * 60);
	    millis -= minutes * (1000 * 60);
	    long seconds = millis / 1000;
	    	return nf.format(hours) + ":" + nf.format(minutes) + ":" + nf.format(seconds);
	}
	
	public static String formatNumber(int start) {
        DecimalFormat nf = new DecimalFormat("0.0");
        double i = start;
        if(i >= 1000000) {
            return nf.format((i / 1000000)) + "m";
        }
        if(i >=  1000) {
            return nf.format((i / 1000)) + "k";
        }
        return ""+start;
    }

	public static String perHour(int gained) {
		return formatNumber((int) ((gained) * 3600000D / (System.currentTimeMillis() - Data.timeRan)));
	}
	
}

