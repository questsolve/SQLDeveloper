package com.kosmo.ui.dbmanager.service.sql;

import java.util.List;
import java.util.Map;

import com.kosmo.ui.dbmanager.service.emp.EmpVO;

public interface SqlUsageDao {

	public List<SqlUsageVO> selectList(EmpVO vo);
	public int insert(SqlUsageVO sqlVO);
	public int updateDownloadCount(SqlUsageVO sqlUsageVO);
	public String selectQuery(SqlUsageVO sqlUsageVO);
	public List<Map> selectByQuery(String sql);
}
