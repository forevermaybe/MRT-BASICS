package com.mry.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProxyUtils implements InvocationHandler {

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("执行开始");
		Object result = method.invoke(proxy, args);
		System.out.println("执行结束");
		return result;
	}

	public static void domethod(Object obj, String methodname, Object[] args)
			throws NoSuchMethodException, SecurityException {
		ProxyUtils util = new ProxyUtils();
		Class<?> clazz = obj.getClass();
		Class<?>[] parameterClass = new Class[args.length];
		for (int i = 0; i < args.length; i++) {
			parameterClass[i] = args[i].getClass();
		}
		Method method = clazz.getDeclaredMethod(methodname, parameterClass);
		try {
			util.invoke(obj, method, args);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

}
