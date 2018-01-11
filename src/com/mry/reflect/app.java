package com.mry.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.mry.base.Mry;

public class app {

	public static void main(String[] args)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException,
			SecurityException, IllegalArgumentException, InvocationTargetException {
		forname();
	}

	private static void forname() throws ClassNotFoundException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
		Class<?> clazz = Class.forName("com.mry.base.Mry");
		// clazz=Mry.class;
		Constructor<?>[] constructors = clazz.getConstructors();
		for (Constructor<?> ct : constructors) {
			System.out.println(ct.getName());
			Class<?>[] clazzs = ct.getParameterTypes();
			for (Class<?> clazz1 : clazzs) {
				System.out.println(clazz1.toString());
			}
			System.out.println("---------华丽分割线-----------");
		}
		System.out.println("---------构造函数-----------");
		Constructor<?> constructor = clazz.getConstructor(String.class, String.class, String.class);
		Mry mry = (Mry) constructor.newInstance("大黄", "中国", "22");
		System.out.println(mry.toString());
		System.out.println("---------华丽分割线-----------");
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			Object obj = field.get(mry);
			if(obj!=null) {
				System.out.println(obj);
			}
		}
		System.out.println("---------属性-----------");
		for (Field field : fields) {
			String methodname = "get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
			try {
				Method method = clazz.getDeclaredMethod(methodname, new Class[] {});
				Object ss = method.invoke(mry, new Object[] {});
				System.out.println(ss);
			} catch (NoSuchMethodException e) {
				continue;
			}
		}
	}
}
