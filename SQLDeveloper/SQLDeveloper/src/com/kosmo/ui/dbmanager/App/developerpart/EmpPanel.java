package com.kosmo.ui.dbmanager.App.developerpart;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;

import com.kosmo.ui.dbmanager.App.LoginFrame;
import com.kosmo.ui.dbmanager.service.domain.EmpVO;
import com.kosmo.ui.dbmanager.service.emp.EmpService;
import com.kosmo.ui.dbmanager.service.sql.SqlUsageService;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import java.awt.FlowLayout;

public class EmpPanel extends JPanel {

	private EmpService empService;
	private SqlUsageService sqlService;
	private String sql;
	/**
	 * Create the panel.
	 */
	public EmpPanel(EmpVO vo,TablePanel tablePanel, JFrame frame) {


		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		JTextArea textArea = new JTextArea(reassembleVO(vo));

		panel.add(textArea);
		textArea.setEditable(false);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		panel.add(tabbedPane, BorderLayout.SOUTH);

		JPanel empTab = new JPanel();
		tabbedPane.addTab("Emp", null, empTab, null);

		JButton downExcel = new JButton("DownloadToPC");
		empTab.add(downExcel);

		JButton LogOut = new JButton("LogOut");
		empTab.add(LogOut);
		LogOut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				(new LoginFrame()).setVisible(true);
			}
		});
		downExcel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null,"download Table By Excel in your PC");
			}
		});

		if(vo.getAuth()==3) {

			JPanel dbaEmp = new JPanel();
			tabbedPane.addTab("DBA", null, dbaEmp, null);

			JButton addNewEmp = new JButton("AddNewUser");
			dbaEmp.add(addNewEmp);

			JButton updateAuth = new JButton("UpdateAuth");
			updateAuth.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					JOptionPane.showMessageDialog(null,"update Authority");
				}
			});
			dbaEmp.add(updateAuth);
			addNewEmp.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					InsertEmpFrame insertFrame = new InsertEmpFrame();
					insertFrame.setVisible(true);

				}
			});


		}

	}

	public String reassembleVO(EmpVO vo) {
		StringBuilder sb = new StringBuilder();
		sb.append("EMP No.    - ").append(vo.getEmpno()).append("\n");
		sb.append("Name       - ").append(vo.getEmpName()).append("\n");
		sb.append("JOB        - ").append(vo.getJob()).append("\n");
		sb.append("department - ").append(vo.getDeptName()).append("\n");

		String level = "";
		if(vo.getAuth()==3) {
			level = "DBA";
		}else if(vo.getAuth()==2) {
			level = "DML";
		}else {
			level = "Only SELECT & INSERT";
		}

		sb.append("Authority  - ").append(level).append("\n");



		return sb.toString();
	}


}
