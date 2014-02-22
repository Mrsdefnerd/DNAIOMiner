package org.parabot.mrsdefnerd.aiominer.constants;

import java.awt.Point;

import org.parabot.environment.input.Mouse;
import org.parabot.mrsdefnerd.aiominer.methods.Methods;
import org.rev317.api.wrappers.hud.Interface;
import org.rev317.api.wrappers.hud.Region;

public enum Teleports {
	
	HOME(new Point(570, 240), null, ""),
	EDGEVILLE(new Point(571, 287), null, ""),
	COMBAT_TRAINING_LUMBRIDGE(new Point(640, 285), Region.CHATBOX, "lvl 1 -"),
	COMBAT_TRAINING_RELEKKA(new Point(640, 285), Region.CHATBOX, "lvl 11 -"),
	COMBAT_TRAINING_WARRIORS_GUILD(new Point(640, 285), Region.CHATBOX, "lvl 51 -"),
	COMBAT_TRAINING_APE_ATOLL(new Point(640, 285), Region.CHATBOX, "lvl 91 -"),
	SKILLING_THIEVING(new Point(717, 285), Region.CHATBOX, "Thieving"),
	SKILLING_WOODCUTTING(new Point(717, 285), Region.CHATBOX, "Woodcutting"),
	SKILLING_MINING(new Point(717, 285), Region.CHATBOX, "Mining"),
	SKILLING_SMITHING(new Point(717, 285), Region.CHATBOX, "Smithing"),
	SKILLING_FISHING(new Point(717, 285), Region.CHATBOX, "Fishing", "More skilling"),
	SKILLING_FARMING(new Point(717, 285), Region.CHATBOX, "Farming", "More skilling"),
	SKILLING_CRAFTING(new Point(717, 285), Region.CHATBOX, "Crafting", "More skilling"),
	SKILLNG_GNOME_AGILITY(new Point(717, 285), Region.CHATBOX, "Gnome agility", "More skilling"),
	SKILLING_SLAYER(new Point(717, 285), Region.CHATBOX, "Slayer", "More skilling"),
	SKILLING_HUNTER(new Point(717, 285), Region.CHATBOX, "Hunter", "More skilling"),
	SKILLING_SUMMONING(new Point(717, 285), Region.CHATBOX, "Summoning", "More skilling"),
	LUMBRIDGE(new Point(644, 313), Region.CHATBOX, "Lumbridge"),
	SEERS_VILLAGE(new Point(644, 313), Region.CHATBOX, "Lumbridge"),
	FALADOR(new Point(644, 313), Region.CHATBOX, "Lumbridge"),
	CANAFIS(new Point(644, 313), Region.CHATBOX, "Lumbridge"),
	CATHERBY(new Point(644, 313), Region.CHATBOX, "Catherby", "More cities"),
	AL_KHARID(new Point(644, 313), Region.CHATBOX, "Al Kharid", "More cities"),
	PORT_PHASMATYS(new Point(644, 313), Region.CHATBOX, "Port Phasma", "More cities"),
	YANILLE(new Point(644, 313), Region.CHATBOX, "Yanille", "More cities"),
	BARROWS(new Point(620, 333), Region.CHATBOX, "Barrows"),
	PEST_CONTROL(new Point(620, 333), Region.CHATBOX, "Pest Control"),
	DUEL_ARENA(new Point(620, 333), Region.CHATBOX, "Duel arena"),
	FIGHT_CAVES(new Point(620, 333), Region.CHATBOX, "Tzhaar fight cave", "More minigames"),
	FIGHT_PITS(new Point(620, 333), Region.CHATBOX, "Tzhaar fight pit", "More minigames"),
	CASTLE_WARS(new Point(620, 333), Region.CHATBOX, "Castle wars", "More minigames"),
	CLAN_WARS(new Point(620, 333), Region.CHATBOX, "Clan wars", "More minigames"),
	MONSTERS_DRAGONS(new Point(570, 360), Region.TAB, "Dragons"),
	MONSTER_DAGANNOTHS(new Point(570, 360), Region.TAB, "Dagannoths"),
	MONSTER_KQUEEN(new Point(570, 360), Region.TAB, "KQueen"),
	MONSTER_GODWARS(new Point(570, 360), Region.TAB, "GodWars"),
	MONSTER_KBD(new Point(570, 360), Region.TAB, "KBD");
	
	private Point point;
	private String[] interactions;
	private Region region;
	
	Teleports(Point point, Region region, String ... interactions) {
		this.point = point;
		this.region = region;
		this.interactions = interactions;
	}
	
	public Region getRegion() {
		return region;
	}
	
	public Point getPoint() {
		return point;
	}
	
	public String[] getInteractions() {
		return interactions;
	}
	
	public void clickTeleport() {
		Mouse.getInstance().click(getPoint());
	}
	
	public Point getInterfacePoint() {
		Interface i;
		if (getRegion() != null && getRegion().equals(Region.TAB))
			i = Methods.getInterfaceByText(false, getInteractions());
		else
			i = Methods.getInterfaceByText(true, getInteractions());
		if (i != null && getRegion() != null) {
			if (getRegion().equals(Region.TAB))
				return new Point((int) i.getPoint(getRegion()).getX(),(int) i.getPoint(getRegion()).getY() + 10);
			else
				return new Point((int) i.getPoint(getRegion()).getX() + 100,(int) i.getPoint(getRegion()).getY() + 10);
		}
		return null;
	}
	

}
