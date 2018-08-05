package com.kosmo.ui.dbmanager.App.developerpart;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.kosmo.ui.dbmanager.DBManager;
import com.kosmo.ui.dbmanager.service.sql.SqlUsageService;
import com.kosmo.ui.dbmanager.service.sql.impl.SqlUsageServiceImpl;

public class TablePanel extends JPanel {

	private JPanel contentPane;
	JTable jTable;
	JPanel panel;
	DefaultTableModel defaultTableModel;
	private SqlUsageService sqlService;
	/**
	 * Create the panel.
	 */
	public TablePanel() {
		contentPane = new JPanel();

		contentPane.setBorder(new EmptyBorder(5,5,5,5));
		contentPane.setLayout(new BorderLayout(0, 0)); 

		
		defaultTableModel = selectColumnAndData("select e.empno, e.ename, d.deptno, d.dname from emp e, dept d where e.deptno=d.deptno"); 	//컬럼,?��?��?��: sql?��?�� 결과 
		jTable = new JTable(defaultTableModel);
		
		ListSelectionModel rowSM = jTable.getSelectionModel();
		rowSM.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		rowSM.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting())        {
					ListSelectionModel lsm = (ListSelectionModel)e.getSource();
					System.out.println("[?�� �?�?] " + lsm.getMinSelectionIndex()+ "번째 ?�� ?��?��");
				}
			}
		});

		ListSelectionModel colSM = jTable.getColumnModel().getSelectionModel();
		colSM.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		colSM.addListSelectionListener(new ListSelectionListener()
		{
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting())        {
					ListSelectionModel lsm = (ListSelectionModel)e.getSource();
					System.out.println("[컬럼] " + lsm.getMinSelectionIndex()+ "번째 컬럼 ?��?��");
				}
			}
		});

		JScrollPane jScollPane = new JScrollPane(jTable);
		jScollPane.setPreferredSize(new Dimension(660,270));
		contentPane.add(jScollPane);
		add(contentPane, BorderLayout.WEST);



	}


	public ArrayList<Vector<Object>> selectDataOnlyEmp() {
		Connection conn = null;
		PreparedStatement pstmt =null;
		ResultSet rs  = null;
		DBManager db = new DBManager();

		ArrayList<Vector<Object>> list = new ArrayList<Vector<Object>>();

		try {
			conn = db.dbConn();
			pstmt = conn.prepareStatement("select empno, ename, sal from emp"); 
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Vector<Object> vt = new Vector<Object>();
				vt.addElement(rs.getInt("empno"));
				vt.addElement(rs.getString("ename"));
				vt.addElement(rs.getInt("sal"));
				list.add(vt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.dbClose(conn, pstmt, rs);
		}
		return list;
	}


	public DefaultTableModel selectColumnAndData(String sql){
		Vector<Vector<Object>> vtData = new Vector<Vector<Object>>();
		Vector<String> vtColumnNames = new Vector<String>();

		try {
			DBManager db = new DBManager();
			Connection conn = db.dbConn();
			PreparedStatement pstmt= conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();	//컬럼�??��

			for (int i=1; i <= columnCount; i++) {
				vtColumnNames.add(metaData.getColumnLabel(i));	
			}
			while (rs.next()) {
				Vector<Object> vtOneRow = new Vector<Object>();
				for (int i=1; i <= columnCount; i++) {
					vtOneRow.addElement(rs.getObject(i));
				}
				vtData.addElement(vtOneRow);
			}
		} catch (  Exception e) {
			e.printStackTrace();
		}
		return new DefaultTableModel(vtData, vtColumnNames);
	}
	
	public TablePanel(int result) {
		sqlService = new SqlUsageServiceImpl();
		
	}

}
