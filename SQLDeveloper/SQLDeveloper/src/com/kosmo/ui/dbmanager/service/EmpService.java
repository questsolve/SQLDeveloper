package com.kosmo.ui.dbmanager.service;

public interface EmpService {

	public EmpVO select(EmpVO temp);
	public int insert(EmpVO vo);
	public int update(EmpVO vo);
	
}
