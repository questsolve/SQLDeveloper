package com.kosmo.ui.sample;



import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultTreeSelectionModel;
import javax.swing.tree.TreeSelectionModel;


import com.kosmo.ui.dbmanager.DBManager;

import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Color;
import java.awt.Dimension;

public class JTree_panel extends JPanel {

	private JPanel contentPane;
	JTree jTree;
	
	
	public JTree_panel(JTable_panel tablePanel) { //<--?��?��블패?���?경위?�� 주소 받기
		
		contentPane = new JPanel();
//		contentPane.setBackground(Color.PINK);
		contentPane.setBorder(new EmptyBorder(0,0,0,0));
		contentPane.setLayout(new BorderLayout(0, 0)); 
		
		DefaultTreeSelectionModel treeSelectionModel = new DefaultTreeSelectionModel(); 
		treeSelectionModel.setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);



		//---------------------------------------------
		//1. node?��?��: DB값으�? ?��?�� ?��?��
		//---------------------------------------------
		DefaultMutableTreeNode rootNode = dbTreeData();  // <----DB값으�? ?��?�� 만들�?

		//---------------------------------------------
		//2. 모델?��?��
		//---------------------------------------------
		DefaultTreeModel model = new DefaultTreeModel(rootNode);

		//---------------------------------------------
		//3. JTree?��?��
		//---------------------------------------------
		jTree = new JTree(model);	
		jTree.setSelectionModel(treeSelectionModel);	//?��?��?��?��만�??��?���?


		//?��?��?��벤트
		jTree.addTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode)e.getPath().getLastPathComponent();
				if(selectedNode.isLeaf()) {
					System.out.println("?��?�� ?��?��블명:" + selectedNode.getUserObject());
					
					//---------------------------------------------
					//		기존 ?��?��?�� 초기?�� 방법
					//		defaultTableModel.setNumRows(0);		
					//      for (int i = 0; i < defaultTableModel.getRowCount(); i++) {
					//      	defaultTableModel.removeRow(i);
					//      }
					//----------------------------------------------
				
					tablePanel.defaultTableModel.setNumRows(0);
					DefaultTableModel vv = tablePanel.selectColumnAndData("select * from "+ selectedNode.getUserObject().toString()); 	//컬럼,?��?��?��: sql?��?�� 결과 
					tablePanel.jTable.setModel(vv);
					
					
				}
			}
		});
		//?���?,?��?�� ?��벤트
		jTree.addTreeExpansionListener(new TreeExpansionListener() {
			public void treeCollapsed(TreeExpansionEvent e) {
				System.out.println("jTree ?��?��");

			}
			public void treeExpanded(TreeExpansionEvent e) {
				System.out.println("jTree ?��쳐짐");
			}
		});


		
		JScrollPane jScollPane = new JScrollPane(jTree);
		jScollPane.setPreferredSize(new Dimension(200,600));
		contentPane.add(jScollPane);
		add(contentPane, BorderLayout.WEST);

		
	}


	public DefaultMutableTreeNode dbTreeData() {
		DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("XE");
		DefaultMutableTreeNode childNode = null;

		//--------------- DB?��?�� �??��?�� ?��?���? 목록
		childNode = new DefaultMutableTreeNode("Table");
		DBManager db = new DBManager();
		Connection conn = db.dbConn();
		String sql = "select tname from tab";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				childNode.add(new DefaultMutableTreeNode(rs.getString("tname")));  
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		rootNode.add(childNode);
		//---------------

		childNode = new DefaultMutableTreeNode("View");
		childNode.add(new DefaultMutableTreeNode("B1"));
		childNode.add(new DefaultMutableTreeNode("B2"));
		rootNode.add(childNode);

		return rootNode;
	}

}
