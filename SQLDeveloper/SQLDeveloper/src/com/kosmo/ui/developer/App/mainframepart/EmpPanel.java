package com.kosmo.ui.developer.App.mainframepart;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.kosmo.ui.developer.App.InsertEmpFrame;
import com.kosmo.ui.developer.App.LoginFrame;
import com.kosmo.ui.developer.App.UpdateFrame;
import com.kosmo.ui.developer.service.domain.EmpVO;
import com.kosmo.ui.developer.service.domain.SqlUsageVO;
import com.kosmo.ui.developer.service.emp.EmpService;
import com.kosmo.ui.developer.service.emp.impl.EmpServiceImpl;
import com.kosmo.ui.developer.service.sql.SqlUsageService;
import com.kosmo.ui.developer.service.sql.impl.SqlUsageServiceImpl;

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
	public EmpPanel(EmpVO vo,TablePanel tablePanel, JFrame frame,TextPanel textPanal) {


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
				String sql = textPanal.sql;
				System.out.println("emp"+sql);
				downloadSQL(sql);
				sqlService = new SqlUsageServiceImpl();
				SqlUsageVO sqlVO = sqlService.selectBySQL(sql, vo);
				sqlService.updateDownloadCount(sqlVO);
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
					UpdateFrame frame = new UpdateFrame();
					frame.setVisible(true);

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

	private void downloadSQL(String sql) {
		String path = "C:\\Users\\kosmo05\\Desktop\\excelFromDB";
		String fileName = "dbEmployee.xls";
		empService = new EmpServiceImpl();
		sqlService = new SqlUsageServiceImpl();

		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("DBdata");
		XSSFRow row = null;

		row=sheet.createRow(1);

		List<Map> ColAndData = sqlService.selectByQuery(sql);

		Map column = (Map)ColAndData.get(0);
		List colNames = (List)column.get("column");
		System.out.println(colNames);

		for (int i = 0; i < colNames.size(); i++) {
			row.createCell(i+1).setCellValue(colNames.get(i).toString());

		}

		for (int i = 1; i < ColAndData.size(); i++) {
			row= sheet.createRow(i+1);
			for (int j = 0; j < colNames.size(); j++) {
				row.createCell(1).setCellValue((ColAndData.get(i)).toString());			
			}
		}

		FileOutputStream outFile=null;
		try {
			outFile = new FileOutputStream(path+"\\"+fileName);
			workbook.write(outFile);

			outFile.close();



			System.out.println("���ϻ��� �Ϸ�");

		} catch (Exception e) {

			e.printStackTrace();

		}finally {
			try {
				workbook.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}






	}

}
