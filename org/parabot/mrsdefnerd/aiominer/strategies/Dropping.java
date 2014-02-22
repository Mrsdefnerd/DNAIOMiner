package org.parabot.mrsdefnerd.aiominer.strategies;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.parabot.mrsdefnerd.aiominer.constants.MiningType;
import org.parabot.mrsdefnerd.aiominer.data.Data;
import org.rev317.api.methods.Bank;
import org.rev317.api.methods.Inventory;
import org.rev317.api.wrappers.hud.Item;

public class Dropping implements Strategy {

	@Override
	public boolean activate() {
		return Inventory.isFull() && Data.type.equals(MiningType.POWERMINING);
	}

	@Override
	public void execute() {
		Data.botState = "Dropping..";
		if (Bank.isOpen()) {
			Bank.close();
			return;
		}
		for (Item i : Inventory.getItems())
			if (i != null ) {
				i.interact("Drop");
				Time.sleep(500);
			}
	}

}
