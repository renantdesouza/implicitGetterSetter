package util;

import java.lang.reflect.Field;

public abstract class Alterable {

	@SuppressWarnings("unchecked")
	public <T> T get(String name) throws Exception {
		try {
			return (T)getField(name, "get").get(this);
		} catch (NullPointerException
				| SecurityException
				| NoSuchFieldException
				| IllegalAccessException
				| IllegalArgumentException e) 
		{
			throw new Exception(e);
		}
	}
	
	public void set(String name, Object value) throws Exception {
		try {
			getField(name, "set").set(this, value);
		} catch(NullPointerException
				| SecurityException
				| NoSuchFieldException
				| IllegalAccessException
				| IllegalArgumentException e) 
		{
			throw new Exception(e);
		}
	}
	
	private Field getField(String name, String method) 
			throws SecurityException, IllegalAccessException, NoSuchFieldException 
	{
		Field f = getClass().getDeclaredField(name);
		if(f.isAnnotationPresent(Inacessible.class)) {
			Inacessible a = f.getAnnotation(Inacessible.class);
			if((method.equals("set") && a.set()) 
				|| (method.equals("get") && a.get())) 
			{
				throw new IllegalAccessException();
			}
		}
		f.setAccessible(true);
		return f; 
	}
	
}
