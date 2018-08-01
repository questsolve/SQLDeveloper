package com.kosmo.ui.sample;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.kosmo.ui.dbmanager.DBManager;

public class JTable_panel extends JPanel {

	private JPanel contentPane;
	JTable jTable;
	DefaultTableModel defaultTableModel;
	
	
	/**
	 * Create the frame.
	 */
	public JTable_panel() {
		
		contentPane = new JPanel();

		contentPane.setBorder(new EmptyBorder(5,5,5,5));
		contentPane.setLayout(new BorderLayout(0, 0)); 

//		**************************************************
//		** JTABLE ?Ç¨?ö©Î≤? **
//		   Î∞©Î≤ï1: ?ç∞?ù¥?Ñ∞,Ïª¨ÎüºÎ™ÖÏÉù?Ñ± 							  -> JTable?Éù?Ñ±(?ç∞?ù¥?Ñ∞[][], Ïª¨Îüº[])
//		   Î∞©Î≤ï2: ?ç∞?ù¥?Ñ∞,Ïª¨ÎüºÎ™ÖÏÉù?Ñ±  ?õÑ  Î™®Îç∏?óê ?†Å?ö©(?ç∞?ù¥?Ñ∞[][], Ïª¨Îüº[]) -> JTable?Éù?Ñ±(Î™®Îç∏)
//			
//			Î™®Îç∏?Ç¨?ö©Î≤?---------------------------------------
//			public DefaultTableModel(Object[][] data, Object[] columnNames) { }
//			public DefaultTableModel(Vector data, Vector columnNames) { }
//			----------------------------------------------
//		**************************************************
		
		//----------------------------------------------
		//Î∞©Î≤ï1: new JTable(?ç∞?ù¥?Ñ∞[][], Ïª¨Îüº[])
		//----------------------------------------------
//		Object[][] data = {
//				{"1","Íπ??î®",1000},
//				{"2","?ù¥?î®",2000},
//				{"3","Î∞ïÏî®",3000}
//		};
//		String[] columnNames = {"EMPNO","ENAME","SAL"};
//		jTable =  new JTable(data, columnNames);

		
		
		//---------------------------------------------
		//Î∞©Î≤ï2: ?ç∞?ù¥?Ñ∞,Ïª¨ÎüºÎ™ÖÏÉù?Ñ± ?õÑ Î™®Îç∏?óê ?†Å?ö© -> JTable?Éù?Ñ±(Î™®Îç∏): ?ïò?ìúÏΩîÎî© 
		//---------------------------------------------
//		Object[][] data = {
//				{"1","Íπ??î®",1000},
//				{"2","?ù¥?î®",2000},
//				{"3","Î∞ïÏî®",3000}
//		};
//		String[] columnNames = {"EMPNO","ENAME","SAL"};
//		defaultTableModel = new DefaultTableModel(data, columnNames);
//		jTable = new JTable(defaultTableModel);
		
		
		
		
		//---------------------------------------------
		//Î∞©Î≤ï2: ?ç∞?ù¥?Ñ∞,Ïª¨ÎüºÎ™ÖÏÉù?Ñ± ?õÑ Î™®Îç∏?óê ?†Å?ö© -> JTable?Éù?Ñ±(Î™®Îç∏):  DB?óê?Ñú Í∞??†∏?ò§Í∏?(EMP?Öå?ù¥Î∏? Í≥†Ï†ï)
		//---------------------------------------------
//		String[] columnNames = {"EMPNO","ENAME","SAL"};
//		defaultTableModel= new DefaultTableModel(null, columnNames);	//Ïª¨Îüº:?ïò?ìúÏΩîÎî©
//		ArrayList<Vector<Object>> list = selectDataOnlyEmp();			//?ç∞?ù¥?Ñ∞: EMP?Öå?ù¥Î∏? Í≥†Ï†ï..
//		for(int i=0; i<list.size(); i++) {
//			defaultTableModel.addRow(list.get(i)); 
//		}
//		jTable = new JTable(defaultTableModel);
		
		
		
		//---------------------------------------------
		//Î∞©Î≤ï2: ?ç∞?ù¥?Ñ∞,Ïª¨ÎüºÎ™ÖÏÉù?Ñ± ?õÑ Î™®Îç∏?óê ?†Å?ö© -> JTable?Éù?Ñ±(Î™®Îç∏):  DB?óê?Ñú Í∞??†∏?ò§Í∏?(SQL?ã§?ñâÍ≤∞Í≥º)
		//---------------------------------------------
		defaultTableModel = selectColumnAndData("select e.empno, e.ename, d.deptno, d.dname from emp e, dept d where e.deptno=d.deptno"); 	//Ïª¨Îüº,?ç∞?ù¥?Ñ∞: sql?ã§?ñâ Í≤∞Í≥º 
		jTable = new JTable(defaultTableModel);
		
		
		//?ñâ?Ñ†?Éù ?ù¥Î≤§Ìä∏
		ListSelectionModel rowSM = jTable.getSelectionModel();
		rowSM.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		rowSM.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting())        {
					ListSelectionModel lsm = (ListSelectionModel)e.getSource();
					System.out.println("[?ñâ Î≥?Í≤?] " + lsm.getMinSelectionIndex()+ "Î≤àÏß∏ ?ñâ ?Ñ†?Éù");
				}
			}
		});
		//Ïª¨Îüº?Ñ†?Éù ?ù¥Î≤§Ìä∏
		ListSelectionModel colSM = jTable.getColumnModel().getSelectionModel();
		colSM.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		colSM.addListSelectionListener(new ListSelectionListener()
		{
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting())        {
					ListSelectionModel lsm = (ListSelectionModel)e.getSource();
					System.out.println("[Ïª¨Îüº] " + lsm.getMinSelectionIndex()+ "Î≤àÏß∏ Ïª¨Îüº ?Ñ†?Éù");
				}
			}
		});
		
		
		
		//---------------------------------------------
		//		Í∏∞Ï°¥ ?ç∞?ù¥?Ñ∞ Ï¥àÍ∏∞?ôî Î∞©Î≤ï
		//		defaultTableModel.setNumRows(0);		
		//      for (int i = 0; i < defaultTableModel.getRowCount(); i++) {
		//      	defaultTableModel.removeRow(i);
		//      }
		//----------------------------------------------
		
		

		JScrollPane jScollPane = new JScrollPane(jTable);
		jScollPane.setPreferredSize(new Dimension(660,270));
		contentPane.add(jScollPane);
		add(contentPane, BorderLayout.WEST);
		

		
	}
	
	
	//------------------------------------------------------------
	// ?ç∞?ù¥?Ñ∞: EMP?Öå?ù¥Î∏? Í≥†Ï†ï..
	//------------------------------------------------------------
	//public ArrayList<EmpVO> select() {
	public ArrayList<Vector<Object>> selectDataOnlyEmp() {
		Connection conn = null;
		PreparedStatement pstmt =null;
		ResultSet rs  = null;
		DBManager db = new DBManager();
		
		//ArrayList<EmpVO> list = new ArrayList<EmpVO>();
		ArrayList<Vector<Object>> list = new ArrayList<Vector<Object>>();
		
		try {
			conn = db.dbConn();
			pstmt = conn.prepareStatement("select empno, ename, sal from emp"); 
			rs = pstmt.executeQuery();
			while(rs.next()) {
//				EmpVO vo = new EmpVO();
//				vo.setEmpno(rs.getInt("empno"));
//				vo.setEname(rs.getString("ename"));
//				vo.setSal(rs.getInt("sal"));
//				list.add(vo);
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
	
	
	//------------------------------------------------------------
	// Ïª¨Îüº,?ç∞?ù¥?Ñ∞: sql?ã§?ñâ Í≤∞Í≥º
	//------------------------------------------------------------
	public DefaultTableModel selectColumnAndData(String sql){
		Vector<Vector<Object>> vtData = new Vector<Vector<Object>>();
		Vector<String> vtColumnNames = new Vector<String>();
		
		try {
			DBManager db = new DBManager();
			Connection conn = db.dbConn();
			PreparedStatement pstmt= conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();	//Ïª¨ÎüºÍ∞??àò

			//sql?ã§?ñâÍ≤∞Í≥º Ïª¨ÎüºÎ™? -------------
			for (int i=1; i <= columnCount; i++) {
				vtColumnNames.add(metaData.getColumnLabel(i));	
			}
			//sql?ã§?ñâÍ≤∞Í≥º ?ç∞?ù¥?Ñ∞ -------------
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

}
