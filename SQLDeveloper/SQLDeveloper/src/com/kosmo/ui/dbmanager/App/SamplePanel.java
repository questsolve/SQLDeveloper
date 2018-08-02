package com.kosmo.ui.dbmanager.App;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JEditorPane;

public class SamplePanel extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SamplePanel frame = new SamplePanel();
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
	public SamplePanel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel treePanel = new JPanel();
		contentPane.add(treePanel, BorderLayout.WEST);
		treePanel.setLayout(new BorderLayout(0, 0));
		
		JTree tree = new JTree();
		treePanel.add(tree);
		
		JPanel textPanel = new JPanel();
		contentPane.add(textPanel, BorderLayout.CENTER);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.TRAILING);
		textPanel.add(textField);
		textField.setColumns(10);
		
		JPanel tablePanel = new JPanel();
		contentPane.add(tablePanel, BorderLayout.SOUTH);
		
		table = new JTable();
		tablePanel.add(table);
		
		JPanel empPanel = new JPanel();
		contentPane.add(empPanel, BorderLayout.EAST);
		
		JEditorPane editorPane = new JEditorPane();
		empPanel.add(editorPane);
	}

}
