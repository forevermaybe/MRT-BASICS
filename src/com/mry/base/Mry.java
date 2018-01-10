package com.mry.base;

import com.mry.annotate.Mryautowired;
import com.mry.annotate.MyAnnotate;
import com.mry.proxy.Xhh;

public class Mry {

	private String name;

	private String address;

	private String age;
	
	@Mryautowired
	private Xhh xhh;

	
	public Mry(){};
	
	@MyAnnotate
	public Mry(String name, String address, String age) {
		super();
		this.name = name;
		this.address = address;
		this.age = age;
	}
	
	public void sayno() {
		xhh.sayno();
	}

	public void sayhello(){
		System.out.println("我叫"+name+"今年"+age+"岁"+"来自"+address);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Mry [name=" + name + ", address=" + address + ", age=" + age + "]";
	}

}
