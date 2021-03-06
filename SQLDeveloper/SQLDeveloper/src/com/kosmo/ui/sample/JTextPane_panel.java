package com.kosmo.ui.sample;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.BadLocationException;



import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

public class JTextPane_panel extends JPanel {

	private JPanel contentPane;
	JTextArea jTextArea;
	String sql = "";
	

	/**
	 * Highlighter,
	 * Create the frame.
	 */
	public JTextPane_panel(JTable_panel tablePanel) {  //<--??΄λΈν¨?λ³?κ²½μ?΄ μ£Όμ λ°κΈ°
		contentPane = new JPanel();
//		contentPane.setBackground(Color.YELLOW);
		contentPane.setBorder(new EmptyBorder(5,5,5,5));
		contentPane.setLayout(new BorderLayout(0, 0)); 
		
////		HighlightedDocument document = new HighlightedDocument();
////		document.setHighlightStyle(HighlightedDocument.SQL_STYLE); 
//		
//		jTextArea = new JTextArea(document);
//		jTextArea.setPreferredSize(new Dimension(100, 100));
		jTextArea.setFont(new Font("Arial", Font.BOLD, 22));
		jTextArea.setSelectedTextColor(Color.red);
		jTextArea.setText("select * from emp;\n\nselect * from dept \nwhere deptno=10;");
		
//		JTextPane test = new JTextPane();
//		test.
		
		jTextArea.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER && e.getModifiers() ==KeyEvent.CTRL_MASK) {
				
				System.out.println("===============================");
				try {
					//jTextArea ?Ό?Έ ?? μ»€μ ?κΈ?
					//int offset = jTextArea.getLineEndOffset(jTextArea.getLineCount()-1);
					//jTextArea.setCaretPosition(offset);
					//System.out.println(jTextArea.getCaret().toString());
					
					
					//??¬μ»€μ(cursorPositon) ?Ό?Έ(cursorLine)? ???μΉ?(cursorLineStartOffset) κ΅¬νκΈ? 
					int cursorPositon = jTextArea.getCaretPosition();
					int cursorLine = jTextArea.getLineOfOffset(cursorPositon);
					int cursorLineStartOffset = jTextArea.getLineStartOffset(cursorLine);
					int cursorLineEndOffset = jTextArea.getLineEndOffset(cursorLine);
					//String cursorLine = jTextArea.getText().toString().substring(cursorLineStartOffset, cursorLineEndOffset);
					
					
					
					String findStr = ";";
					//??¬μ»€μ(cursorPositon) ?΄? ?  ;?μΉμ°ΎκΈ?
					int prevIdx = jTextArea.getText().lastIndexOf(findStr, cursorPositon-findStr.length()); 
					// κ²???΄ ??? κ²½μ° 
					if (prevIdx != -1) { 
						jTextArea.requestFocus();
						jTextArea.select(prevIdx, findStr.length()+prevIdx); 
					} else {
						prevIdx = cursorLineStartOffset;
					}
					
					//??¬μ»€μ(cursorPositon) ?΄??  ;?μΉμ°ΎκΈ?
					int startIdx = jTextArea.getText().indexOf(findStr, cursorPositon);
					int nextIdx = startIdx + findStr.length();
					// κ²???΄ ??? κ²½μ°
					if (startIdx != -1) { 
						jTextArea.requestFocus();
						jTextArea.select(prevIdx, nextIdx);
					} else {
						//λ¬Έμ?
						jTextArea.select(0,-1);
						cursorPositon=0;
						startIdx=0;
						nextIdx=0;
					}
					
					String executeSqlAtCurosr = jTextArea.getText().toString().substring(prevIdx, nextIdx);
					System.out.println("[?€?SQL]"+executeSqlAtCurosr);
					sql = executeSqlAtCurosr.replaceAll(";","");
					
//					System.out.println("[cursorLine]"+cursorLine + " cursorPositon:" + cursorPositon);
				} catch (BadLocationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				//??κ·? ? ?λ¬Έμ₯ λ³΄μ¬μ£ΌκΈ°
				String selectedText = jTextArea.getSelectedText();
				if(selectedText != null) {
					int startPoint = jTextArea.getSelectionStart();
					int endPoint = jTextArea.getSelectionEnd();
					System.out.println("[??κ·?] " + selectedText + "    " + startPoint + "," + endPoint);
					sql = selectedText.replaceAll(";","");
				}
				
				
				tablePanel.defaultTableModel.setNumRows(0);
				DefaultTableModel vv = tablePanel.selectColumnAndData(sql); 
				tablePanel.jTable.setModel(vv);
				
				
				
//				System.out.println("===============================");
//				//?Έλ―Έμ½λ‘?(;) ?¨?λ‘? sqlλΆλ¦¬
//				if(selectedText != null) {
//					String[] sqlArr = selectedText.split(";");
//					for(int i=0; i<sqlArr.length; i++) {
//						if(!sqlArr[i].trim().equals("")) {
//							System.out.println("(" + sqlArr[i] + ")");
//						}
//					}
//					System.out.println("===============================");
//					System.out.println("(" + sqlArr[sqlArr.length-1] + ")");
//				}
				
				}
			}
		});
		
		JScrollPane jScollPane = new JScrollPane(jTextArea);
		jScollPane.setPreferredSize(new Dimension(660, 300));
		contentPane.add(jScollPane);
		add(contentPane, BorderLayout.WEST);
		
		
		
	}

}

