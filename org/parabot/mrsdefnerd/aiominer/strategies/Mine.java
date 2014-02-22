package org.parabot.mrsdefnerd.aiominer.strategies;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.parabot.mrsdefnerd.aiominer.data.Data;
import org.parabot.mrsdefnerd.aiominer.interfaces.Condition;
import org.parabot.mrsdefnerd.aiominer.methods.Methods;
import org.parabot.mrsdefnerd.aiominer.methods.Sleep;
import org.rev317.api.methods.Bank;
import org.rev317.api.methods.Inventory;
import org.rev317.api.methods.Players;
import org.rev317.api.wrappers.scene.SceneObject;

public class Mine implements Strategy {

	@Override
	public boolean activate() {
		return !Methods.isAnimating() && !Inventory.isFull() && Methods.getNearestObject(Data.rocks.getRockIds()) != null && Methods.getNearestObject(Data.rocks.getRockIds()).distanceTo() <= 18;
	}

	@Override
	public void execute() {
		Data.botState = "Mining..";
		if (Bank.isOpen()) {
			Bank.close();
			return;
		}
		SceneObject rock = Methods.getNearestObject(Data.rocks.getRockIds());
		if (rock != null) {
			if (rock.isOnScreen() && rock.interact("Mine")) {
				Time.sleep(750);
				Sleep.sleep(new Condition() {

					@Override
					public boolean validate() {
						return Players.getLocal().isWalking();
					}
					
				}, 3000);
			} else {
				rock.getLocation().clickMM();
				Time.sleep(750);
				Sleep.sleep(new Condition() {

					@Override
					public boolean validate() {
						return Players.getLocal().isWalking();
					}
		
				}, 3000);
			}
		}
	}

}
