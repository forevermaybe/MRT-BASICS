package com.mry.proxy;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import com.mry.base.Mry;

public class App {

	public static void main(String[] args) throws Throwable {
		Mry mry=(Mry)Utils.getInstance(Mry.class, new Object[] {"da","da","da"});
		mry.sayno();
		ProxyUtils utils = new ProxyUtils();
		utils.invoke(mry, mry.getClass().getMethod("sayno", new Class[] {}), new Object[] {});
	}
}
