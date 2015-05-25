package com.koldbyte.codebackup.core.entities;

public enum LanguagesEnum {
	C("c"), CPP("cpp"), JAVA("java"), PYTHON("py"), RUBY("rb"), TEXT("txt");

	private String extension;

	LanguagesEnum(String ext) {
		setExtension(ext);
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public static LanguagesEnum findExtension(String ext) {
		String e = ext.toLowerCase();
		if (e.contains("c++")) {
			return CPP;
		}
		if (e.contains("java")) {
			return JAVA;
		}
		if (e.contains("python")) {
			return PYTHON;
		}
		if (e.contains("ruby")) {
			return RUBY;
		}

		return TEXT;
	}
}
