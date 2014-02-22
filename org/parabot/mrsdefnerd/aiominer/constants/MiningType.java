package org.parabot.mrsdefnerd.aiominer.constants;

public enum MiningType {
	
	DEPOSIT_BOX, BANKING, POWERMINING;

	@Override
	public String toString() {
		return name().charAt(0)+name().substring(1).toLowerCase().replace("_", " ");
	}
}
