package com.kosmo.ui.dbmanager.service.sql.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.kosmo.ui.dbmanager.DBManager;
import com.kosmo.ui.dbmanager.service.emp.EmpVO;
import com.kosmo.ui.dbmanager.service.sql.SqlUsageDao;
import com.kosmo.ui.dbmanager.service.sql.SqlUsageVO;

public class SqlUsageDaoImpl implements SqlUsageDao {

	public List<SqlUsageVO> selectList(EmpVO vo) {
		Connection con = null;
		PreparedStatement pstate = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd= null;
		List<SqlUsageVO> list = new ArrayList<SqlUsageVO>();
		DBManager dbm = new DBManager();
		SqlUsageVO sqlUsageVO = new SqlUsageVO();
		StringBuilder sb = new StringBuilder("SELECT usageno,empno,usedquery,downloadcount");
		sb.append(" FROM freusage");
		sb.append( "WHERE empno = ?");
		try {
			con =dbm.dbConn();
			pstate = con.prepareStatement(sb.toString());
			pstate.setInt(1, vo.getEmpno());
			rs = pstate.executeQuery();
			
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return null;
		
	}
}
