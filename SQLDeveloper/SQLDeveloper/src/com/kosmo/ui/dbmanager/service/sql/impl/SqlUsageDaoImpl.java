package com.kosmo.ui.dbmanager.service.sql.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.kosmo.ui.dbmanager.DBManager;
import com.kosmo.ui.dbmanager.service.domain.EmpVO;
import com.kosmo.ui.dbmanager.service.domain.SqlUsageVO;
import com.kosmo.ui.dbmanager.service.sql.SqlUsageDao;

public class SqlUsageDaoImpl implements SqlUsageDao {

	public List<SqlUsageVO> selectList(EmpVO vo) {
		Connection con = null;
		PreparedStatement pstate = null;
		ResultSet rs = null;
		List<SqlUsageVO> list = new ArrayList<SqlUsageVO>();
		DBManager dbm = new DBManager();
		StringBuilder sb = new StringBuilder("SELECT usageno,empno,usedquery,downloadcount");
		sb.append(" FROM freusage");
		sb.append( "WHERE empno = ?");

		try {
			con =dbm.dbConn();
			pstate = con.prepareStatement(sb.toString());
			pstate.setInt(1, vo.getEmpno());
			rs = pstate.executeQuery();
			while(rs.next()) {
				SqlUsageVO sqlUsageVO = new SqlUsageVO();
				sqlUsageVO.setDownloadCount(rs.getInt("downloadcount"));
				sqlUsageVO.setEmp(vo);
				sqlUsageVO.setUsageNo(rs.getInt("usageno"));
				sqlUsageVO.setUseQuery(rs.getString("usedquery"));
				list.add(sqlUsageVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbm.dbClose(con, pstate, rs);
			
		}
		return list;
	}
	
	public int insert(SqlUsageVO sqlVO) {
		int result =0;
		Connection con = null;
		PreparedStatement pstate = null;
		DBManager dbm = new DBManager();
		StringBuilder sb = new StringBuilder("INSERT INTO freusage(usageno,empno,usedquery,downloadcount)");
		sb.append(" VALUES(freusage_seq.nextval,?,?,0)");
		
		try {
			con = dbm.dbConn();
			pstate = con.prepareStatement(sb.toString());
			pstate.setInt(1, sqlVO.getEmp().getEmpno());
			pstate.setString(2, sqlVO.getUseQuery());
			result = pstate.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbm.dbClose(con, pstate);
		}
		return result;
	}
	
	
	public int updateDownloadCount(SqlUsageVO sqlUsageVO) {
		int result =0;
		Connection con = null;
		PreparedStatement pstate = null;
		DBManager dbm = new DBManager();
		StringBuilder sb = new StringBuilder("UPDATE freusage");
		sb.append(" SET downloadcount = downloadcount+1");
		sb.append(" WHERE usageno = ?");
		
		try {
			con = dbm.dbConn();
			pstate = con.prepareStatement(sb.toString());
			pstate.setInt(1, sqlUsageVO.getUsageNo());
			
			result = pstate.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbm.dbClose(con, pstate);
		}
		return result;
		
	}
	
	public String selectQuery(SqlUsageVO sqlUsageVO) {
		Connection con = null;
		PreparedStatement pstate = null;
		ResultSet rs = null;
		String query = "";
		DBManager dbm = new DBManager();
		StringBuilder sb = new StringBuilder("SELECT usedquery");
		sb.append(" FROM freusage");
		sb.append(" WHERE usageno = ?");
		try {
			con =dbm.dbConn();
			pstate = con.prepareStatement(sb.toString());
			pstate.setInt(1, sqlUsageVO.getUsageNo());
			rs = pstate.executeQuery();
			while(rs.next()) {
				query = rs.getString("usedquery");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbm.dbClose(con, pstate, rs);
		}
		return query;
	}
	
	public List<String> getColumns(String sql){
		Connection con = null;
		PreparedStatement pstate = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd =null;
		DBManager dbm = new DBManager();
		List<String> columns = new ArrayList<String>();
		try {
			con =dbm.dbConn();
			pstate = con.prepareStatement(sql);
			rs = pstate.executeQuery();
			rsmd = rs.getMetaData();
			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				columns.add(rsmd.getColumnLabel(i));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			dbm.dbClose(con, pstate, rs);
		}
		
		return columns;
	}
	
	public List<Map> selectByQuery(String sql){
		List<Map> list = new ArrayList<Map>();
		Map<String, String> map =  null;
		Map<String, List<String>> columnMap =new HashMap<String, List<String>>();
		Connection con = null;
		PreparedStatement pstate = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd =null;
		
		DBManager dbm = new DBManager();
		List<String> column = new ArrayList<String>();
		columnMap.put("column", column);
		list.add(columnMap);
		try {
			con =dbm.dbConn();
			pstate = con.prepareStatement(sql);
			rs = pstate.executeQuery();
			rsmd = rs.getMetaData();
			
			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				
				column.add(rsmd.getColumnLabel(i));
			}
			
			while(rs.next()) {
				map = new HashMap<String, String>();
				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					//System.out.println(rs.getString(i));
					map.put(column.get(i-1), rs.getString(i));
				}
				
				list.add(map);
				
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbm.dbClose(con, pstate, rs);
		}
			
		return list;
	}
	
}
