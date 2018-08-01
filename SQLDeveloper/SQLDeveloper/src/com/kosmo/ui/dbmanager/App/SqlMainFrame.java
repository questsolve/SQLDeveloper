package com.kosmo.ui.dbmanager.App;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
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

public class SqlMainFrame {

	private JFrame frame;
	private JTable table;
	private JTable table_1;

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
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("FIle");
		menuBar.add(mnFile);
		
		JMenuItem mntmNew = new JMenuItem("New");
		mnFile.add(mntmNew);
		
		JMenuItem mntmOpen = new JMenuItem("Open");
		mnFile.add(mntmOpen);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mnFile.add(mntmSave);
		
		JMenuItem mntmSaveAs = new JMenuItem("Save as");
		mnFile.add(mntmSaveAs);
		
		JMenuItem mntmRestart = new JMenuItem("Restart");
		mnFile.add(mntmRestart);
		
		JMenuItem mntmQuit = new JMenuItem("Exit");
		mnFile.add(mntmQuit);
		
		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
		JMenu mnNavigate = new JMenu("Navigate");
		menuBar.add(mnNavigate);
		
		JMenu mnRun = new JMenu("Run");
		menuBar.add(mnRun);
		
		JMenu mnWindow = new JMenu("Window");
		menuBar.add(mnWindow);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("플레이 리스트");
		DefaultMutableTreeNode child1 = new DefaultMutableTreeNode("힙합");
		DefaultMutableTreeNode child2 = new DefaultMutableTreeNode("재즈");
		DefaultMutableTreeNode child3 = new DefaultMutableTreeNode("락");
		
		root.add(child1);
		root.add(child2);
		root.add(child3);
		
		JTree tree = new JTree(root);
		tree.setBackground(Color.WHITE);
		tree.addTreeExpansionListener(new TreeExpansionListener() {
			public void treeCollapsed(TreeExpansionEvent event) {
				System.out.println("패스");
			}
			public void treeExpanded(TreeExpansionEvent event) {
				System.out.println("오픈");
			}
		});
		frame.getContentPane().add(tree, BorderLayout.WEST);
		
		JTextArea textArea = new JTextArea();
		textArea.setBackground(UIManager.getColor("Button.background"));
		frame.getContentPane().add(textArea, BorderLayout.CENTER);
		
		
		
		String columnNames[] =
			{ "userno", "username", "job", "sal" };
		Object rowData[][] =
			{
			{ 1, "맛동산", 100, "오리온" },
			{ 2, "아폴로", 200, "불량식품" },
			{ 3, "칸쵸코", 300, "과자계의 레전드" } };
		
		table =  new JTable(rowData, columnNames);
		JScrollPane js = new JScrollPane(table);
		frame.getContentPane().add(js, BorderLayout.SOUTH);
		
		
		JButton btnConnection = new JButton("Connection");
		btnConnection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				EmployeeDAO empDao = new EmployeeDAOImpl();
//				List<EmpVO> list = empDao.selectAll();
//				for (EmpVO empVO : list) {
//					System.out.println(empVO);
//				}
//				System.out.println(list.size());
			}
		});
		frame.getContentPane().add(btnConnection, BorderLayout.EAST);
		
		table_1 = new JTable();
		frame.getContentPane().add(table_1, BorderLayout.SOUTH);
	}
}
