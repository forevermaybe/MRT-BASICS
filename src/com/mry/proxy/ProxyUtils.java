package com.mry.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

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
	
}
