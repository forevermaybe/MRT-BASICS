package com.mry.proxy;

import com.mry.base.Mry;

public class App {

	public static void main(String[] args) throws Throwable {
		Mry mry = (Mry) Utils.getInstance(Mry.class, new Object[] { "da", "da", "da" });
		mry.sayno();
		System.out.println("--------------");
		Proxy pro = new Proxy(new XhhImpl());
		Xhh xhh = (Xhh) pro.getinstance();
		xhh.sayno();
	}
}
