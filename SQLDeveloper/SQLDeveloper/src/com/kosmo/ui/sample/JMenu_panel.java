package com.kosmo.ui.sample;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.KeyStroke;
import java.awt.event.InputEvent;

public class JMenu_panel extends JPanel {

	private JPanel contentPane;


	/**
	 * Create the frame.
	 */
	public JMenu_panel() {
		
		contentPane = new JPanel();
//		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5,5,5,5));
		contentPane.setLayout(new BorderLayout(0, 0)); 
		
//		**************************************************
//		** JMenu ?��?���? **
//			메뉴바생?��  -> 메뉴?��?�� -> 메뉴?��?��?��?��?��
//		**************************************************
		
		
		//---------------------------------------------
		//1. 메뉴바생?��
		//---------------------------------------------
		JMenuBar menuBar = new JMenuBar();
		
		//---------------------------------------------
		//2. 메뉴?��?�� -> 메뉴?��?��?��?��?�� : File
		//---------------------------------------------
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic('F');
		//fileMenu.setMnemonic(KeyEvent.VK_F); //alt+F
				//--------------------------------------------------------
				JMenuItem openMenuItem = new JMenuItem("Open");
				openMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.ALT_MASK));
				openMenuItem.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.out.println("?���?");
					}
				});
				
				JMenuItem saveMenuItem = new JMenuItem("Save");
				saveMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
				saveMenuItem.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.out.println("???��");
					}
				});
				//--------------------------------------------------------
		fileMenu.add(openMenuItem);
		fileMenu.add(saveMenuItem);
		menuBar.add(fileMenu);
		
		
		
		//---------------------------------------------
		//2. 메뉴?��?�� -> 메뉴?��?��?��?��?�� : Edit
		//---------------------------------------------
		JMenu editMenu = new JMenu("Edit");
		editMenu.setMnemonic('E');
		//fileMenu.setMnemonic(KeyEvent.VK_E); //alt+E
				//--------------------------------------------------------
				JMenuItem copyMenuItem = new JMenuItem("Copy");
				copyMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
				copyMenuItem.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.out.println("복사");
					}
				});
				//--------------------------------------------------------
		editMenu.add(copyMenuItem);
		menuBar.add(editMenu);
		
		
		
		
		menuBar.setPreferredSize(new Dimension(600-20, 30));
		contentPane.add(menuBar);
		add(contentPane, BorderLayout.NORTH); 
		
	}

}
