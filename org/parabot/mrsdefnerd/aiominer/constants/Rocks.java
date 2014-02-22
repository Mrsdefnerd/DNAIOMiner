package org.parabot.mrsdefnerd.aiominer.constants;

public enum Rocks {
	
	COPPER(new MiningType[]{MiningType.POWERMINING, MiningType.BANKING}, 2091, 2090),
	TIN(new MiningType[]{MiningType.POWERMINING, MiningType.BANKING}, 2094, 2095),
	IRON(new MiningType[]{MiningType.POWERMINING, MiningType.BANKING}, 2092, 2093),
	COAL(new MiningType[]{MiningType.POWERMINING, MiningType.BANKING, MiningType.DEPOSIT_BOX}, 2096, 2097),
	GOLD(new MiningType[]{MiningType.POWERMINING, MiningType.BANKING}, 2099, 2098),
	MITHRIL(new MiningType[]{MiningType.POWERMINING, MiningType.BANKING, MiningType.DEPOSIT_BOX}, 2103, 2102),
	ADAMANT(new MiningType[]{MiningType.POWERMINING, MiningType.BANKING, MiningType.DEPOSIT_BOX}, 2105, 2104),
	RUNITE(new MiningType[]{MiningType.POWERMINING, MiningType.DEPOSIT_BOX}, 2106, 2107);
	
	private int[] ids;
	private MiningType[] types;
	Rocks(MiningType[] types, int... ids) {
		this.ids = ids;
		this.types = types;
	}
	
	@Override
	public String toString() {
		return name().charAt(0)+name().substring(1).toLowerCase().replace("_", " ");
	}
	
	public int[] getRockIds() {
		return ids;
	}
	
	public MiningType[] getTypes() {
		return types;
	}
	
	

}
