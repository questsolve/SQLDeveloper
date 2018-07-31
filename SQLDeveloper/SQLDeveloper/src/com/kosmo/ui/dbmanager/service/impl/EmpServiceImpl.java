package com.kosmo.ui.dbmanager.service.impl;

import com.kosmo.ui.dbmanager.service.EmpDao;
import com.kosmo.ui.dbmanager.service.EmpService;
import com.kosmo.ui.dbmanager.service.EmpVO;

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
}
