package com.kosmo.ui.dbmanager.service.sql.impl;

import java.util.List;
import java.util.Map;

import com.kosmo.ui.dbmanager.service.emp.EmpVO;
import com.kosmo.ui.dbmanager.service.sql.SqlUsageDao;
import com.kosmo.ui.dbmanager.service.sql.SqlUsageService;
import com.kosmo.ui.dbmanager.service.sql.SqlUsageVO;

public class SqlUsageServiceImpl implements SqlUsageService {

	private SqlUsageDao sqlUsageDao;
	
	public SqlUsageServiceImpl() {
		System.out.println(this.getClass().getName());
		sqlUsageDao = new SqlUsageDaoImpl();
	}

	@Override
	public List<SqlUsageVO> selectList(EmpVO vo) {
		return sqlUsageDao.selectList(vo);
	}

	@Override
	public int insert(SqlUsageVO sqlVO) {
		return sqlUsageDao.insert(sqlVO);
	}

	@Override
	public int updateDownloadCount(SqlUsageVO sqlUsageVO) {
		return sqlUsageDao.updateDownloadCount(sqlUsageVO);
	}

	@Override
	public String selectQuery(SqlUsageVO sqlUsageVO) {
		return sqlUsageDao.selectQuery(sqlUsageVO);
	}

	@Override
	public List<Map> selectByQuery(String sql) {
		return sqlUsageDao.selectByQuery(sql);
	}

}
