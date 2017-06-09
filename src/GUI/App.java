package GUI;
import java.awt.EventQueue;
import rsa.RSA;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Point;

import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JSlider;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import DES.Encrypt;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import net.miginfocom.swing.MigLayout;


import java.awt.Button;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JTabbedPane;
import java.awt.Color;
public class App {
	private JFrame frame;
	 private static char[][] charTable;
	    private static Point[] positions;
	private JTextField textField;
	private  static JTextField textField_1;
	public static JTextField textField_2;
	public static JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	String message;
	String encryptedMessage;
	public String encrypted;
	public static JTextArea  textArea_3 = new JTextArea();
	JPanel panel_3 = new JPanel();
	JTextArea textArea_4 = new JTextArea();
	public Encrypt en;
	public static JTextField textField_6;
	public static JTextPane textPane_1 = new JTextPane();
	public static JTextPane textPane_4 = new JTextPane();
	private JTextField textField_7;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App window = new App();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public App() {
		initialize();
	}
	
	public static String prepareText(String s, boolean changeJtoI) {
        s = s.toUpperCase().replaceAll("[^A-Z]", "");
        return changeJtoI ? s.replace("J", "I") : s.replace("Q", "");
    }
 
    private static void createTable(String key, boolean changeJtoI) {
        charTable = new char[5][5];
        positions = new Point[26];
 
        String s = prepareText(key + "ABCDEFGHIJKLMNOPQRSTUVWXYZ", changeJtoI);
 
        int len = s.length();
        for (int i = 0, k = 0; i < len; i++) {
            char c = s.charAt(i);
            if (positions[c - 'A'] == null) {
                charTable[k / 5][k % 5] = c;
                positions[c - 'A'] = new Point(k % 5, k / 5);
                k++;
            }
        }
    }
 
    public static String encode(String s) {
        StringBuilder sb = new StringBuilder(s);
 
        for (int i = 0; i < sb.length(); i += 2) {
 
            if (i == sb.length() - 1)
                sb.append(sb.length() % 2 == 1 ? 'X' : "");
 
            else if (sb.charAt(i) == sb.charAt(i + 1))
                sb.insert(i + 1, 'X');
        }
        return codec(sb, 1);
    }
 
    private static String decode(String s) {
        return(codec(new StringBuilder(s), 4)).toString();
    }
 
    private static String codec(StringBuilder text, int direction) {
        int len = text.length();
        for (int i = 0; i < len; i += 2) {
            char a = text.charAt(i);
            char b = text.charAt(i + 1);
 
            int row1 = positions[a - 'A'].y;
            int row2 = positions[b - 'A'].y;
            int col1 = positions[a - 'A'].x;
            int col2 = positions[b - 'A'].x;
 
            if (row1 == row2) {
                col1 = (col1 + direction) % 5;
                col2 = (col2 + direction) % 5;
 
            } else if (col1 == col2) {
                row1 = (row1 + direction) % 5;
                row2 = (row2 + direction) % 5;
 
            } else {
                int tmp = col1;
                col1 = col2;
                col2 = tmp;
            }
 
            text.setCharAt(i, charTable[row1][col1]);
            text.setCharAt(i + 1, charTable[row2][col2]);
        }
        return text.toString();
    }
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBackground(Color.LIGHT_GRAY);
		frame.setBounds(300, 300, 554, 492);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, "cell 0 0,grow");
		
		JScrollPane scrollPane = new JScrollPane();
		tabbedPane.addTab("Caesar", null, scrollPane, null);
		
		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(new MigLayout("", "[89px][grow][][][][][][][]", "[23px][][][][grow]"));
		
		JLabel lblText = new JLabel("Text:");
		panel.add(lblText, "cell 0 0,alignx trailing");
		JScrollPane scrollPane_1 = new JScrollPane();
		tabbedPane.addTab("Playfair", null, scrollPane_1, null);
		
		JPanel panel_1 = new JPanel();
		scrollPane_1.setViewportView(panel_1);
		panel_1.setLayout(new MigLayout("", "[][grow][grow]", "[][][][][][grow]"));
		
		JLabel lblText_1 = new JLabel("Text:");
		panel_1.add(lblText_1, "flowx,cell 1 0");
		
		textField_2 = new JTextField();
		panel_1.add(textField_2, "cell 1 0,alignx center");
		textField_2.setColumns(10);
		
		JLabel lblMesajCriptare = new JLabel("Cipher message:");
		panel_1.add(lblMesajCriptare, "flowx,cell 1 1");
		
		textField_3 = new JTextField();
		panel_1.add(textField_3, "cell 1 1,alignx center");
		textField_3.setColumns(10);
		textField = new JTextField();
		panel.add(textField, "cell 1 0 3 1,growx");
		textField.setColumns(10);
		
		JLabel lblCifru = new JLabel("Cipher");
		panel.add(lblCifru, "cell 0 1,alignx trailing");
		
		textField_1 = new JTextField();
		panel.add(textField_1, "cell 1 1 3 1,growx");
		textField_1.setColumns(10);

		JTextPane textPane = new JTextPane();
		panel.add(textPane, "cell 1 4 6 1,grow");
		
		JLabel lblJSauI = new JLabel("J or I?");
		panel_1.add(lblJSauI, "flowx,cell 1 2");
		
		textField_6 = new JTextField();
		panel_1.add(textField_6, "cell 1 2,alignx left");
		textField_6.setColumns(10);
		
		JButton btnEncrypt_1 = new JButton("Encrypt");
		btnEncrypt_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String key = textField_3.getText();
		        String txt = textField_2.getText();
		        String jti = textField_6.getText();
		 String enc;
		       boolean changeJtoI = jti.equalsIgnoreCase("y");
		 
		        createTable(key, changeJtoI);
		 
		        enc = encode(prepareText(txt, changeJtoI));
		        textArea_4.setText(enc);
		     //textPane_1.append(enc);
			}
		});
		panel_1.add(btnEncrypt_1, "flowx,cell 1 3");
		
		JScrollPane scrollPane_10 = new JScrollPane();
		panel_1.add(scrollPane_10, "cell 1 5,grow");
		
		JLabel lblTextCifrat = new JLabel("Encripted Text");
		scrollPane_10.setColumnHeaderView(lblTextCifrat);
		
		
		scrollPane_10.setViewportView(textArea_4);
		
		JScrollPane scrollPane_11 = new JScrollPane();
		panel_1.add(scrollPane_11, "cell 2 5,grow");
		
		JLabel lblTextDecriptat_1 = new JLabel("Decripted Text");
		scrollPane_11.setColumnHeaderView(lblTextDecriptat_1);
		
		JTextArea textArea_5 = new JTextArea();
		scrollPane_11.setViewportView(textArea_5);
		JButton btnDecrypt = new JButton("Decrypt");
		btnDecrypt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String key = App.textField_3.getText();
			        String txt = App.textField_2.getText();
			        String jti = App.textArea_3.getText();
			 String enc;
			       boolean changeJtoI = jti.equalsIgnoreCase("y");
			 
			        createTable(key, changeJtoI);
			 
			        enc = encode(prepareText(txt, changeJtoI));
			 
			     textArea_5.setText(decode(enc));
			}
		});
		panel_1.add(btnDecrypt, "cell 1 3,alignx right");
		
		JScrollPane scrollPane_2 = new JScrollPane();
		tabbedPane.addTab("Viginere", null, scrollPane_2, null);
		
		JPanel panel_2 = new JPanel();
		scrollPane_2.setViewportView(panel_2);
		panel_2.setLayout(new MigLayout("", "[grow][grow]", "[][grow]"));
		
		JLabel lblCheie = new JLabel("Key:");
		panel_2.add(lblCheie, "flowx,cell 0 0,alignx right");
		
		textField_4 = new JTextField();
		panel_2.add(textField_4, "cell 0 0,grow");
		textField_4.setColumns(10);
		
		JScrollPane scrollPane_5 = new JScrollPane();
		panel_2.add(scrollPane_5, "cell 0 1,grow");
		
		JTextPane textPane_2 = new JTextPane();
		scrollPane_5.setViewportView(textPane_2);
		
		JLabel lblTextOriginal = new JLabel("Original Text");
		scrollPane_5.setColumnHeaderView(lblTextOriginal);
		
		JScrollPane scrollPane_6 = new JScrollPane();
		panel_2.add(scrollPane_6, "cell 1 1,grow");
		
		JTextPane textPane_3 = new JTextPane();
		scrollPane_6.setViewportView(textPane_3);
		
		JLabel lblTextCriptat = new JLabel("Cripted Text");
		scrollPane_6.setColumnHeaderView(lblTextCriptat);
		JTextArea textArea_2 = new JTextArea();
		JButton btnCriptare_2 = new JButton("Encrypt");
		btnCriptare_2.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {
				 int i, j = 0;
			        
			        String cipher = "";
			        String key = textField_4.getText().toUpperCase();
			        String plain = textPane_2.getText().toUpperCase();
			        encrypted = key;
			        int max = key.length();
			        
			        for(i = 0; i < plain.length(); i++) {
			            
			            char x = plain.charAt(i);
			            
			            if(j >= max ){
			                j = 0;
			            }
			            
			            if(x > 'A' || x < 'Z') {
			                cipher += (char)((x + key.charAt(j)- 2 * 'A') % 26 + 'A');
			            }
			            
			            j++;
			        }
			        
			        textPane_3.setText(cipher);
				}
			}
		);
		panel_2.add(btnCriptare_2, "flowx,cell 1 0");
		
		JButton btnDecriptare_1 = new JButton("Decrypt");
		btnDecriptare_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 int i, j = 0;
			        
			        String plain = "";
			        String key = textField_4.getText().toUpperCase();
			        String cipher = textPane_3.getText().toUpperCase();
			        
			        int max = key.length();
			        
			        for(i = 0; i < cipher.length(); i++) {
			            
			            char x = cipher.charAt(i);
			            
			            if(j >= max) {
			                j = 0;
			            }
			            
			            if(x > 'A' || x < 'Z') {
			                plain += (char)((x - key.charAt(j) + 26) % 26 + 'A');
			            }
			            
			            j++;
			        }
			        
			        textPane_3.setText(plain);
			
			}
		});
		panel_2.add(btnDecriptare_1, "cell 1 0");
		
		JScrollPane scrollPane_3 = new JScrollPane();
		tabbedPane.addTab("DES", null, scrollPane_3, null);

		JTextArea textArea_1 = new JTextArea();
		
		scrollPane_3.setViewportView(panel_3);
		JTextArea textArea = new JTextArea();
		JButton btnCriptare = new JButton("Encrypt");
		
		btnCriptare.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {
				 String plaintext=textArea.getText();
	                String keyworD=textField_5.getText();
	                textArea_3.append("Cheie : "+keyworD+'\n');
	                textArea_3.append("Text Original : "+plaintext+'\n');
	                en= new Encrypt(plaintext,keyworD);
	                en.DoEncryption();
	                 
	                textArea_1.append(en.getEncryption());
			}
		});
		panel_3.setLayout(new MigLayout("", "[118px][124px][147px][104px]", "[23px][371px]"));
		panel_3.add(btnCriptare, "cell 0 0,alignx left,aligny top");
		
		JButton btnDecriptare = new JButton("Decrypt");
		btnDecriptare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    en.DoDecryption();
                textArea_2.append("\n "+en.getBinDec());
                textArea_2.setCaretPosition(textArea_2. getDocument().getLength());
                textArea_2.append("\n"+en.getDecryption());

			}
		});
		panel_3.add(btnDecriptare, "cell 1 0,alignx left,aligny top");
		
		JLabel lblCheie_1 = new JLabel("Key:");
		panel_3.add(lblCheie_1, "cell 2 0,alignx right,aligny center");
		
		textField_5 = new JTextField();
		panel_3.add(textField_5, "cell 3 0,growx,aligny center");
		textField_5.setColumns(10);
		
		JScrollPane scrollPane_7 = new JScrollPane();
		panel_3.add(scrollPane_7, "cell 0 1,grow");
		
		
		scrollPane_7.setViewportView(textArea);
		
		JLabel lblTextOriginal_1 = new JLabel("Original Text");
		scrollPane_7.setColumnHeaderView(lblTextOriginal_1);
		
		JScrollPane scrollPane_12 = new JScrollPane();
		panel_3.add(scrollPane_12, "cell 1 1,grow");
		
		JLabel lblExecutie = new JLabel("Execution");
		scrollPane_12.setColumnHeaderView(lblExecutie);
		
		
		scrollPane_12.setViewportView(textArea_3);
		
		JScrollPane scrollPane_9 = new JScrollPane();
		panel_3.add(scrollPane_9, "cell 2 1,grow");
		
		scrollPane_9.setViewportView(textArea_1);
		
		JLabel lblTextCriptat_1 = new JLabel("Cripted Text");
		scrollPane_9.setColumnHeaderView(lblTextCriptat_1);
		
		JScrollPane scrollPane_8 = new JScrollPane();
		panel_3.add(scrollPane_8, "cell 3 1,grow");
		
		
		scrollPane_8.setViewportView(textArea_2);
		
		JLabel lblTextDecriptat = new JLabel("Decrypted Text");
		scrollPane_8.setColumnHeaderView(lblTextDecriptat);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		tabbedPane.addTab("RSA", null, scrollPane_4, null);
		
		JPanel panel_4 = new JPanel();
		scrollPane_4.setViewportView(panel_4);
		panel_4.setLayout(new MigLayout("", "[][grow]", "[][]"));
		
		JLabel lblText_2 = new JLabel("Text:");
		panel_4.add(lblText_2, "cell 0 0,alignx trailing");
		
		textField_7 = new JTextField();
		panel_4.add(textField_7, "cell 1 0,growx");
		textField_7.setColumns(10);
		
		JButton btnCriptare_3 = new JButton("Encrypt");
		btnCriptare_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
             RSA MyRSA = new RSA(textField_7.getText());
				
				
				JLabel pLabel = new JLabel("P: ");
				JTextArea PText = new JTextArea();
				PText.setText(MyRSA.getp());
				PText.setLineWrap(true);
				panel_4.add(pLabel, "gapbottom 10");
				panel_4.add(PText,"grow, span, center, gapbottom 10");
				
				
				JLabel qLabel = new JLabel("Q: ");
				JTextArea QText = new JTextArea();
				QText.setText(MyRSA.getq());
				QText.setLineWrap(true);
				panel_4.add(qLabel, "gapbottom 10");
				panel_4.add(QText,"grow, span, center, gapbottom 10");
				
				
				JLabel nLabel = new JLabel("N: ");
				JTextArea NText = new JTextArea();
				NText.setText(MyRSA.getn());
				NText.setLineWrap(true);
				panel_4.add(nLabel, "gapbottom 10");
				panel_4.add(NText,"grow, span, center, gapbottom 10");
				
				
				JLabel totLabel = new JLabel("Indicator Euler: ");
				JTextArea totText = new JTextArea();
				totText.setText(MyRSA.getTot());
				totText.setLineWrap(true);
				panel_4.add(totLabel, "gapbottom 10");
				panel_4.add(totText,"grow, span, center, gapbottom 10");
				
				
				JLabel eLabel = new JLabel("E: ");
				JTextArea eText = new JTextArea();
				eText.setText(MyRSA.gete());
				eText.setLineWrap(true);
				panel_4.add(eLabel, "gapbottom 10");
				panel_4.add(eText,"grow, span, center, gapbottom 10");
				
				
				JLabel encryptedIntLabel = new JLabel("BigInt Criptat: ");
				JTextArea encryptedIntText = new JTextArea();
				encryptedIntText.setText(MyRSA.getEncryptedInt());
				encryptedIntText.setLineWrap(true);
				panel_4.add(encryptedIntLabel, "gapbottom 10");
				panel_4.add(encryptedIntText,"grow, span, center, gapbottom 10");
				
				
				JLabel encryptedTextLabel = new JLabel("Text Criptat: ");
				JTextArea encryptedTextText = new JTextArea();
				encryptedTextText.setText(MyRSA.getEncryptedText());
				encryptedTextText.setLineWrap(true);
				panel_4.add(encryptedTextLabel, "gapbottom 10");
				panel_4.add(encryptedTextText,"grow, span, center, gapbottom 10");
				
				
				JLabel decryptedIntLabel = new JLabel("BigInt Decriptat: ");
				JTextArea decryptedIntText = new JTextArea();
				decryptedIntText.setText(MyRSA.getDecryptedInt());
				decryptedIntText.setLineWrap(true);
				panel_4.add(decryptedIntLabel, "gapbottom 10");
				panel_4.add(decryptedIntText,"grow, span, center, gapbottom 10");
				
				
				JLabel decryptedTextLabel = new JLabel("Text Decriptat: ");
				JTextArea decryptedTextText = new JTextArea();
				decryptedTextText.setText(MyRSA.getDecryptedText());
				decryptedTextText.setLineWrap(true);
				panel_4.add(decryptedTextLabel, "gapbottom 10");
				panel_4.add(decryptedTextText,"grow, span, center, gapbottom 10");
				
				
				panel_4.revalidate();
				panel_4.repaint();
			}
		});
		panel_4.add(btnCriptare_3, "cell 0 1");
		JScrollPane pane = new JScrollPane();
		JButton btnCriptare_1 = new JButton("Criptare");
		btnCriptare_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 
		        int i, j = 0;
		        
		        String cipher = "";
		        String key = textField_5.getText().toUpperCase();
		        String plain = textPane_2.getText().toUpperCase();
		        
		        int max = key.length();
		        
		        for(i = 0; i < plain.length(); i++) {
		            
		            char x = plain.charAt(i);
		            
		            if(j >= max ){
		                j = 0;
		            }
		            
		            if(x > 'A' || x < 'Z') {
		                cipher += (char)((x + key.charAt(j)- 2 * 'A') % 26 + 'A');
		            }
		            
		            j++;
		        }
		        
		        textPane_3.setText(cipher);
			}
		});
		
		
		JButton btnEncrypt = new JButton("Encrypt");
		btnEncrypt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  message = textField.getText();

			        try {
			            int shift = Integer.parseInt(textField_1.getText());
			            char array[] = message.toCharArray(); 

			            for (int i = 0; i < textField.getText().length(); i++) {
			                array[i] = (char) (array[i] + shift); 
			            }
			             encryptedMessage = String.valueOf(array); 
			            textPane.setText(encryptedMessage);

			        } catch (Exception e1) {
			            e1.printStackTrace();
			            JOptionPane.showMessageDialog( btnEncrypt, "Introduceti un numar");
			            textField.setText(null);
			            textPane.setText(null);
			            textField_1.setText(null);

			        }
			}
		});
		panel.add(btnEncrypt, "cell 0 2 2 1");
		
		JButton btnNewButton = new JButton("Decrypt");
		btnNewButton.addActionListener(new ActionListener() {
			private String decryptedMessage;

			public void actionPerformed(ActionEvent e) {
				if (textField_1.toString().equals(encryptedMessage)) { 

	            encryptedMessage = textField_1.getText();
	            int shift = Integer.parseInt(textField_1.getText());
	            char array[] = encryptedMessage.toCharArray();

	            try {

	                for (int i = 0; i < textField.getText().length(); i++) {
	                    array[i] = (char) (array[i] - shift);
	                }
	                decryptedMessage = String.valueOf(array);
	                textField_1.setText(decryptedMessage);

	            } catch (Exception e1) {
	            }
	        } else {
	            textPane.setText(message); 
	        }
			}
		});
		panel.add(btnNewButton, "cell 6 2,alignx left,aligny top");
		
		
		
		
		
		
		
	
	}


		
	
	
}

