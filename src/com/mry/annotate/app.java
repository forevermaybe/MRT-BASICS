package com.mry.annotate;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.mry.base.Mry;

public class app {

	public static void main(String[] args) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class<?> clazz = Mry.class;
		Constructor<?> constructor = clazz.getConstructor(new Class[] { String.class, String.class, String.class });
		MyAnnotate anno = constructor.getAnnotation(MyAnnotate.class);
		if(anno!=null) {
			Object obj=constructor.newInstance(anno.name(),anno.address(),anno.age());
			System.out.println(obj.toString());
			Method method=clazz.getDeclaredMethod("sayhello", new Class[] {});
			method.invoke(obj, new Object[] {});
		}
	}
}
