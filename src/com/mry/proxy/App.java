package com.mry.proxy;

import com.mry.base.Mry;

public class App {

	public static void main(String[] args) throws Throwable {
		Mry mry = (Mry) Utils.getInstance(Mry.class, new Object[] { "da", "da", "da" });
		mry.sayno();
		System.out.println("--------------");
		ProxyUtils utils = new ProxyUtils();
		utils.invoke(mry, mry.getClass().getMethod("sayno", new Class[] {}), new Object[] {});
		System.out.println("--------------");
		ProxyUtils.domethod(mry, "sayno", args);
	}
}
