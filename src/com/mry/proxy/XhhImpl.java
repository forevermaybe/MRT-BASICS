package com.mry.proxy;

public class XhhImpl implements Xhh {

	XhhImpl() {
		System.out.println("启动构造函数");
	}

	@Override
	public void sayno() {
		System.out.println("对恶势力sayno,世界和平");
	}

}
