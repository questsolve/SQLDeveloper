package com.kosmo.ui.dbmanager.App.developerpart;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MenuPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	
	
	/**
	 * Create the panel.
	 */
	public MenuPanel(JFrame frame) {
		
		contentPane = new JPanel();
//		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5,5,5,5));
		contentPane.setLayout(new BorderLayout(0, 0));
		
		
		JMenuBar menuBar = new JMenuBar();
		
		
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
		
		menuBar.setPreferredSize(new Dimension(frame.getWidth()-20, 30));
		//menuBar.setPreferredSize(contentPane.getSize());
		contentPane.add(menuBar);
		
		
		
		add(contentPane,BorderLayout.NORTH);
	}

}
