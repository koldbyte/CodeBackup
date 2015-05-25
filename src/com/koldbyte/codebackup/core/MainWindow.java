package com.koldbyte.codebackup.core;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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
	private JTextField passSpoj;
	private JTextField txtDir;
	private ImageIcon progress;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frmCodeback.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
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
	 * Converts a given Image into a BufferedImage
	 *
	 * @param img
	 *            The Image to be converted
	 * @return The converted BufferedImage
	 */
	public static BufferedImage toBufferedImage(Image img) {
		if (img instanceof BufferedImage) {
			return (BufferedImage) img;
		}

		// Create a buffered image with transparency
		BufferedImage bimage = new BufferedImage(img.getWidth(null),
				img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

		// Draw the image on to the buffered image
		Graphics2D bGr = bimage.createGraphics();
		bGr.drawImage(img, 0, 0, null);
		bGr.dispose();

		// Return the buffered image
		return bimage;
	}

	public static BufferedImage resize(BufferedImage image, int width,
			int height) {
		BufferedImage bi = new BufferedImage(width, height,
				BufferedImage.TRANSLUCENT);
		Graphics2D g2d = (Graphics2D) bi.createGraphics();
		g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY));
		g2d.drawImage(image, 0, 0, width, height, null);
		g2d.dispose();
		return bi;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		progress = new ImageIcon(this.getClass().getResource("/progress.gif"));

		frmCodeback = new JFrame();
		frmCodeback.setType(Type.UTILITY);
		frmCodeback.setResizable(false);
		frmCodeback.setTitle("CodeBack");
		frmCodeback.setBounds(100, 100, 284, 519);
		frmCodeback.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCodeback.getContentPane().setLayout(null);
		frmCodeback.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				int confirmed = JOptionPane.showConfirmDialog(null,
						"Are you sure you want to exit the program?",
						"Exit Program Message Box", JOptionPane.YES_NO_OPTION);

				if (confirmed == JOptionPane.YES_OPTION) {
					frmCodeback.dispose();
				}
			}
		});
		JCheckBox chckbxCodechef = new JCheckBox("Codechef");
		chckbxCodechef.setHorizontalAlignment(SwingConstants.LEFT);

		chckbxCodechef.setBounds(10, 7, 213, 23);
		frmCodeback.getContentPane().add(chckbxCodechef);

		JPanel panelCodechef = new JPanel();
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
		handleCodechef.setBounds(102, 8, 135, 20);
		panelCodechef.add(handleCodechef);
		handleCodechef.setColumns(10);

		JCheckBox chckbxCodeforces = new JCheckBox("Codeforces");
		chckbxCodeforces.setHorizontalAlignment(SwingConstants.LEFT);

		chckbxCodeforces.setBounds(10, 84, 213, 23);
		frmCodeback.getContentPane().add(chckbxCodeforces);

		JPanel panelCodeforces = new JPanel();
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
		handleCodeforces.setBounds(107, 8, 135, 20);
		panelCodeforces.add(handleCodeforces);

		JCheckBox chckbxSpoj = new JCheckBox("Spoj");
		chckbxSpoj.setHorizontalAlignment(SwingConstants.LEFT);

		chckbxSpoj.setBounds(10, 161, 213, 23);
		frmCodeback.getContentPane().add(chckbxSpoj);

		JPanel panelSpoj = new JPanel();
		panelSpoj.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelSpoj.setLayout(null);
		panelSpoj.setBounds(10, 190, 248, 73);
		frmCodeback.getContentPane().add(panelSpoj);
		panelSpoj.setVisible(false);

		JLabel label_1 = new JLabel("Handle");
		label_1.setBounds(10, 11, 46, 14);
		panelSpoj.add(label_1);

		handleSpoj = new JTextField();
		handleSpoj.setColumns(10);
		handleSpoj.setBounds(66, 8, 171, 20);
		panelSpoj.add(handleSpoj);

		JLabel lblPass = new JLabel("Pass");
		lblPass.setBounds(10, 40, 46, 14);
		panelSpoj.add(lblPass);

		passSpoj = new JTextField();
		passSpoj.setBounds(66, 39, 171, 20);
		panelSpoj.add(passSpoj);
		passSpoj.setColumns(10);

		JPanel statusPanel = new JPanel();
		statusPanel.setToolTipText("Status");
		statusPanel.setBounds(10, 365, 248, 105);
		frmCodeback.getContentPane().add(statusPanel);

		final JTextArea statusLabel = new JTextArea();
		statusLabel.setLineWrap(true);
		statusLabel.setRows(6);
		statusLabel.setColumns(28);
		statusPanel.add(statusLabel);

		statusPanel.add(new JScrollPane(statusLabel,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));

		MessageConsole mc = new MessageConsole(statusLabel);
		mc.redirectOut();
		mc.redirectErr(Color.RED, null);
		// mc.redirectOut(null, System.out);

		/*
		 * //Attach the status Label with the logStatus Singleton new
		 * Logger().getInstance().statusLabel = statusLabel;
		 */

		JLabel lblDirectory = new JLabel("Directory");
		lblDirectory.setBounds(10, 273, 60, 14);
		frmCodeback.getContentPane().add(lblDirectory);

		txtDir = new JTextField();
		txtDir.setEditable(false);
		txtDir.setColumns(10);
		txtDir.setBounds(80, 270, 129, 20);
		frmCodeback.getContentPane().add(txtDir);

		JButton btnDirectory = new JButton("\u00BB");

		btnDirectory.setBounds(219, 269, 39, 23);
		frmCodeback.getContentPane().add(btnDirectory);

		JButton btnRun = new JButton("Run");

		btnRun.setBounds(10, 301, 248, 23);
		frmCodeback.getContentPane().add(btnRun);

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmCodeback.dispatchEvent(new WindowEvent(frmCodeback,
						WindowEvent.WINDOW_CLOSING));
			}
		});
		btnExit.setBounds(10, 331, 248, 23);
		frmCodeback.getContentPane().add(btnExit);

		URL url = this.getClass().getResource("/progress.gif");
		ImageIcon progressIcon = new ImageIcon(url);

		JLabel progressCodechef = new JLabel();
		progressCodechef.setBounds(234, 7, 24, 24);
		progressCodechef.setIcon(progressIcon);
		frmCodeback.getContentPane().add(progressCodechef);
		progressCodechef.setVisible(false);

		JLabel progressCodeforces = new JLabel();
		progressCodeforces.setBounds(234, 83, 24, 24);
		progressCodeforces.setIcon(progressIcon);
		frmCodeback.getContentPane().add(progressCodeforces);
		progressCodeforces.setVisible(false);

		JLabel progressSpoj = new JLabel();
		progressSpoj.setBounds(234, 160, 24, 24);
		progressSpoj.setIcon(progressIcon);
		frmCodeback.getContentPane().add(progressSpoj);
		progressSpoj.setVisible(false);

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
				Boolean codechefStatus = chckbxCodechef.isSelected();
				Boolean codeforcesStatus = chckbxCodeforces.isSelected();
				Boolean spojStatus = chckbxSpoj.isSelected();
				String msg = "";
				String succesMsg = "";
				String dir = txtDir.getText();
				if (dir == null || dir.isEmpty()) {
					msg += "Please provide a output Directory.\n";
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
								msg += "Provided Codechef handle is invalid.\n";
							} else {
								PluginWorker runnable = new PluginWorker(dir,
										user, PluginEnum.CODECHEF,
										progressCodechef);
								progressCodechef.setIcon(progress);
								progressCodechef.setVisible(true);
								// Run in the new Thread
								// TODO:
								runnable.execute();
								// runnable.run();
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
								msg += "Provided Codeforces handle is invalid.\n";
							} else {
								PluginWorker runnable = new PluginWorker(dir,
										user, PluginEnum.CODEFORCES,
										progressCodeforces);
								progressCodeforces.setIcon(progress);
								progressCodeforces.setVisible(true);
								// Run in the new Thread
								// TODO:
								runnable.execute();
								// runnable.run();
							}
						}
					}

					/*
					 * Check for Spoj
					 */
					if (spojStatus) {
						String spojHandle = handleCodeforces.getText();
						String spojPass = passSpoj.getText();
						if (spojHandle == null || spojHandle.isEmpty()) {
							msg += "Please provide a Spoj handle.\n";
						} else if (spojPass == null || spojPass.isEmpty()) {
							msg += "Please provide pass for the Spoj handle.\n";
						} else {
							User user = new SpojUser(spojHandle);
							((SpojUser) user).setPass(spojPass);
							if (!user.isValidUser()) {
								msg += "Provided Codeforces handle is invalid.\n";
							} else {
								PluginWorker runnable = new PluginWorker(dir,
										user, PluginEnum.SPOJ, progressSpoj);
								progressSpoj.setIcon(progress);
								progressSpoj.setVisible(true);
								// Run in the new Thread
								// TODO:
								runnable.execute();
								// runnable.run();
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
