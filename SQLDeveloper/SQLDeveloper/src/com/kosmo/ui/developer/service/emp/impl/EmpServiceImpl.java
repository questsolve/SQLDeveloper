package com.kosmo.ui.developer.service.emp.impl;

import java.util.List;

import com.kosmo.ui.developer.service.domain.EmpVO;
import com.kosmo.ui.developer.service.emp.EmpDao;
import com.kosmo.ui.developer.service.emp.EmpService;

public class EmpServiceImpl implements EmpService {

	private EmpDao empDao;
	
	public EmpServiceImpl() {
		System.out.println(this.getClass().getName());
		empDao = new EmpDaoImpl();
	}
	
	public EmpVO select(EmpVO temp) {
		return empDao.select(temp);
	}

	@Override
	public int insert(EmpVO vo) {
		return empDao.insert(vo);
	}

	public int update(EmpVO vo) {
		return empDao.update(vo);
	}
	
	public int updateAuth(EmpVO vo) {
		return empDao.updateAuth(vo);
	}
	
	public int delete(EmpVO vo) {
		return empDao.delete(vo);
	}
	
	public List<EmpVO> selectAllEmp(String sql){
		return empDao.selectAllEmp(sql);
	}
	public List<String> getColumnName(String sql){
		return empDao.getColumnName(sql);
	}
	public EmpVO select(int empNo) {
		return empDao.select(empNo);
	}
}
