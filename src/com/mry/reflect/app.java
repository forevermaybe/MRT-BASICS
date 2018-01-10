package com.mry.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class app {

	public static void main(String[] args)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException,
			SecurityException, IllegalArgumentException, InvocationTargetException {
		forname();
	}

	private static void forname() throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class<?> clazz = Class.forName("com.mry.reflect.Mry");
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
			System.out.println(obj);
		}
		System.out.println("---------属性-----------");
		for (Field field : fields) {
			String methodname = "get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
			Method method = clazz.getDeclaredMethod(methodname, null);
			Object ss=method.invoke(mry, null);
			System.out.println(ss);
		}
	}
}
