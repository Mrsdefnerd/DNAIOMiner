package org.parabot.mrsdefnerd.aiominer.core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;

import org.parabot.environment.api.interfaces.Paintable;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.Category;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.ScriptManifest;
import org.parabot.environment.scripts.framework.Strategy;
import org.parabot.mrsdefnerd.aiominer.constants.Constants;
import org.parabot.mrsdefnerd.aiominer.data.Data;
import org.parabot.mrsdefnerd.aiominer.methods.Methods;
import org.parabot.mrsdefnerd.aiominer.strategies.AntiRandom;
import org.parabot.mrsdefnerd.aiominer.strategies.Banking;
import org.parabot.mrsdefnerd.aiominer.strategies.Dropping;
import org.parabot.mrsdefnerd.aiominer.strategies.Login;
import org.parabot.mrsdefnerd.aiominer.strategies.Mine;
import org.parabot.mrsdefnerd.aiominer.strategies.Traveling;
import org.rev317.api.events.MessageEvent;
import org.rev317.api.events.listeners.MessageListener;
import org.rev317.api.methods.BotMouse;
import org.rev317.api.methods.Skill;

@ScriptManifest(author = "Mrsdefnerd", category = Category.MINING, description = "Mines any ore in PKHonor", name = "DNAIOMiner", servers = { "PKHonor" }, version = 0.2D)
public class DNAIOMiner extends Script implements Paintable, MessageListener {
	
	ArrayList<Strategy> strategies = new ArrayList<>();
	
	public static UI ui;
	
	public static boolean isRunning = true;
	
	
	@Override
	public boolean onExecute() {
		ui = new UI();
		while (ui.isRunning && ui.isVisible())
			Time.sleep(50);
		Data.timeRan = System.currentTimeMillis();
		Data.startExp = Skill.MINING.getExperience();
		oresMinedThread();
		Collections.addAll(strategies, new AntiRandom(), new Login(), new Banking(), new Traveling(), new Dropping(), new Mine());
		provide(strategies);
		return true;
	}
	
	@Override
	public void onFinish() {
		isRunning = false;
	}
	
	@Override
	public void paint(Graphics g) {
		if (ui != null && !ui.isRunning) {
			g.setColor(new Color(Color.DARK_GRAY.getRed(), Color.DARK_GRAY.getGreen(), Color.DARK_GRAY.getBlue(), 100));
			g.fillRect(350, 240, 166, 98);
			g.setColor(Color.black);
			g.drawRect(350, 240, 166, 98);
			g.setColor(Color.white);
			g.drawString("State: "+Data.botState, 360, 255);
			g.drawString("Rock type: "+Data.rocks.toString(), 360, 270);
			g.drawString("Mining mode: "+Data.type.toString(), 360, 285);
			g.drawString("Ores mined (hr): "+Methods.formatNumber(Data.oresMined)+" ("+Methods.perHour(Data.oresMined)+")", 360, 300);
			g.drawString("Exp gained (hr): "+Methods.formatNumber(Data.gainedExp)+" ("+Methods.perHour(Data.gainedExp)+")", 360, 315);
			g.drawString("Runtime: "+Methods.runTime(Data.timeRan), 360, 330);
			Point p = new Point(BotMouse.getMouseX(), BotMouse.getMouseY());
			g.setColor(Constants.MOUSE_COLOR);
			g.fillRect(p.x - 10, p.y, 22, 3);
			g.fillRect(p.x, p.y - 10, 3, 22);
		}
	}
	
	public void oresMinedThread() {
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				while (isRunning) {
					try {
						if (Skill.MINING.getExperience() - Data.startExp != Data.gainedExp) {
							Data.oresMined++;
							Data.gainedExp = Skill.MINING.getExperience()
									- Data.startExp;
						}
					} catch (Exception e) {}
				}
			}
			
		});
		t.start();
	}

	@Override
	public void messageReceived(MessageEvent m) {
		if (m.getMessage().toLowerCase().contains("click the marked"))
			Data.shouldRemove = true;
		else if (m.getMessage().toLowerCase().contains("click the portal"))
			Data.shouldRemove = true;
		else if (m.getMessage().toLowerCase().contains("you have escaped"))
			Data.shouldEscape = false;
	}

}
