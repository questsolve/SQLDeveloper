package com.kosmo.ui.dbmanager.App.developerpart;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Panel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultTreeSelectionModel;
import javax.swing.tree.TreeSelectionModel;

import com.kosmo.ui.dbmanager.DBManager;

import javax.swing.JTree;

public class TreeAppendPanel extends Panel {

	private JPanel contentPane;
	JTree jTree;

	/**
	 * Launch the application.
	 */
	
	public TreeAppendPanel() {
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		
		
		DefaultTreeSelectionModel treeSelectionModel = new DefaultTreeSelectionModel(); 
		treeSelectionModel.setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

		DefaultMutableTreeNode rootNode = dbTreeData(); 
		DefaultTreeModel model = new DefaultTreeModel(rootNode);
		jTree = new JTree(model);	
		jTree.setSelectionModel(treeSelectionModel);
		
		
		contentPane.add(jTree, BorderLayout.WEST);
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
	
	public TreeAppendPanel(TablePanel tablePanel) {
		this();
	}

}
