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
//		** JTABLE ?¬?©λ²? **
//		   λ°©λ²1: ?°?΄?°,μ»¬λΌλͺμ?± 							  -> JTable??±(?°?΄?°[][], μ»¬λΌ[])
//		   λ°©λ²2: ?°?΄?°,μ»¬λΌλͺμ?±  ?  λͺ¨λΈ? ? ?©(?°?΄?°[][], μ»¬λΌ[]) -> JTable??±(λͺ¨λΈ)
//			
//			λͺ¨λΈ?¬?©λ²?---------------------------------------
//			public DefaultTableModel(Object[][] data, Object[] columnNames) { }
//			public DefaultTableModel(Vector data, Vector columnNames) { }
//			----------------------------------------------
//		**************************************************
		
		//----------------------------------------------
		//λ°©λ²1: new JTable(?°?΄?°[][], μ»¬λΌ[])
		//----------------------------------------------
//		Object[][] data = {
//				{"1","κΉ??¨",1000},
//				{"2","?΄?¨",2000},
//				{"3","λ°μ¨",3000}
//		};
//		String[] columnNames = {"EMPNO","ENAME","SAL"};
//		jTable =  new JTable(data, columnNames);

		
		
		//---------------------------------------------
		//λ°©λ²2: ?°?΄?°,μ»¬λΌλͺμ?± ? λͺ¨λΈ? ? ?© -> JTable??±(λͺ¨λΈ): ??μ½λ© 
		//---------------------------------------------
//		Object[][] data = {
//				{"1","κΉ??¨",1000},
//				{"2","?΄?¨",2000},
//				{"3","λ°μ¨",3000}
//		};
//		String[] columnNames = {"EMPNO","ENAME","SAL"};
//		defaultTableModel = new DefaultTableModel(data, columnNames);
//		jTable = new JTable(defaultTableModel);
		
		
		
		
		//---------------------------------------------
		//λ°©λ²2: ?°?΄?°,μ»¬λΌλͺμ?± ? λͺ¨λΈ? ? ?© -> JTable??±(λͺ¨λΈ):  DB?? κ°?? Έ?€κΈ?(EMP??΄λΈ? κ³ μ )
		//---------------------------------------------
//		String[] columnNames = {"EMPNO","ENAME","SAL"};
//		defaultTableModel= new DefaultTableModel(null, columnNames);	//μ»¬λΌ:??μ½λ©
//		ArrayList<Vector<Object>> list = selectDataOnlyEmp();			//?°?΄?°: EMP??΄λΈ? κ³ μ ..
//		for(int i=0; i<list.size(); i++) {
//			defaultTableModel.addRow(list.get(i)); 
//		}
//		jTable = new JTable(defaultTableModel);
		
		
		
		//---------------------------------------------
		//λ°©λ²2: ?°?΄?°,μ»¬λΌλͺμ?± ? λͺ¨λΈ? ? ?© -> JTable??±(λͺ¨λΈ):  DB?? κ°?? Έ?€κΈ?(SQL?€?κ²°κ³Ό)
		//---------------------------------------------
		defaultTableModel = selectColumnAndData("select e.empno, e.ename, d.deptno, d.dname from emp e, dept d where e.deptno=d.deptno"); 	//μ»¬λΌ,?°?΄?°: sql?€? κ²°κ³Ό 
		jTable = new JTable(defaultTableModel);
		
		
		//?? ? ?΄λ²€νΈ
		ListSelectionModel rowSM = jTable.getSelectionModel();
		rowSM.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		rowSM.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting())        {
					ListSelectionModel lsm = (ListSelectionModel)e.getSource();
					System.out.println("[? λ³?κ²?] " + lsm.getMinSelectionIndex()+ "λ²μ§Έ ? ? ?");
				}
			}
		});
		//μ»¬λΌ? ? ?΄λ²€νΈ
		ListSelectionModel colSM = jTable.getColumnModel().getSelectionModel();
		colSM.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		colSM.addListSelectionListener(new ListSelectionListener()
		{
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting())        {
					ListSelectionModel lsm = (ListSelectionModel)e.getSource();
					System.out.println("[μ»¬λΌ] " + lsm.getMinSelectionIndex()+ "λ²μ§Έ μ»¬λΌ ? ?");
				}
			}
		});
		
		
		
		//---------------------------------------------
		//		κΈ°μ‘΄ ?°?΄?° μ΄κΈ°? λ°©λ²
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
	// ?°?΄?°: EMP??΄λΈ? κ³ μ ..
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
	// μ»¬λΌ,?°?΄?°: sql?€? κ²°κ³Ό
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
			int columnCount = metaData.getColumnCount();	//μ»¬λΌκ°??

			//sql?€?κ²°κ³Ό μ»¬λΌλͺ? -------------
			for (int i=1; i <= columnCount; i++) {
				vtColumnNames.add(metaData.getColumnLabel(i));	
			}
			//sql?€?κ²°κ³Ό ?°?΄?° -------------
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
