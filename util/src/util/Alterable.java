package util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public abstract class Alterable {

	@SuppressWarnings("unchecked")
	public <T> T get(String name) throws Exception {
		try {
			Field f = getField(name, "get");
			if(f.isAnnotationPresent(BeforeGet.class)) {
				execute(f.getAnnotation(BeforeGet.class).method(), false, null);
			}
			return (T)f.get(this);
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
			Field f = getField(name, "set");
			if(f.isAnnotationPresent(BeforeSet.class)) {
				BeforeSet b = f.getAnnotation(BeforeSet.class);
				execute(b.method(), b.hasParam(), f.get(this));
			}
			f.set(this, value);
			if(f.isAnnotationPresent(AfterSet.class)) {
				AfterSet a = f.getAnnotation(AfterSet.class);
				execute(a.method(), a.hasParam(), f.get(this));
			}
		} catch(NullPointerException
				| SecurityException
				| NoSuchFieldException
				| IllegalAccessException
				| IllegalArgumentException e) 
		{
			throw new Exception(e);
		}
	}
	
	private void execute(String method, boolean hasParam, Object param) throws Exception {
		if(hasParam) {
			Method m = null;
			(m = getClass().getDeclaredMethod(method, param.getClass())).setAccessible(true);
			m.invoke(this, param);
		} else {
			Method m = null;
			(m = getClass().getDeclaredMethod(method)).setAccessible(true);
			m.invoke(this);	
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
