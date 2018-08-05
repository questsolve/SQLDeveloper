package com.kosmo.ui.dbmanager.service.emp;

import java.util.List;

import com.kosmo.ui.dbmanager.service.domain.EmpVO;
import com.kosmo.ui.dbmanager.service.domain.SqlUsageVO;

public interface EmpService {

	public EmpVO select(EmpVO temp);
	public int insert(EmpVO vo);
	public int update(EmpVO vo);
	public int updateAuth(EmpVO vo);
	public int delete(EmpVO vo) ;
	public List<EmpVO> selectAllEmp(String sql);
	public List<String> getColumnName(String sql);
	public EmpVO select(int empNo);
	
}
