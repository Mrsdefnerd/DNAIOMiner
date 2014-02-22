package org.parabot.mrsdefnerd.aiominer.strategies;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.parabot.mrsdefnerd.aiominer.constants.Constants;
import org.parabot.mrsdefnerd.aiominer.constants.MiningType;
import org.parabot.mrsdefnerd.aiominer.data.Data;
import org.parabot.mrsdefnerd.aiominer.interfaces.Condition;
import org.parabot.mrsdefnerd.aiominer.methods.Methods;
import org.parabot.mrsdefnerd.aiominer.methods.Sleep;
import org.rev317.api.methods.Bank;
import org.rev317.api.methods.Inventory;
import org.rev317.api.methods.Players;
import org.rev317.api.wrappers.scene.SceneObject;

public class Banking implements Strategy {

	@Override
	public boolean activate() {
		return !Data.type.equals(MiningType.POWERMINING) && Inventory.isFull() && (Data.type.equals(MiningType.BANKING) ? Constants.HOME_TILE.distanceTo() <= 15 : true);
	}

	@Override
	public void execute() {
		Data.botState = "Banking..";
		if (Bank.isOpen()) {
			Bank.depositAll();
		} else {
			SceneObject bank = Methods.getNearestObject(Constants.BANK_IDS);
			if (bank != null) {
				if (bank.isOnScreen() && bank.interact((bank.getId() == Constants.BANK_IDS[1]) ? "Deposit" : "Use")) {
					Time.sleep(750);
					Sleep.sleep(new Condition() {
	
						@Override
						public boolean validate() {
							return Players.getLocal().isWalking();
						}
						
					}, 3000);
				} else {
					bank.getLocation().clickMM();
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

}
