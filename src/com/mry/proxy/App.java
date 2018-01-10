package com.mry.proxy;

import com.mry.base.Mry;

public class App {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException {
		Mry mry=(Mry)ProxyUtils.getInstance(Mry.class, null);
		mry.sayno();
	}
}
