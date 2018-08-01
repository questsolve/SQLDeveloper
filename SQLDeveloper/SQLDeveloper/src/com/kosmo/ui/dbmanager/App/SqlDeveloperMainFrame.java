package com.kosmo.ui.dbmanager.App;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;




public class SqlDeveloperMainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private MenuPanel menuPanel;
	private TablePanel tablePanel;
	private TextPanel textPanel;

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
		setBounds(100, 100, 900, 600);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		menuPanel = new MenuPanel(this);
		contentPane.add(menuPanel, BorderLayout.NORTH);
		
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new BorderLayout(0, 0));
		contentPane.add(centerPanel, BorderLayout.CENTER);
		
		tablePanel = new TablePanel();
		centerPanel.add(tablePanel, BorderLayout.SOUTH);
		

		textPanel = new TextPanel(tablePanel); 
		centerPanel.add(textPanel, BorderLayout.NORTH);
		
		/*

		treePanel = new  
		contentPanel.add(treePanel, BorderLayout.WEST);

		 */	
		setContentPane(contentPane);
	}

}
