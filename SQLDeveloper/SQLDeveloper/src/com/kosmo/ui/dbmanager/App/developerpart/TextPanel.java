package com.kosmo.ui.dbmanager.App.developerpart;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.BadLocationException;

import com.kosmo.ui.sample.JTable_panel;

public class TextPanel extends JPanel {

	private JPanel contentPane;
	JTextArea jTextArea;
	String sql = "";
	/**
	 * Create the panel.
	 */

	public TextPanel(JPanel centerPanel,TablePanel tablePanel) {
		setBorder(new EmptyBorder(5, 5, 5, 5));  
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(15,15,15,15));
		contentPane.setLayout(new BorderLayout(10, 10)); 

		jTextArea = new JTextArea();
		jTextArea.setPreferredSize(new Dimension(100, 100));
		jTextArea.setFont(new Font("Arial", Font.BOLD, 22));
		jTextArea.setSelectedTextColor(Color.BLUE);
		jTextArea.setText("");


		jTextArea.addKeyListener(new KeyAdapter() {
			@Override
			//ctrl+enter
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER && e.getModifiers() ==KeyEvent.CTRL_MASK) {

					System.out.println("===============================");
					try {

						int cursorPositon = jTextArea.getCaretPosition();
						int cursorLine = jTextArea.getLineOfOffset(cursorPositon);
						int cursorLineStartOffset = jTextArea.getLineStartOffset(cursorLine);
						int cursorLineEndOffset = jTextArea.getLineEndOffset(cursorLine);

						String findStr = ";";
						int prevIdx = jTextArea.getText().lastIndexOf(findStr, cursorPositon-findStr.length()); 
						if (prevIdx != -1) { 
							jTextArea.requestFocus();
							jTextArea.select(prevIdx, findStr.length()+prevIdx); 
						} else {
							prevIdx = cursorLineStartOffset;
						}

						int startIdx = jTextArea.getText().indexOf(findStr, cursorPositon);
						int nextIdx = startIdx + findStr.length();
						if (startIdx != -1) { 
							jTextArea.requestFocus();
							jTextArea.select(prevIdx, nextIdx);
						} else {
							jTextArea.select(0,-1);
							cursorPositon=0;
							startIdx=0;
							nextIdx=0;
						}

						String executeSqlAtCurosr = jTextArea.getText().toString().substring(prevIdx, nextIdx);
						System.out.println("[ Selected SQL ]"+executeSqlAtCurosr);
						sql = executeSqlAtCurosr.replaceAll(";","");


					} catch (BadLocationException e1) {
						e1.printStackTrace();
					}


					String selectedText = jTextArea.getSelectedText();
					if(selectedText != null) {
						int startPoint = jTextArea.getSelectionStart();
						int endPoint = jTextArea.getSelectionEnd();
						System.out.println("[SQL && Location] " + selectedText + "    " + startPoint + "," + endPoint);
						sql = selectedText.replaceAll(";","");
					}

					if(sql.toUpperCase().startsWith("SELECT")) {
						System.out.println("SELECT");

						tablePanel.defaultTableModel.setNumRows(0);
						DefaultTableModel vv = tablePanel.selectColumnAndData(sql); 
						tablePanel.jTable.setModel(vv);

					}else {
						System.out.println("");
						
					}



				}
			}
		});

		JScrollPane jScollPane = new JScrollPane(jTextArea);
		jScollPane.setBorder(new EmptyBorder(0, 10, 10, 10));
		jScollPane.setPreferredSize(new Dimension(660, 300));
		contentPane.add(jScollPane);
		add(contentPane, BorderLayout.WEST);



	}

}


