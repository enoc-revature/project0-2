package com.revature.project0_2.dao;

public interface InterfaceDAO<T> {
	public static void writeSerial(T t);
	public static T readSerial(String id, char type);
}
