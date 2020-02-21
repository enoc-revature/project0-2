package com.revature.project0_2.dao;

public interface InterfaceDAO<T> {
	public void writeSerial(T t);
	public T readSerial(String id, char type);
}
