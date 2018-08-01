package com.kosmo.ui.dbmanager.App;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import com.kosmo.ui.sample.JTable_panel;
import com.kosmo.ui.sample.JTextPane_panel;

public class SqlMainFrame {

	private JFrame frame;
	private TablePanel tablePanel;
	private MenuPanel menuPanel;
	private TextPanel textPanel;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SqlMainFrame window = new SqlMainFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SqlMainFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 900, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new BorderLayout(0, 0));
		
		
		
		menuPanel = new MenuPanel(frame);
		centerPanel.add(menuPanel,BorderLayout.NORTH);
		frame.add(centerPanel);
		
		
		/*
		tablePanel = new TablePanel();
		centerPanel.add(tablePanel, BorderLayout.SOUTH);
		
		//�뿉�뵒�꽣
		textPanel = new TextPanel(tablePanel);  //<--�뀒�씠釉뷀뙣�꼸蹂�寃쎌쐞�빐 二쇱냼 二쇨린
		centerPanel.add(textPanel, BorderLayout.NORTH);
		//*/
		
//		
//		
//		DefaultMutableTreeNode root = new DefaultMutableTreeNode("플레이 리스트");
//		DefaultMutableTreeNode child1 = new DefaultMutableTreeNode("힙합");
//		DefaultMutableTreeNode child2 = new DefaultMutableTreeNode("재즈");
//		DefaultMutableTreeNode child3 = new DefaultMutableTreeNode("락");
//		
//		root.add(child1);
//		root.add(child2);
//		root.add(child3);
//		
//		JTree tree = new JTree(root);
//		tree.setBackground(Color.WHITE);
//		tree.addTreeExpansionListener(new TreeExpansionListener() {
//			public void treeCollapsed(TreeExpansionEvent event) {
//				System.out.println("패스");
//			}
//			public void treeExpanded(TreeExpansionEvent event) {
//				System.out.println("오픈");
//			}
//		});
//		frame.getContentPane().add(tree, BorderLayout.WEST);
//		
//		JTextArea textArea = new JTextArea();
//		textArea.setBackground(UIManager.getColor("Button.background"));
//		frame.getContentPane().add(textArea, BorderLayout.CENTER);
//		
//		
//		
//		String columnNames[] =
//			{ "userno", "username", "job", "sal" };
//		Object rowData[][] =
//			{
//			{ 1, "맛동산", 100, "오리온" },
//			{ 2, "아폴로", 200, "불량식품" },
//			{ 3, "칸쵸코", 300, "과자계의 레전드" } };
//		
//		table =  new JTable(rowData, columnNames);
//		JScrollPane js = new JScrollPane(table);
//		frame.getContentPane().add(js, BorderLayout.SOUTH);
//		
//		
//		JButton btnConnection = new JButton("Connection");
//		btnConnection.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				EmployeeDAO empDao = new EmployeeDAOImpl();
//				List<EmpVO> list = empDao.selectAll();
//				for (EmpVO empVO : list) {
//					System.out.println(empVO);
//				}
//				System.out.println(list.size());
//			}
//		});
//		frame.getContentPane().add(btnConnection, BorderLayout.EAST);
//		
//		table_1 = new JTable();
//		frame.getContentPane().add(table_1, BorderLayout.SOUTH);
	}
}
