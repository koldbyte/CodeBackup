package com.koldbyte.codebackup.core;

import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MainWindow {

	private JFrame frmCodeback;
	private JTextField handleCodechef;
	private JTextField handleCodeforces;
	private JTextField handleSpoj;
	private JTextField passSpoj;
	private JTextField txtDir;

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
		frmCodeback = new JFrame();
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
		lblHandle.setBounds(10, 11, 46, 14);
		panelCodechef.add(lblHandle);

		handleCodechef = new JTextField();
		handleCodechef.setBounds(66, 8, 171, 20);
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
		label.setBounds(10, 11, 46, 14);
		panelCodeforces.add(label);

		handleCodeforces = new JTextField();
		handleCodeforces.setColumns(10);
		handleCodeforces.setBounds(66, 8, 171, 20);
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

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_3.setToolTipText("Status");
		panel_3.setBounds(10, 365, 248, 105);
		frmCodeback.getContentPane().add(panel_3);

		JLabel label_2 = new JLabel("Directory");
		label_2.setBounds(10, 273, 60, 14);
		frmCodeback.getContentPane().add(label_2);

		txtDir = new JTextField();
		txtDir.setEditable(false);
		txtDir.setColumns(10);
		txtDir.setBounds(80, 270, 129, 20);
		frmCodeback.getContentPane().add(txtDir);

		JButton btnDirectory = new JButton("\u00BB");

		btnDirectory.setBounds(219, 269, 39, 23);
		frmCodeback.getContentPane().add(btnDirectory);

		JButton button_1 = new JButton("Run");
		button_1.setBounds(10, 301, 248, 23);
		frmCodeback.getContentPane().add(button_1);

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
	}
}
