package org.parabot.mrsdefnerd.aiominer.methods;

import java.lang.reflect.Field;

import org.parabot.core.Context;

public class Reflection {
	
	private static Class<?> clientClass;
	
	public static Class<?> getClientClass() {
		if (clientClass == null)
			clientClass = loadClass();
		return clientClass;
	}
	
	public static Object getInstance() {
		return (Object) Context.resolve().getApplet();
	}

	
	private static Class<?> loadClass() {
		try {
			return Context.resolve().getASMClassLoader().loadClass("pkhonor.Client");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Object getFieldValue(String fieldName) {
		try {
			Field f = getClientClass().getDeclaredField(fieldName);
			f.setAccessible(true);
			return f.get(getInstance());
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}
