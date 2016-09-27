package com.koldbyte.codebackup.core;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import com.koldbyte.codebackup.core.entities.User;
import com.koldbyte.codebackup.core.tools.MessageConsole;
import com.koldbyte.codebackup.core.tools.PluginWorker;
import com.koldbyte.codebackup.plugins.PluginEnum;
import com.koldbyte.codebackup.plugins.codechef.core.entities.CodechefUser;
import com.koldbyte.codebackup.plugins.codeforces.core.entities.CodeforcesUser;
import com.koldbyte.codebackup.plugins.spoj.core.entities.SpojUser;

public class MainWindow {

	private JFrame frmCodeback;
	private JTextField handleCodechef;
	private JTextField handleCodeforces;
	private JTextField handleSpoj;
	private JPasswordField passSpoj;
	private JTextField proxyName;
	private JTextField proxyPort;
	private JTextField txtDir;
	private ImageIcon progress;
	private Map<String, String> backupSystemSettings;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frmCodeback.setVisible(true);
					
					window.frmCodeback
							.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
				} catch (Exception e) {
					System.err.println("Main: Error starting Application.");
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
		progress = new ImageIcon(this.getClass().getResource("/progress.gif"));

		frmCodeback = new JFrame();
		frmCodeback.setResizable(false);
		frmCodeback.setTitle("CodeBack v3");
		frmCodeback.setBounds(100, 100, 675, 519);
		frmCodeback.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCodeback.getContentPane().setLayout(null);
		frmCodeback.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				int confirmed = JOptionPane.showConfirmDialog(null,
						"Are you sure you want to exit the program?",
						"Confirm Exit", JOptionPane.YES_NO_OPTION);

				if (confirmed == JOptionPane.YES_OPTION) {
					frmCodeback.dispose();
				}
			}
		});
		
		////////////////////////// Codechef options
		
		final JCheckBox chckbxCodechef = new JCheckBox("Codechef");
		chckbxCodechef.setHorizontalAlignment(SwingConstants.LEFT);

		chckbxCodechef.setBounds(10, 7, 218, 23);
		frmCodeback.getContentPane().add(chckbxCodechef);

		final JPanel panelCodechef = new JPanel();
		panelCodechef.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null,
				null));
		panelCodechef.setBounds(10, 36, 248, 41);
		frmCodeback.getContentPane().add(panelCodechef);
		panelCodechef.setLayout(null);
		panelCodechef.setVisible(false);

		JLabel lblHandle = new JLabel("Handle");
		lblHandle.setBounds(10, 11, 74, 14);
		panelCodechef.add(lblHandle);

		handleCodechef = new JTextField();
		handleCodechef.setBounds(94, 8, 145, 20);
		panelCodechef.add(handleCodechef);
		handleCodechef.setColumns(10);
		
		////////////////////////// Codeforces options
		
		final JCheckBox chckbxCodeforces = new JCheckBox("Codeforces");
		chckbxCodeforces.setHorizontalAlignment(SwingConstants.LEFT);

		chckbxCodeforces.setBounds(10, 84, 218, 23);
		frmCodeback.getContentPane().add(chckbxCodeforces);

		final JPanel panelCodeforces = new JPanel();
		panelCodeforces.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null,
				null));
		panelCodeforces.setLayout(null);
		panelCodeforces.setBounds(10, 113, 248, 41);
		frmCodeback.getContentPane().add(panelCodeforces);
		panelCodeforces.setVisible(false);

		JLabel label = new JLabel("Handle");
		label.setBounds(10, 11, 77, 14);
		panelCodeforces.add(label);

		handleCodeforces = new JTextField();
		handleCodeforces.setColumns(10);
		handleCodeforces.setBounds(94, 8, 145, 20);
		panelCodeforces.add(handleCodeforces);
		
		////////////////////////// Spoj options
		
		final JCheckBox chckbxSpoj = new JCheckBox("Spoj");
		chckbxSpoj.setHorizontalAlignment(SwingConstants.LEFT);

		chckbxSpoj.setBounds(10, 161, 218, 23);
		frmCodeback.getContentPane().add(chckbxSpoj);

		final JPanel panelSpoj = new JPanel();
		panelSpoj.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelSpoj.setLayout(null);
		panelSpoj.setBounds(10, 190, 248, 73);
		frmCodeback.getContentPane().add(panelSpoj);
		panelSpoj.setVisible(false);

		JLabel label_1 = new JLabel("Handle");
		label_1.setBounds(10, 11, 74, 14);
		panelSpoj.add(label_1);

		handleSpoj = new JTextField();
		handleSpoj.setColumns(10);
		handleSpoj.setBounds(94, 8, 145, 20);
		panelSpoj.add(handleSpoj);

		JLabel lblPass = new JLabel("Pass");
		lblPass.setBounds(10, 40, 74, 14);
		panelSpoj.add(lblPass);

		passSpoj = new JPasswordField();
		passSpoj.setBounds(94, 39, 145, 20);
		panelSpoj.add(passSpoj);
		passSpoj.setColumns(10);
		
		////////////////////////// Proxy options
		
		//Backup original proxy settings of the system
		backupSystemSettings = new HashMap<String, String>();
		backupSystemSettings.put("http.proxyHost", System.getProperty("http.proxyHost"));
		backupSystemSettings.put("http.proxyPort", System.getProperty("http.proxyPort"));
		backupSystemSettings.put("http.proxySet", System.getProperty("http.proxySet"));
			
		final JCheckBox chckbxProxy = new JCheckBox("Use Proxy");
		chckbxProxy.setHorizontalAlignment(SwingConstants.LEFT);

		chckbxProxy.setBounds(286, 161, 218, 23);
		frmCodeback.getContentPane().add(chckbxProxy);

		final JPanel panelProxy = new JPanel();
		panelProxy.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelProxy.setLayout(null);
		panelProxy.setBounds(296, 190, 248, 73);
		frmCodeback.getContentPane().add(panelProxy);
		panelProxy.setVisible(false);

		JLabel labelName = new JLabel("Proxy");
		labelName.setBounds(10, 11, 74, 14);
		panelProxy.add(labelName);
		
		proxyName = new JTextField();
		proxyName.setColumns(10);
		proxyName.setBounds(94, 8, 145, 20);
		panelProxy.add(proxyName);
		
		JLabel labelPort = new JLabel("Port");
		labelPort.setBounds(10, 40, 74, 14);
		panelProxy.add(labelPort);
		
		proxyPort = new JTextField();
		proxyPort.setColumns(10);
		proxyPort.setBounds(94, 39, 145, 20);
		panelProxy.add(proxyPort);
		
		////////////////////////// Status Panel

		JPanel statusPanel = new JPanel(new BorderLayout());
		statusPanel.setToolTipText("Status");
		statusPanel.setBounds(10, 320, 649, 158);
		frmCodeback.getContentPane().add(statusPanel);

		JTextPane statusLabel = new JTextPane();
		statusLabel.setBackground(Color.DARK_GRAY);
		statusLabel.setForeground(Color.BLACK);
		statusLabel.setFont(new Font("SansSerif", Font.BOLD, 11));
		statusLabel.setBounds(10, 274, 620, 124);
		statusLabel.setEditable(false);
		statusLabel.setSize(620, 124);
		// statusLabel.setLineWrap(true);
		// statusLabel.setRows(6);
		// statusLabel.setColumns(78);
		statusPanel.add(statusLabel);

		JScrollPane scrollPane = new JScrollPane(statusLabel,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		statusPanel.add(scrollPane,
				BorderLayout.CENTER);
		
		
		////////////////////////// Redirect out and Err to status label

		MessageConsole mc = new MessageConsole(statusLabel);
		mc.redirectOut(Color.GREEN, null);
		mc.redirectErr(Color.RED, null);
		// mc.redirectOut(null, System.out);

		////////////////////////// Select Directory 
		JLabel lblDirectory = new JLabel("Directory");
		lblDirectory.setBounds(286, 15, 373, 14);
		frmCodeback.getContentPane().add(lblDirectory);

		txtDir = new JTextField();
		txtDir.setEditable(false);
		txtDir.setColumns(10);
		txtDir.setBounds(286, 37, 324, 20);
		frmCodeback.getContentPane().add(txtDir);

		JButton btnDirectory = new JButton("\u00BB");

		btnDirectory.setBounds(620, 36, 39, 23);
		frmCodeback.getContentPane().add(btnDirectory);

		JButton btnRun = new JButton("Run");

		btnRun.setBounds(385, 275, 111, 23);
		frmCodeback.getContentPane().add(btnRun);

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmCodeback.dispatchEvent(new WindowEvent(frmCodeback,
						WindowEvent.WINDOW_CLOSING));
			}
		});
		btnExit.setBounds(596, 275, 59, 23);
		frmCodeback.getContentPane().add(btnExit);

		URL url = this.getClass().getResource("/progress.gif");
		ImageIcon progressIcon = new ImageIcon(url);

		final JLabel progressCodechef = new JLabel();
		progressCodechef.setBounds(234, 7, 24, 24);
		progressCodechef.setIcon(progressIcon);
		frmCodeback.getContentPane().add(progressCodechef);
		progressCodechef.setVisible(false);

		final JLabel progressCodeforces = new JLabel();
		progressCodeforces.setBounds(234, 83, 24, 24);
		progressCodeforces.setIcon(progressIcon);
		frmCodeback.getContentPane().add(progressCodeforces);
		progressCodeforces.setVisible(false);

		final JLabel progressSpoj = new JLabel();
		progressSpoj.setBounds(234, 160, 24, 24);
		progressSpoj.setIcon(progressIcon);
		frmCodeback.getContentPane().add(progressSpoj);

		final JCheckBox chkOverwrite = new JCheckBox("Overwrite if Code Exist");
		chkOverwrite.setBounds(286, 84, 373, 23);
		frmCodeback.getContentPane().add(chkOverwrite);

		final JCheckBox chkProblem = new JCheckBox(
				"Also Fetch Problem statements");
		chkProblem.setBounds(286, 111, 373, 23);
		frmCodeback.getContentPane().add(chkProblem);
		
		////////////////////////// Show a messageDialog on About click

		JButton btnInfo = new JButton("About");
		btnInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String msg = "CodeBack - Developed by Koldbyte (Bhaskar Divya)\n";
				msg += "CodeBack is a tool to backup all your code submissions on contest sites - Spoj, Codeforces and Codechef.\n\n";
				msg += "GITHUB : https://github.com/koldbyte/CodeBackup\n\n";
				msg += "How to use it?\n\n";
				msg += "1. Enable the checkboxes for which you want to fetch submissions.\n";
				msg += "2. Enter your handle(username) registered on the website.\n";
				msg += "3. Select a directory where you want to save the codes\n";
				msg += "4, Select other options as required.\n";
				msg += "5. Hit Run.\n";
				msg += "\n";
				msg += "CodeBack will save all the Codes and Problem Statement in following directory format :\n";
				msg += "(Select Directory) / (Handle) / (ContestSite) / (PROBLEMNAME) / (PROBLEMNAME)-(SUBMISSIONID).(EXT)\n\n";
				msg += "Feature added by Devansh Dalal( https://github.com/devanshdalal )\n";
				msg += "UPDATE: new proxy feature added. You can download the codes via proxy now as well\n";

				JOptionPane.showMessageDialog(frmCodeback, msg,
						"About CodeBack", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnInfo.setBounds(508, 275, 76, 23);
		frmCodeback.getContentPane().add(btnInfo);

		final JCheckBox chkFetchAllAccepted = new JCheckBox(
				"Fetch All Accepted Submissions");
		chkFetchAllAccepted.setBounds(286, 138, 373, 23);
		frmCodeback.getContentPane().add(chkFetchAllAccepted);
		frmCodeback.getContentPane().setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{chckbxCodechef, handleCodechef, chckbxCodeforces, handleCodeforces, chckbxSpoj, handleSpoj, passSpoj, lblDirectory, txtDir, btnDirectory, chkOverwrite, chkProblem, chkFetchAllAccepted, btnInfo, btnRun, btnExit, statusLabel, panelCodechef, lblHandle, panelCodeforces, label, panelSpoj, label_1, lblPass, statusPanel, scrollPane, statusLabel, progressCodechef, progressCodeforces, progressSpoj}));
		progressSpoj.setVisible(false);

		////////////////////////// Show hide options based on checkboxes
		chckbxCodechef.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				Boolean status = ((JCheckBox) e.getSource()).isSelected();
				panelCodechef.setVisible(status);
			}
		});

		chckbxCodeforces.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				Boolean status = ((JCheckBox) e.getSource()).isSelected();
				panelCodeforces.setVisible(status);
			}
		});

		chckbxSpoj.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				Boolean status = ((JCheckBox) e.getSource()).isSelected();
				panelSpoj.setVisible(status);
			}
		});

		chckbxProxy.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				Boolean status = ((JCheckBox) e.getSource()).isSelected();
				panelProxy.setVisible(status);
			}
		});

		btnDirectory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// choose directory
				JFileChooser fc = new JFileChooser();
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				fc.setAcceptAllFileFilterUsed(false);
				int returnVal = fc.showOpenDialog(frmCodeback);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File yourFolder = fc.getSelectedFile();
					txtDir.setText(yourFolder.getAbsolutePath());
					System.out.println("Selected Directory :- "
							+ yourFolder.toPath());
				}
			}
		});

		btnRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Disable the run button for now
				((JButton) e.getSource()).setEnabled(false);

				// Initialize Appconfig
				AppConfig.setOverWrite(chkOverwrite.isSelected());
				AppConfig.setFetchProblem(chkProblem.isSelected());
				AppConfig.setFetchAllAC(chkFetchAllAccepted.isSelected());
				

				Boolean codechefStatus = chckbxCodechef.isSelected();
				Boolean codeforcesStatus = chckbxCodeforces.isSelected();
				Boolean spojStatus = chckbxSpoj.isSelected();
				
				////////////////////////// Proxy settings
				Boolean proxyStatus = chckbxProxy.isSelected();
				if(proxyStatus){
					//System.setProperty("http.proxySet", "true");
					System.setProperty("http.proxyHost", proxyName.getText() );
					System.setProperty("http.proxyPort", proxyPort.getText() );
				}else{
					//use original settings which were retrieved on the first run 
					//It might be overwritten by the above code in previous runs
					if(backupSystemSettings.get("http.proxySet") != null)
						System.setProperty("http.proxySet", backupSystemSettings.get("http.proxySet"));
					if(backupSystemSettings.get("http.proxyHost") != null)
						System.setProperty("http.proxyHost", backupSystemSettings.get("http.proxyHost") );
					if(backupSystemSettings.get("http.proxyPort") != null)
						System.setProperty("http.proxyPort", backupSystemSettings.get("http.proxyPort") );
				}
				
				String msg = "";
				String succesMsg = "";
				String dir = txtDir.getText();
				if (dir == null || dir.isEmpty()) {
					msg += "Please provide an output Directory.\n";
				} else {
					/*
					 * Check for codechef
					 */
					if (codechefStatus) {
						String codechefHandle = handleCodechef.getText();
						if (codechefHandle == null || codechefHandle.isEmpty()) {
							msg += "Please provide a Codechef handle.\n";
						} else {
							User user = new CodechefUser(codechefHandle);
							if (!user.isValidUser()) {
								msg += "Entered Codechef handle is invalid.\n";
							} else {
								PluginWorker runnable = new PluginWorker(dir,
										user, PluginEnum.CODECHEF,
										progressCodechef);
								progressCodechef.setIcon(progress);
								progressCodechef.setVisible(true);

								runnable.execute();
							}
						}
					}
					/*
					 * Check for Codeforces
					 */
					if (codeforcesStatus) {
						String codeforceHandle = handleCodeforces.getText();
						if (codeforceHandle == null
								|| codeforceHandle.isEmpty()) {
							msg += "Please provide a Codeforces handle.\n";
						} else {
							User user = new CodeforcesUser(codeforceHandle);
							if (!user.isValidUser()) {
								msg += "Entered Codeforces handle is invalid.\n";
							} else {
								PluginWorker runnable = new PluginWorker(dir,
										user, PluginEnum.CODEFORCES,
										progressCodeforces);
								progressCodeforces.setIcon(progress);
								progressCodeforces.setVisible(true);

								runnable.execute();
							}
						}
					}

					/*
					 * Check for Spoj
					 */
					if (spojStatus) {
						String spojHandle = handleSpoj.getText();
						String spojPass = String.valueOf(passSpoj.getPassword());
						if (spojHandle == null || spojHandle.isEmpty()) {
							msg += "Please provide a Spoj handle.\n";
						} else if (spojPass == null || spojPass.isEmpty()) {
							msg += "Please provide password for the Spoj handle.\n";
						} else {
							User user = new SpojUser(spojHandle);
							((SpojUser) user).setUsername(spojHandle);
							((SpojUser) user).setPass(spojPass);
							if (!user.isValidUser()) {
								msg += "Entered Spoj handle is invalid.\n";
							} else {
								PluginWorker runnable = new PluginWorker(dir,
										user, PluginEnum.SPOJ, progressSpoj);
								progressSpoj.setIcon(progress);
								progressSpoj.setVisible(true);

								runnable.execute();
							}
						}
					}
				}
				if (!msg.isEmpty()) {
					JOptionPane.showMessageDialog(frmCodeback, msg, "Error",
							JOptionPane.ERROR_MESSAGE);
				}
				if (!succesMsg.isEmpty()) {
					JOptionPane.showMessageDialog(frmCodeback, succesMsg,
							"Success", JOptionPane.INFORMATION_MESSAGE);
				}

				// Reenable the run button
				((JButton) e.getSource()).setEnabled(true);
			}
		});
	}
}
