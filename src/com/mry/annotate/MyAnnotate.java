package com.mry.annotate;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotate {
	public String name() default "神坑辉";
	public String age() default "21";
	public String address() default "桂林";
}
