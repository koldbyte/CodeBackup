package com.koldbyte.codebackup.core.tools;

import javax.swing.JLabel;

public class Logger {
	private Logger m_instance = null;
	public JLabel statusLabel;
	private String status;

	public Logger getInstance() {
		if (m_instance == null) {
			m_instance = new Logger();
		}
		return m_instance;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public synchronized void addStatus(String msg) {
		status += msg + "\n";
		if (statusLabel != null)
			statusLabel.setText(status);
	}
}
