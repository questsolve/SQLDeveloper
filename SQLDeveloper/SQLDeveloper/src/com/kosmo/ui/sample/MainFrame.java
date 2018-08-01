package com.kosmo.ui.sample;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MainFrame extends JFrame {

	private JPanel contentPanel;
	
	
	public JMenu_panel menuPanel;
	public JTree_panel treePanel;
	public JTextPane_panel textPanel;
	public JTable_panel tablePanel;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					
//					//硫붾돱諛�
//					JPanel menuPanel = new JMenu_panel(frame);
//					frame.add(menuPanel, BorderLayout.NORTH);
//					
//					//�듃由�
//					JPanel treePanel = new JTree_panel();
//					frame.add(treePanel, BorderLayout.WEST);
//					
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
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 0, 900, 700);
		
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(0,0,0,0));
		contentPanel.setLayout(new BorderLayout(0, 0));
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new BorderLayout(0, 0));
	
		
		//�뀒�씠釉�
		tablePanel = new JTable_panel();
		centerPanel.add(tablePanel, BorderLayout.SOUTH);
		
		//�뿉�뵒�꽣
		textPanel = new JTextPane_panel(tablePanel);  //<--�뀒�씠釉뷀뙣�꼸蹂�寃쎌쐞�빐 二쇱냼 二쇨린
		centerPanel.add(textPanel, BorderLayout.NORTH);
		
		//�듃由�
		treePanel = new JTree_panel(tablePanel); //<--�뀒�씠釉뷀뙣�꼸蹂�寃쎌쐞�빐 二쇱냼 二쇨린
		contentPanel.add(treePanel, BorderLayout.WEST);
		
		//硫붾돱諛�
		menuPanel = new JMenu_panel();
		contentPanel.add(menuPanel, BorderLayout.NORTH);
		
		
		
		
		
		contentPanel.add(centerPanel, BorderLayout.CENTER);
		setContentPane(contentPanel);
		
		
	}

}
