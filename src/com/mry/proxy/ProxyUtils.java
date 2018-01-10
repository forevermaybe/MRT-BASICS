package com.mry.proxy;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import com.mry.annotate.Mryautowired;

public class ProxyUtils implements InvocationHandler {

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Class<?> clazz = proxy.getClass();
		Class<?> implclass = clazz.getInterfaces()[0];
		System.out.println("执行开始");
		if (implclass != null) {
			Object impl = implclass.newInstance();
			Object obj = method.invoke(impl, args);
			System.out.println("执行完毕");
			return obj;
		}
		System.out.println("没实现类");
		return null;
	}

	public static Object getInstance(Class<?> clazz, Object... args)
			throws InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException {
		Object obj;
		if (args == null || args.length == 0) {
			obj = clazz.newInstance();
		} else {
			Class<?>[] conclasses = new Class[args.length];
			for (int i = 0; i < args.length; i++) {
				conclasses[i] = args[i].getClass();
			}
			obj = clazz.getConstructor(conclasses);
		}
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			Annotation anno = field.getAnnotation(Mryautowired.class);
			if (anno == null) {
				continue;
			}
			Class<?> fieldclass = field.getType();
			Class<?>[] implclass=fieldclass.get;
			System.out.println(123);
			/*Object impl=implclass.newInstance();
			field.setAccessible(true);
			field.set(obj, impl);*/
		}
		return obj;
	}

}
