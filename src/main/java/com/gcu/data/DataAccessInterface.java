package com.gcu.data;

import java.util.List;

public interface DataAccessInterface <T>{
	
	public List<T> findAll();
	public T findById(int id);
	public boolean create(T t);
	public int update(T t);
	public int delete(int t);

}
