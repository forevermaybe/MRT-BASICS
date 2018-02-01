package com.mry.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class Proxy implements InvocationHandler {

	Object targetobj;
	
	public Proxy(Object obj){
		super();
		this.targetobj=obj;
	}
	
	public Object getinstance(){
		return java.lang.reflect.Proxy.newProxyInstance(targetobj.getClass().getClassLoader(), targetobj.getClass().getInterfaces(), this);
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("执行开始");
		Object result = method.invoke(targetobj, args);
		return result;
	}

}
