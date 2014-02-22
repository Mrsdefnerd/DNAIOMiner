package org.parabot.mrsdefnerd.aiominer.constants;

import java.awt.Point;

import org.parabot.environment.input.Mouse;
import org.rev317.Loader;

/**
 * 
 * @author Everel, Dane
 * 
 * 
 * 
 */
public enum Tab {

	ATTACK(0, new Point(543, 188)), STATS(1, new Point(578, 178)), QUESTS(2, new Point(613, 181)), INVENTORY(3, new Point(640, 185)), EQUIPMENT(4, new Point(673, 179)), PRAYER(5, new Point(711, 184)), MAGIC(
			6, new Point(738, 183)), CLANCHAT(7, new Point(645, 477)), FRIENDS(8, new Point(573, 484)), IGNORE(9, new Point(612, 483)), LOGOUT(10, new Point(752, 15)), OPTIONS(11, new Point(677, 480)), EMOTES(
			12, new Point(708, 485)), MUSIC(13, new Point(746, 476)), UNKNOWN(-1, new Point(-1, -1));

	private int index;
	private Point point;
	
	private Tab(int index, Point point) {
		this.index = index;
		this.point = point;
	}

	/**
	 * Gets the Tab's index.
	 * 
	 * @return the index
	 */
	public final int getIndex() {
		return this.index;
	}
	
	/**
	 * Gets the Tab's point.
	 * 
	 * @return the point
	 */
	public final Point getPoint() {
		return this.point;
	}

	/**
	 * Opens the Tab.
	 */
	public final void open() {
		Mouse.getInstance().click(getPoint());
	}

	/**
	 * Returns true if the Tab is open.
	 */
	public final boolean isOpen() {
		return Loader.getClient().getOpenTab() == this.getIndex();
	}

	/**
	 * Returns the name of the Tab.
	 */
	public final String getName() {
		return Character.toUpperCase(name().charAt(0))
				+ name().toLowerCase().substring(1);
	}

	public final String toString() {
		return "Tab: [" + this.getName() + ", " + this.getIndex() + "]";
	}

	/**
	 * Gets the currently opened Tab.
	 * 
	 * @return the Tab
	 */
	public static final Tab getOpened() {
		return get(Loader.getClient().getOpenTab());
	}

	/**
	 * Opens a Tab with the provided index.
	 * 
	 * @param index
	 */
	public static final void open(int index) {
		Loader.getClient().openTab(index);
	}

	/**
	 * Opens the provided Tab.
	 * 
	 * @param Tab
	 *            the Tab.
	 */
	public static final void open(Tab Tab) {
		Tab.open();
	}

	/**
	 * Finds and opens a tab that matches the provided string.
	 * 
	 * @param Name
	 *            the tab name.
	 */
	public static final void open(String name) {
		for (Tab t : Tab.values()) {
			if (t.getName().equalsIgnoreCase(name)) {
				t.open();
				return;
			}
		}
	}

	/**
	 * Gets the name of the Tab with the matching provided index.
	 * 
	 * @param index
	 *            the Tab index.
	 * @return the name of the Tab.
	 */
	public static final String getName(int index) {
		for (Tab Tab : values()) {
			if (Tab.getIndex() == index) {
				return Tab.getName();
			}
		}
		return "Invalid Tab";
	}

	/**
	 * Gets the Tab with the provided index.
	 * 
	 * @param index
	 *            the index
	 * @return the Tab
	 */
	public static final Tab get(int index) {
		for (Tab Tab : values()) {
			if (Tab.getIndex() == index) {
				return Tab;
			}
		}
		return Tab.UNKNOWN;
	}

}
