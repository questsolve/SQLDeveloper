package com.kosmo.ui.dbmanager.service.sql;

import java.util.List;
import java.util.Map;

import com.kosmo.ui.dbmanager.service.domain.EmpVO;
import com.kosmo.ui.dbmanager.service.domain.SqlUsageVO;

public interface SqlUsageService {
	public List<SqlUsageVO> selectList(EmpVO vo);
	public int insert(SqlUsageVO sqlVO);
	public int updateDownloadCount(SqlUsageVO sqlUsageVO);
	public String selectQuery(SqlUsageVO sqlUsageVO);
	public List<Map> selectByQuery(String sql);
	public SqlUsageVO selectBySQL(String sql, EmpVO vo);
}
