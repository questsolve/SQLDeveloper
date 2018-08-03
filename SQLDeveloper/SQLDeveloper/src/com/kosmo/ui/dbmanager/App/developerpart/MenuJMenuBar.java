package com.kosmo.ui.dbmanager.App.developerpart;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuJMenuBar extends JMenuBar {

	public MenuJMenuBar(JFrame frame) {
		JMenu mnFile = new JMenu("FIle");
		add(mnFile);
		
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
		add(mnEdit);
		
		JMenu mnNavigate = new JMenu("Navigate");
		add(mnNavigate);
		
		JMenu mnRun = new JMenu("Run");
		add(mnRun);
		
		JMenu mnWindow = new JMenu("Window");
		add(mnWindow);
		
		JMenu mnHelp = new JMenu("Help");
		add(mnHelp);
		
		setPreferredSize(new Dimension(frame.getWidth()-20, 30));
	
	}
}
