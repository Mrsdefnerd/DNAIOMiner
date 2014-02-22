package org.parabot.mrsdefnerd.aiominer.strategies;

import org.parabot.environment.scripts.framework.Strategy;
import org.parabot.mrsdefnerd.aiominer.constants.Constants;
import org.parabot.mrsdefnerd.aiominer.constants.MiningType;
import org.parabot.mrsdefnerd.aiominer.constants.Teleports;
import org.parabot.mrsdefnerd.aiominer.data.Data;
import org.parabot.mrsdefnerd.aiominer.methods.Methods;
import org.rev317.api.methods.Bank;
import org.rev317.api.methods.Inventory;

public class Traveling implements Strategy {

	@Override
	public boolean activate() {
		return Data.type.equals(MiningType.BANKING) && (Inventory.isFull() && Constants.HOME_TILE.distanceTo() > 15 || !Inventory.isFull() && Constants.MINING_TILE.distanceTo() > 50);
	}

	@Override
	public void execute() {
		Data.botState = "Teleporting..";
		if (Bank.isOpen()) {
			Bank.close();
			return;
		}
		if (Inventory.isFull()) {
			Methods.teleport(Teleports.HOME);
		} else {
			Methods.teleport(Teleports.SKILLING_MINING);
		}	
	}

}
