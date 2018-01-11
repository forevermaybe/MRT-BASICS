package com.mry.proxy;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import com.mry.annotate.Mryautowired;

public class Utils {

	public static final String[] packagenames = new String[] { "com.mry" };

	public static Object getInstance(Class<?> clazz, Object... args)
			throws InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException,
			IOException, IllegalArgumentException, InvocationTargetException {
		Object obj;
		if (args == null || args.length == 0) {
			obj = clazz.newInstance();
		} else {
			Class<?>[] conclasses = new Class[args.length];
			for (int i = 0; i < args.length; i++) {
				conclasses[i] = args[i].getClass();
			}
			obj = clazz.getConstructor(conclasses).newInstance(args);
		}
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			Annotation anno = field.getAnnotation(Mryautowired.class);
			if (anno == null) {
				continue;
			}
			Class<?> fieldclass = field.getType();
			List<Class<?>> classes = getAllClass(packagenames);
			for (Class<?> clazz2 : classes) {
				if (clazz2.isInterface()) {
					continue;
				}
				Class<?>[] interfaces = clazz2.getInterfaces();
				for (Class<?> interfaceclass : interfaces) {
					if (interfaceclass.equals(fieldclass)) {
						field.setAccessible(true);
						field.set(obj, clazz2.newInstance());
					}
				}
			}
		}
		return obj;
	}

	private static List<Class<?>> getAllClass(String... packagenames) throws IOException {
		List<Class<?>> classes = new ArrayList<Class<?>>();
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		for (String paceagename : packagenames) {
			String path = paceagename.replace('.', '/');
			Enumeration<URL> urls = loader.getResources(path);
			List<File> fileList = new ArrayList<File>();
			while (urls.hasMoreElements()) {
				URL url = urls.nextElement();
				fileList.add(new File(url.getFile()));
			}
			for (File file : fileList) {
				classes.addAll(getClass(file, paceagename));
			}
		}
		return classes;
	}

	private static List<Class<?>> getClass(File file, String packagename) {
		List<Class<?>> classes = new ArrayList<Class<?>>();
		if (!file.exists()) {
			return classes;
		}
		File[] files = file.listFiles();
		for (File file2 : files) {
			if (file2.isDirectory()) {
				if (!file2.getName().contains(".")) {
					List<Class<?>> arrayList = getClass(file2, packagename + "." + file2.getName());
					classes.addAll(arrayList);
				}
			} else if (file2.getName().endsWith(".class")) {
				try {
					classes.add(Class
							.forName(packagename + '.' + file2.getName().substring(0, file2.getName().length() - 6)));
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
		return classes;
	}
}
