package com.kosmo.ui.dbmanager.service.emp.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kosmo.ui.dbmanager.service.common.DBManager;
import com.kosmo.ui.dbmanager.service.domain.EmpVO;
import com.kosmo.ui.dbmanager.service.emp.EmpDao;

public class EmpDaoImpl implements EmpDao {

	public EmpDaoImpl() {
		System.out.println(this.getClass().getName());
	}

	//SELECT //LOGIN
	public EmpVO select(EmpVO temp){
		DBManager dbm = new DBManager();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		EmpVO vo = new EmpVO();

		try {
			con = dbm.dbConn();
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT e.empno,e.pw,e.iswork,e.job,d.deptno,e.auth,d.deptname,e.empname");
			sb.append(" FROM devemp e, devdept d");
			sb.append(" WHERE e.deptno = d.deptno");
			sb.append(" AND iswork =1");
			sb.append(" AND empno = ?");
			sb.append(" AND pw = ?");
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setInt(1, temp.getEmpno());
			pstmt.setString(2, temp.getPw());
			rs=pstmt.executeQuery();
			while(rs.next()) {
				vo.setAuth(rs.getInt("auth"));
				vo.setDeptName(rs.getString("deptname"));
				vo.setDeptno(rs.getInt("deptno"));
				vo.setEmpName(rs.getString("empname"));
				vo.setEmpno(rs.getInt("empno"));
				vo.setIsWork(rs.getInt("iswork"));
				vo.setJob(rs.getString("job"));
				vo.setPw(rs.getString("pw"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbm.dbClose(con, pstmt, rs);
		}



		return vo;
	}

	public EmpVO select(int empNo){
		DBManager dbm = new DBManager();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		EmpVO vo = new EmpVO();

		try {
			con = dbm.dbConn();
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT e.empno,e.pw,e.iswork,e.job,d.deptno,e.auth,d.deptname,e.empname");
			sb.append(" FROM devemp e, devdept d");
			sb.append(" WHERE e.deptno = d.deptno");
			sb.append(" AND iswork =1");
			sb.append(" AND empno = ?");
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setInt(1, empNo);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				vo.setAuth(rs.getInt("auth"));
				vo.setDeptName(rs.getString("deptname"));
				vo.setDeptno(rs.getInt("deptno"));
				vo.setEmpName(rs.getString("empname"));
				vo.setEmpno(rs.getInt("empno"));
				vo.setIsWork(rs.getInt("iswork"));
				vo.setJob(rs.getString("job"));
				vo.setPw(rs.getString("pw"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbm.dbClose(con, pstmt, rs);
		}



		return vo;
	}
	
	
	//INSERT
	public int insert(EmpVO vo) {
		int result =0;
		DBManager dbm = new DBManager();
		Connection con = null;
		PreparedStatement pstate = null;
		StringBuilder sb = new StringBuilder();
		sb.append(	"INSERT INTO devemp(empno,pw,iswork,job,deptno,auth,empname)");
		sb.append("VALUES(devemp_seq.nextval,?,1,?,?,?,?)");
		
		try {
			con = dbm.dbConn();
			pstate = con.prepareStatement(sb.toString());
			pstate.setString(1, vo.getPw());
			pstate.setString(2, vo.getJob());
			pstate.setInt(3, vo.getDeptno());
			pstate.setInt(4, vo.getAuth());
			pstate.setString(5, vo.getEmpName());
			
			result = pstate.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbm.dbClose(con, pstate);			
		}
		
		
		return result;
		
	}

	//update
	public int update(EmpVO vo) {
		int result =0;
		DBManager dbm = new DBManager();
		Connection con = null;
		PreparedStatement pstate = null;
		StringBuilder sb = new StringBuilder();
		sb.append(	"UPDATE devemp");
		sb.append(" SET iswork =?,job = ?, deptno =?");
		sb.append("WHERE empno =?");
		sb.append(" AND iswork =1");
		
		
		try {
			con = dbm.dbConn();
			pstate = con.prepareStatement(sb.toString());
			pstate.setInt(1, vo.getIsWork());
			pstate.setString(2, vo.getJob());
			pstate.setInt(3, vo.getDeptno());
			pstate.setInt(4, vo.getEmpno());
			
			result = pstate.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbm.dbClose(con, pstate);			
		}
		
		
		return result;

	}

	//update Auth
	public int updateAuth(EmpVO vo) {
		int result =0;
		DBManager dbm = new DBManager();
		Connection con = null;
		PreparedStatement pstate = null;
		StringBuilder sb = new StringBuilder();
		sb.append(	"UPDATE devemp");
		sb.append(" SET auth = 2");
		sb.append("WHERE empno =?");
		sb.append(" AND iswork =1");
		
		try {
			con = dbm.dbConn();
			pstate = con.prepareStatement(sb.toString());
			pstate.setInt(1, vo.getEmpno());
			
			result = pstate.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbm.dbClose(con, pstate);			
		}
		
		
		return result;

	}

	//delete
	public int delete(EmpVO vo) {
		int result =0;
		DBManager dbm = new DBManager();
		Connection con = null;
		PreparedStatement pstate = null;
		StringBuilder sb = new StringBuilder();
		sb.append(	"UPDATE devemp");
		sb.append(" SET iswork = 0");
		sb.append("WHERE empno =?");
		
		try {
			con = dbm.dbConn();
			pstate = con.prepareStatement(sb.toString());
			pstate.setInt(1, vo.getEmpno());
			
			result = pstate.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbm.dbClose(con, pstate);			
		}
		
		
		return result;

	}
	
	public List<EmpVO> selectAllEmp(String sql){
		DBManager dbm = new DBManager();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<EmpVO> empList = new ArrayList<EmpVO>();

		try {
			con = dbm.dbConn();
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT e.empno,e.iswork,d.deptno,e.auth,d.deptname,e.empname");
			sb.append(" FROM devemp e, devdept d");
			pstmt = con.prepareStatement(sb.toString());
			
			rs=pstmt.executeQuery();
			while(rs.next()) {
				EmpVO vo = new EmpVO();
				vo.setAuth(rs.getInt("auth"));
				vo.setDeptName(rs.getString("deptname"));
				vo.setDeptno(rs.getInt("deptno"));
				vo.setEmpName(rs.getString("empname"));
				vo.setEmpno(rs.getInt("empno"));
				vo.setIsWork(rs.getInt("iswork"));
				vo.setJob(rs.getString("job"));
				empList.add(vo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbm.dbClose(con, pstmt, rs);
		}

		return empList;
	}
	
	public List<String> getColumnName(String sql){
		List<String> list = new ArrayList<String>();
		Connection con = null;
		PreparedStatement pstate = null;
		ResultSet rs = null;
		DBManager cm = new DBManager();
		//String sql = "SELECT empno,ename,job,mgr,hiredate,sal,comm,deptno FROM empcp";
		ResultSetMetaData rsmd = null;
		try {
			con = cm.dbConn();
			pstate = con.prepareStatement(sql);
			
			rs = pstate.executeQuery();
			rsmd = rs.getMetaData();
			
			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				list.add(rsmd.getColumnLabel(i));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			cm.dbClose(con, pstate,rs);
		}
		return list;
	}
}
