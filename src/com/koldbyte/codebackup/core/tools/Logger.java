package com.koldbyte.codebackup.core.tools;

public class Logger {
	
	private Logger m_instance = null;

	private Logger() {
		super();
	}

	public Logger getInstance() {
		if (m_instance == null) {
			m_instance = new Logger();
		}
		return m_instance;
	}
	
	public void LogPlugin(String msg){
		
	}

}
