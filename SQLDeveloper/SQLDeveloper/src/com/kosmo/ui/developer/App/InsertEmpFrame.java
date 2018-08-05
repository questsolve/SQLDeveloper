package com.kosmo.ui.developer.App;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.kosmo.ui.developer.service.domain.EmpVO;
import com.kosmo.ui.developer.service.emp.EmpService;
import com.kosmo.ui.developer.service.emp.impl.EmpServiceImpl;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.GridLayout;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class InsertEmpFrame extends JFrame {

	private JPanel contentPane;
	private JTextField nameTextField;
	private JTextField jobTextField;
	private JComboBox<String> authComboBox;
	private EmpService empService;
	private JTextField passwordField;
	private JTextField passwordCheckField;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InsertEmpFrame frame = new InsertEmpFrame();
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
	public InsertEmpFrame() {

		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(5, 5));

		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new GridLayout(6, 2, 10, 10));

		JLabel empNameLabel = new JLabel("Name");
		panel.add(empNameLabel);

		nameTextField = new JTextField();
		panel.add(nameTextField);
		nameTextField.setColumns(10);

		JLabel passwordLabel = new JLabel("Password");
		panel.add(passwordLabel);
		
		passwordField = new JTextField();
		panel.add(passwordField);
		passwordField.setColumns(10);

		JLabel passwordCheckLabel = new JLabel("Password  Check");
		panel.add(passwordCheckLabel);
		
		passwordCheckField = new JTextField();
		panel.add(passwordCheckField);
		passwordCheckField.setColumns(10);

		JLabel departmentLabel = new JLabel("Department");
		panel.add(departmentLabel);

		JComboBox<String> departComboBox = new JComboBox<String>();
		departComboBox.insertItemAt("개발총괄팀",0);
		departComboBox.insertItemAt("개발1팀",1);
		departComboBox.insertItemAt("개발2팀",2);
		departComboBox.insertItemAt("개발3팀",3);
		departComboBox.insertItemAt("개발4팀",4);
		panel.add(departComboBox);

		JLabel jobLabel = new JLabel("Job");
		panel.add(jobLabel);

		jobTextField = new JTextField();
		panel.add(jobTextField);
		jobTextField.setColumns(10);

		JLabel authorityLabel = new JLabel("Authority");
		panel.add(authorityLabel);

		authComboBox = new JComboBox<String>();
		authComboBox.insertItemAt("일반사원", 0);
		authComboBox.insertItemAt("팀장급", 1);
		authComboBox.insertItemAt("DBA", 2);
		panel.add(authComboBox);

		JPanel utilPanel = new JPanel();
		contentPane.add(utilPanel, BorderLayout.SOUTH);

		JButton insertButton = new JButton("Add New Employee");
		insertButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				empService = new EmpServiceImpl();
				
				if(nameTextField.getText() == null || nameTextField.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Insert name of the Employee");
					return;
				}

				if(passwordField.getText() == null || passwordField.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "password weren't inserted");
					return;
				}
				
				
				if(passwordCheckField.getText() == null 
				|| passwordCheckField.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "password for Check weren't inserted");
					return;
				}
				
				
				
				
				
				/*
				if(!( (password).equals(passwordCheck) ) ) {
					
					JOptionPane.showMessageDialog(null, "password  And password for check doesn't match each other");
					return;
				}
				*/
		
				if(departComboBox.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(null, "Select department");
					return;
				}
				
				if(jobTextField.getText() == null || jobTextField.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Insert employee's job");
					return;
				}
				
				if(authComboBox.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(null, "Select boundary of Query");
					return;
				}
				
				
				EmpVO vo = new EmpVO();
				vo.setAuth(authComboBox.getSelectedIndex()+1);
				vo.setDeptno(departComboBox.getSelectedIndex()+10);
				vo.setEmpName(nameTextField.getText());
				vo.setJob(jobTextField.getText());
				vo.setPw(passwordField.getText());
				
				empService.insert(vo);
				
				


			}
		});
		utilPanel.add(insertButton);

		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
			}
		});
		utilPanel.add(btnNewButton_1);
		
		
	}

}
