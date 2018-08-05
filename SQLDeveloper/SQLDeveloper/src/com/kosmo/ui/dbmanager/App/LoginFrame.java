package com.kosmo.ui.dbmanager.App;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.kosmo.ui.dbmanager.App.developerpart.MenuPanel;
import com.kosmo.ui.dbmanager.service.domain.EmpVO;
import com.kosmo.ui.dbmanager.service.emp.EmpService;
import com.kosmo.ui.dbmanager.service.emp.impl.EmpServiceImpl;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginFrame extends JFrame {

	private JPanel contentPane;
	private JTextField empNoTextField;
	private JTextField pwTextField;
	private JButton loginButton;
	private JButton resetButton;
	private MenuPanel menuPanel;
	private EmpService empService;
	private EmpVO empVo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
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
	public LoginFrame() {
		empService = new EmpServiceImpl();
		empVo = new EmpVO();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 225);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		menuPanel = new MenuPanel(this);
		contentPane.add(menuPanel, BorderLayout.NORTH);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(3, 2, 5, 5));

		JLabel lblNewLabel = new JLabel("EMPLOYEE NUMBER");
		panel.add(lblNewLabel);

		empNoTextField = new JTextField();
		panel.add(empNoTextField);
		empNoTextField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("PASSWORD");
		panel.add(lblNewLabel_1);

		pwTextField = new JTextField();
		pwTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() ==e.VK_ENTER) {
					chekcEmpFromDB(empVo);
				}
			}
		});
		panel.add(pwTextField);
		pwTextField.setColumns(10);

		loginButton = new JButton("LOGIN");
		loginButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {	
				chekcEmpFromDB(empVo);
			}
			
		});
		panel.add(loginButton);

		resetButton = new JButton("RESET");
		resetButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				empNoTextField.setText("");
				pwTextField.setText("");
				JOptionPane.showMessageDialog(null, "resetButton Clicked");
			}
		});
		panel.add(resetButton);
	}

	public void chekcEmpFromDB(EmpVO vo) {
		
		if(!(empNoTextField.getText().equals("") || empNoTextField.getText()==null)&&
		   !(pwTextField.getText().equals("") || pwTextField.getText()==null)) {
				empVo.setEmpno(Integer.parseInt(empNoTextField.getText()));
				empVo.setPw(pwTextField.getText());
				empVo = empService.select(empVo);
				System.out.println(empVo);
				if(empVo.getEmpno() ==0) {
					JOptionPane.showMessageDialog(null, "id && pw Check please");
				}else {
					MainPanel sqlFrame = new MainPanel(empVo);
					setVisible(false);
					sqlFrame.setVisible(true);
				}
			}else {
				JOptionPane.showMessageDialog(null, "id && pw Check please");
			}
		
	}
}
