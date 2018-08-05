package com.kosmo.ui.dbmanager.App;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.kosmo.ui.dbmanager.App.developerpart.EmpPanel;
import com.kosmo.ui.dbmanager.App.developerpart.MenuJMenuBar;
import com.kosmo.ui.dbmanager.App.developerpart.TablePanel;
import com.kosmo.ui.dbmanager.App.developerpart.TextPanel;
import com.kosmo.ui.dbmanager.App.developerpart.TreeAppendPanel;
import com.kosmo.ui.dbmanager.service.domain.EmpVO;
import com.kosmo.ui.dbmanager.service.emp.impl.EmpServiceImpl;

import javax.swing.JMenuBar;

import java.awt.Color;
import javax.swing.border.LineBorder;

public class SamplePanel extends JFrame {

	private JPanel contentPane;
	JPanel centerPanel;
	JPanel tablPanel;
	private JPanel UserPanel;

	/**
	 * Launch the application.
	 */
	///*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmpServiceImpl empServiceImpl = new EmpServiceImpl();
					EmpVO vo = new EmpVO();
					vo.setEmpno(1);
					vo.setPw("1234");
					
					vo = empServiceImpl.select(vo);
					System.out.println(vo);
					SamplePanel frame = new SamplePanel(vo);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
//*/
	/**
	 * Create the frame.
	 */
	public SamplePanel(EmpVO vo) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 675);

		JMenuBar menuBar = new MenuJMenuBar(this);
		setJMenuBar(menuBar);
		

		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(5, 5));
		
		TablePanel BottomPanel = new TablePanel();

		
		TextPanel textPanel = new TextPanel(centerPanel,this,BottomPanel,vo);
		textPanel.setBorder(null);

		centerPanel = new JPanel();
		centerPanel.setBackground(Color.WHITE);

		contentPane.add(centerPanel, BorderLayout.CENTER);
		
		centerPanel.setLayout(new BorderLayout(5, 5));
		centerPanel.add(textPanel, BorderLayout.CENTER);

		centerPanel.add(BottomPanel, BorderLayout.SOUTH);

		setContentPane(contentPane);
		
		TreeAppendPanel treePanel = new TreeAppendPanel(BottomPanel,vo);
		
		tablPanel = new JPanel();
		BottomPanel.add(tablPanel);
		BottomPanel.setVisible(false);
		contentPane.add(treePanel, BorderLayout.WEST);
		
		UserPanel = new EmpPanel(vo, BottomPanel,this,textPanel);
		UserPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		centerPanel.add(UserPanel, BorderLayout.EAST);
				
	}

}
