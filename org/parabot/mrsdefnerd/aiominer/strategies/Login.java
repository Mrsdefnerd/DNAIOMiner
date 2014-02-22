package org.parabot.mrsdefnerd.aiominer.strategies;

import java.awt.Point;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.input.Keyboard;
import org.parabot.environment.input.Mouse;
import org.parabot.environment.scripts.framework.Strategy;
import org.parabot.mrsdefnerd.aiominer.data.Data;
import org.parabot.mrsdefnerd.aiominer.interfaces.Condition;
import org.parabot.mrsdefnerd.aiominer.methods.ExtraMethods;
import org.parabot.mrsdefnerd.aiominer.methods.Sleep;
import org.rev317.api.methods.Game;

public class Login implements Strategy {

	@Override
	public boolean activate() {
		try {
			return !Game.isLoggedIn() && Data.username.length() > 0 && Data.password.length() > 0;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public void execute() {
		Data.botState = "Logging in..";
		try {
			if (ExtraMethods.getUsername() == null || !ExtraMethods.getUsername().toLowerCase().equals(Data.username.toLowerCase())) {
				Mouse.getInstance().click(new Point(368, 254));
				Time.sleep(500);
				for (int i = 0; i < 30; i++)
					Keyboard.getInstance().clickKey(8);
				Keyboard.getInstance().sendKeys(Data.username);
				Time.sleep(750);
			} else if (ExtraMethods.getPassword() == null || !ExtraMethods.getPassword().toLowerCase().equals(Data.password.toLowerCase())) {
				Mouse.getInstance().click(new Point(368, 267));
				Time.sleep(500);
				for (int i = 0; i < 30; i++)
					Keyboard.getInstance().clickKey(8);
				Keyboard.getInstance().sendKeys(Data.password);
				Time.sleep(750);
			} else if (ExtraMethods.getPassword().toLowerCase().equals(Data.password.toLowerCase()) &&
					ExtraMethods.getUsername().toLowerCase().equals(Data.username.toLowerCase())) {
				Mouse.getInstance().click(303, 313, true);
				Time.sleep(1000);
				Sleep.sleep(new Condition() {
	
					@Override
					public boolean validate() {
						return !Game.isLoggedIn();
					}
					
				}, 15000);
				Time.sleep(7500);
			}
		} catch (Exception e) {}
	}
	
}