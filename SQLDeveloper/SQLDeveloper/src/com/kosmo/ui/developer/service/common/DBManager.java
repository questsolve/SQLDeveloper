package com.kosmo.ui.developer.service.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBManager {
	
	public Connection dbConn() {
		Connection con=null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","kosmo","0000");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public void dbClose(Connection con, PreparedStatement pstate, ResultSet rs) {
		try {
			if(rs != null) {
				rs.close();
			}
			
			this.dbClose(con, pstate);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void dbClose(Connection con, PreparedStatement pstate) {
		try {
			if(pstate != null) {
				pstate.close();
			}			
			if(con != null) {
				con.close();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}
