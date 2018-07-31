package com.kosmo.ui.dbmanager.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kosmo.ui.dbmanager.DBManager;
import com.kosmo.ui.dbmanager.service.EmpDao;
import com.kosmo.ui.dbmanager.service.EmpVO;

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
			sb.append(" AND empno = ?");
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setInt(1, temp.getEmpno());
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
		sb.append("VALUES(devemp_seq.nextval,?,1,?,?,1,?)");
		
		try {
			con = dbm.dbConn();
			pstate = con.prepareStatement(sb.toString());
			pstate.setString(1, vo.getPw());
			pstate.setString(2, vo.getJob());
			pstate.setInt(3, vo.getDeptno());
			pstate.setString(4, vo.getEmpName());
			
			result = pstate.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		
		try {
			con = dbm.dbConn();
			pstate = con.prepareStatement(sb.toString());
			pstate.setInt(1, vo.getIsWork());
			pstate.setString(2, vo.getJob());
			pstate.setInt(3, vo.getDeptno());
			pstate.setInt(4, vo.getEmpno());
			
			result = pstate.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			dbm.dbClose(con, pstate);			
		}
		
		
		return result;

	}

	//update Auth
	

	//delete

}
