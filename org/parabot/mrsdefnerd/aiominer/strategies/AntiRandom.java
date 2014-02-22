package org.parabot.mrsdefnerd.aiominer.strategies;

import java.awt.Point;
import java.util.ArrayList;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.input.Mouse;
import org.parabot.environment.scripts.framework.Strategy;
import org.parabot.mrsdefnerd.aiominer.data.Data;
import org.parabot.mrsdefnerd.aiominer.methods.Methods;
import org.rev317.api.methods.Npcs;
import org.rev317.api.methods.Players;
import org.rev317.api.methods.SceneObjects;
import org.rev317.api.wrappers.hud.Region;
import org.rev317.api.wrappers.interactive.Npc;
import org.rev317.api.wrappers.scene.SceneObject;
import org.rev317.api.wrappers.scene.Tile;

public class AntiRandom implements Strategy {

	private boolean antiRandom() {
		for (Npc n : Npcs.getNearest())
			if (n != null && n.getDisplayedText() != null && n.getDisplayedText().toLowerCase().contains(Players.getLocal().getName().toLowerCase()))
				return true;
		return false;
	}
	
	private Npc getRandomTarget() {
		for (Npc n : Npcs.getNearest())
			if (n != null && n.getDisplayedText() != null && n.getDisplayedText().toLowerCase().contains(Players.getLocal().getName().toLowerCase()))
				return n;
		return null;
	}
	
	private boolean atEvilBob() {
		return new Tile(2523, 4779, 0).distanceTo() <= 50;
	}
	
	private ArrayList<SceneObject> getPortalsArray() {
		ArrayList<SceneObject> portalArray = new ArrayList<>();
		for (SceneObject ob : SceneObjects.getNearest())
			if (ob != null && ob.getId() == 8987) 
				portalArray.add(ob);
		return portalArray;
	}
	
	private SceneObject getNearest(ArrayList<SceneObject> object) {
		int distance = 1000;
		SceneObject currentOb = null;
		for (SceneObject ob : object)
			if (ob.distanceTo() < distance) {
				currentOb = ob;
				distance = ob.distanceTo();
			}
		return currentOb;
	}
	
	private boolean timedOut(long start, int timeout) {
		return (System.currentTimeMillis() - start < timeout);
	}
	
	@Override
	public boolean activate() {
		return antiRandom() || atEvilBob();
	}

	@Override
	public void execute() {
		try {
			if (Methods.getInterfaceByText(true, "to continue") != null) {
				Mouse.getInstance().click(new Point((int) Methods.getInterfaceByText(true, "to continue").getPoint(Region.CHATBOX).getX() + 100, (int) Methods.getInterfaceByText(true, "to continue").getPoint(Region.CHATBOX).getY() + 10));
				Time.sleep(750, 1000);
			} else {
				if (!atEvilBob()) {
					Npc n = getRandomTarget();
					if (n != null) {
						if (n.isOnScreen()) {
							if (n.interact("Talk-to"))
								Time.sleep(750, 1000);
						} else {
							n.getLocation().clickMM();
							Time.sleep(750, 1000);
						}
					}
				} else {
					ArrayList<SceneObject> portals = getPortalsArray();
					Data.shouldEscape = true;
					long startTime = System.currentTimeMillis();
					while (portals.size() > 0 && timedOut(startTime, 30000) && Data.shouldEscape) {
						SceneObject portal = getNearest(portals);
						if (portal != null) {
							if (portal.isOnScreen()) {
								if (portal.interact("Enter")) {
									Time.sleep(750, 1250);
									if (Data.shouldRemove) {
										portals.remove(portal);
										Data.shouldRemove = false;
									}
								}
							} else {
								portal.getLocation().clickMM();
							}
						} else {
							portals.clear();
						}
					}
				}
			}
		} catch (Exception e) {}
	}
	
}