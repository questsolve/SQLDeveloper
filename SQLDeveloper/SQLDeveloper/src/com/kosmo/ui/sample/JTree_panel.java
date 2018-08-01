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
	
	
	public JTree_panel(JTable_panel tablePanel) { //<--?Öå?ù¥Î∏îÌå®?ÑêÎ≥?Í≤ΩÏúÑ?ï¥ Ï£ºÏÜå Î∞õÍ∏∞
		
		contentPane = new JPanel();
//		contentPane.setBackground(Color.PINK);
		contentPane.setBorder(new EmptyBorder(0,0,0,0));
		contentPane.setLayout(new BorderLayout(0, 0)); 
		
		DefaultTreeSelectionModel treeSelectionModel = new DefaultTreeSelectionModel(); 
		treeSelectionModel.setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

		//		**************************************************
		//		** JTREE ?Ç¨?ö©Î≤? **
		//		   : node?Éù?Ñ±  -> Î™®Îç∏?Éù?Ñ± -> JTree?Éù?Ñ±
		//		**************************************************

		//---------------------------------------------
		//1. node?Éù?Ñ±: ?ïò?ìúÏΩîÎî© ?Ö∏?ìú ?Éù?Ñ±
		//---------------------------------------------
		//		DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("XE");
		//		DefaultMutableTreeNode childNode = null;		
		//		 
		//		childNode = new DefaultMutableTreeNode("TABLE");
		//			childNode.add(new DefaultMutableTreeNode("EMP"));
		//			childNode.add(new DefaultMutableTreeNode("DEPT"));
		//		rootNode.add(childNode);
		//		 
		//		childNode = new DefaultMutableTreeNode("View");
		//		    childNode.add(new DefaultMutableTreeNode("B1"));
		//		    childNode.add(new DefaultMutableTreeNode("B2"));
		//	    rootNode.add(childNode);


		//---------------------------------------------
		//1. node?Éù?Ñ±: DBÍ∞íÏúºÎ°? ?Ö∏?ìú ?Éù?Ñ±
		//---------------------------------------------
		DefaultMutableTreeNode rootNode = dbTreeData();  // <----DBÍ∞íÏúºÎ°? ?Ö∏?ìú ÎßåÎì§Í∏?

		//---------------------------------------------
		//2. Î™®Îç∏?Éù?Ñ±
		//---------------------------------------------
		DefaultTreeModel model = new DefaultTreeModel(rootNode);

		//---------------------------------------------
		//3. JTree?Éù?Ñ±
		//---------------------------------------------
		jTree = new JTree(model);	
		jTree.setSelectionModel(treeSelectionModel);	//?ã®?ùº?Ñ†?ÉùÎßåÍ??ä•?ïòÍ≤?


		//?Ñ†?Éù?ù¥Î≤§Ìä∏
		jTree.addTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode)e.getPath().getLastPathComponent();
				if(selectedNode.isLeaf()) {
					System.out.println("?Ñ†?Éù ?Öå?ù¥Î∏îÎ™Ö:" + selectedNode.getUserObject());
					
					//---------------------------------------------
					//		Í∏∞Ï°¥ ?ç∞?ù¥?Ñ∞ Ï¥àÍ∏∞?ôî Î∞©Î≤ï
					//		defaultTableModel.setNumRows(0);		
					//      for (int i = 0; i < defaultTableModel.getRowCount(); i++) {
					//      	defaultTableModel.removeRow(i);
					//      }
					//----------------------------------------------
				
					tablePanel.defaultTableModel.setNumRows(0);
					DefaultTableModel vv = tablePanel.selectColumnAndData("select * from "+ selectedNode.getUserObject().toString()); 	//Ïª¨Îüº,?ç∞?ù¥?Ñ∞: sql?ã§?ñâ Í≤∞Í≥º 
					tablePanel.jTable.setModel(vv);
					
					
				}
			}
		});
		//?éºÏπ?,?†ë?ûò ?ù¥Î≤§Ìä∏
		jTree.addTreeExpansionListener(new TreeExpansionListener() {
			public void treeCollapsed(TreeExpansionEvent e) {
				System.out.println("jTree ?†ë?ûò");

			}
			public void treeExpanded(TreeExpansionEvent e) {
				System.out.println("jTree ?éºÏ≥êÏßê");
			}
		});


		//		
		//		JEditorPane editorPane = new JEditorPane();
		//		contentPane.add(editorPane, BorderLayout.CENTER);
		//		
		//		JEditorPane editorPane_1 = new JEditorPane();
		//		contentPane.add(editorPane_1, BorderLayout.SOUTH);
		//		
		//		JPanel panel = new JPanel();
		//		contentPane.add(panel, BorderLayout.NORTH);
		//		


		//		JButton btnRun = new JButton("RUN");
		//		btnRun.addActionListener(new ActionListener() {
		//			public void actionPerformed(ActionEvent e) {
		//				System.out.println("btn click");
		//			}
		//		});
		//		panel.add(btnRun);
		
		
		JScrollPane jScollPane = new JScrollPane(jTree);
		jScollPane.setPreferredSize(new Dimension(200,600));
		contentPane.add(jScollPane);
		add(contentPane, BorderLayout.WEST);

		
	}


	public DefaultMutableTreeNode dbTreeData() {
		DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("XE");
		DefaultMutableTreeNode childNode = null;

		//--------------- DB?óê?Ñú Í∞??†∏?ò® ?Öå?ù¥Î∏? Î™©Î°ù
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
