package com.kosmo.ui.developer.App.mainframepart;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Panel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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

import com.kosmo.ui.developer.service.common.DBManager;
import com.kosmo.ui.developer.service.domain.EmpVO;

import javax.swing.JTree;

public class TreeAppendPanel extends Panel {

	private JPanel contentPane;
	JTree jTree;
	private EmpVO vo;
	/**
	 * Launch the application.
	 */
	
	public TreeAppendPanel(TablePanel tablePanel, EmpVO vo) {
		this.vo = vo;
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		
		
		DefaultTreeSelectionModel treeSelectionModel = new DefaultTreeSelectionModel(); 
		treeSelectionModel.setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

		DefaultMutableTreeNode rootNode = dbTreeData(); 
		DefaultTreeModel model = new DefaultTreeModel(rootNode);
		jTree = new JTree(model);	
		jTree.setSelectionModel(treeSelectionModel);
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
					tablePanel.setVisible(true);
					
				}
			}
		});
		jTree.addTreeExpansionListener(new TreeExpansionListener() {
			public void treeCollapsed(TreeExpansionEvent e) {
				System.out.println("jTree ?��?��");

			}
			public void treeExpanded(TreeExpansionEvent e) {
				System.out.println("jTree ?��쳐짐");
			}
		});
		contentPane.add(jTree, BorderLayout.WEST);
		JScrollPane jScollPane = new JScrollPane(jTree);
		jScollPane.setPreferredSize(new Dimension(200,600));
		contentPane.add(jScollPane);
		add(contentPane, BorderLayout.WEST);

	}
	
	public DefaultMutableTreeNode dbTreeData() {
		DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("XE");
		DefaultMutableTreeNode childNode = null;

		//--------------- DB?뿉?꽌 媛??졇?삩 ?뀒?씠釉? 紐⑸줉
		childNode = new DefaultMutableTreeNode("Table");
		DBManager db = new DBManager();
		String sql = "select tname from tab";
		PreparedStatement pstmt=null;
		try {
			System.out.println("connection start");
			Connection conn = db.dbConn();
			System.out.println("Query start");
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				if(vo.getAuth()!=3) {
					String tn= rs.getString("tname");
					if(!(tn.contains("EMP") 
					   ||tn.contains("QUERY") ||tn.contains("USAGE") || tn.contains("USER"))) {
						childNode.add(new DefaultMutableTreeNode(rs.getString("tname")));
					}
				}else {
					childNode.add(new DefaultMutableTreeNode(rs.getString("tname")));
				}
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
	
	public TreeAppendPanel() {
		
	}

}
