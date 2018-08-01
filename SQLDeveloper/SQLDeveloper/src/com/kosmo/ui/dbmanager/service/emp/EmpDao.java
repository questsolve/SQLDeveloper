package com.kosmo.ui.dbmanager.service.emp;

public interface EmpDao {
	
	public EmpVO select(EmpVO temp);
	public int insert(EmpVO vo);
	public int update(EmpVO vo);
	public int updateAuth(EmpVO vo);
	public int delete(EmpVO vo) ;
}
