package org.parabot.mrsdefnerd.aiominer.methods;

import org.parabot.environment.api.utils.Time;
import org.parabot.mrsdefnerd.aiominer.interfaces.Condition;

public class Sleep {
	
	public static boolean sleep(Condition con, int time) {
		long start = System.currentTimeMillis();
		while (System.currentTimeMillis() - start < time) {
			if (!con.validate())
				return false;
			Time.sleep(15);
		}
		return true;
	}

}
