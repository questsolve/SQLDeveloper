package com.kosmo.ui.dbmanager.service;

public interface EmpDao {
	
	public EmpVO select(EmpVO temp);
	public int insert(EmpVO vo);
	public int update(EmpVO vo);
}
