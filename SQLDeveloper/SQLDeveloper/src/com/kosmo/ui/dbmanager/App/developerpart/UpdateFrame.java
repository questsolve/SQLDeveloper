package com.kosmo.ui.dbmanager.App.developerpart;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.kosmo.ui.dbmanager.DBManager;
import com.kosmo.ui.dbmanager.service.domain.EmpVO;
import com.kosmo.ui.dbmanager.service.emp.EmpService;
import com.kosmo.ui.dbmanager.service.emp.impl.EmpServiceImpl;


import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

public class UpdateFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private EmpService empService;
	private JTable table;
	DefaultTableModel defaultTableModel;
	private JPanel panel_1;
	private JButton cancelButton;
	private JButton updateAuthButton;
	private EmpVO updateTarget;
	private int row;
	private int col;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateFrame frame = new UpdateFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UpdateFrame() {
		
		
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		defaultTableModel = listEmp();
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		
		table = new JTable(defaultTableModel);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				row = table.getSelectedRow();
				col = table.getSelectedColumn();
				if(col ==0) {
					empService = new EmpServiceImpl();
					String cellData = table.getValueAt(row, col).toString();
					updateTarget = empService.select(Integer.parseInt(cellData));
					

				}else {
					return;
				}
				
			}
		});
		panel.add(table);
		
		ListSelectionModel rowSM = table.getSelectionModel();
		rowSM.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		ListSelectionModel colSM = table.getColumnModel().getSelectionModel();
		colSM.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		
		JScrollPane jScollPane = new JScrollPane(table);
		jScollPane.setPreferredSize(new Dimension(660,270));
		contentPane.add(jScollPane);
		
		panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		updateAuthButton = new JButton("Approve");
		updateAuthButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "Enhance the authority?");
				if(result == JOptionPane.YES_OPTION) {
					
					empService.updateAuth(updateTarget);
					
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
					}
					updateTarget = empService.select(updateTarget);
					System.out.println(updateTarget);
					table.setVisible(false);
					table.setValueAt(updateTarget.getAuth(),row , col+2);
					table.setVisible(true);
										
				}
				
				
			}
		});
		panel_1.add(updateAuthButton);
		
		cancelButton = new JButton("Cancel");
		cancelButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
			}
		});
		panel_1.add(cancelButton);
		
	}
	
	public DefaultTableModel listEmp() {
		Vector<Vector<Object>> vtData = new Vector<Vector<Object>>();
		Vector<String> vtColumnNames = new Vector<String>();
		String sql = "SELECT e.empno,e.empname,e.auth FROM devemp e";
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

	//update Auth imple필요
}
