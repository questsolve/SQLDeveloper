package com.kosmo.ui.dbmanager.App;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;

import com.kosmo.ui.dbmanager.App.developerpart.MenuPanel;
import com.kosmo.ui.dbmanager.App.developerpart.TablePanel;
import com.kosmo.ui.dbmanager.App.developerpart.TextPanel;
import com.kosmo.ui.dbmanager.App.developerpart.TreeAppendPanel;
import javax.swing.JLabel;
import java.awt.Color;


public class SqlDeveloperMainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	
	private MenuPanel menuPanel;
	private TablePanel tablePanel;
	private TextPanel textPanel;
	private TreeAppendPanel treePanel;
	private JTree tree;
	private JPanel panel;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SqlDeveloperMainFrame frame = new SqlDeveloperMainFrame();
					
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
	
	public SqlDeveloperMainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 0, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new BorderLayout(0, 0));
		
		
		
		tablePanel = new TablePanel();
		centerPanel.add(tablePanel, BorderLayout.SOUTH);
		
		
		textPanel = new TextPanel(centerPanel,tablePanel);
		centerPanel.add(textPanel, BorderLayout.CENTER);

		

		///*
		treePanel = new TreeAppendPanel();
		contentPane.add(treePanel, BorderLayout.WEST);
		//*/
		menuPanel = new MenuPanel(this);
		contentPane.add(menuPanel, BorderLayout.NORTH);
		contentPane.add(centerPanel);
		setContentPane(contentPane);
		
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.EAST);
		
		lblNewLabel = new JLabel("사원 정보");
		lblNewLabel.setBackground(Color.YELLOW);
		panel.add(lblNewLabel);
	}

}
