package com.koldbyte.codebackup.core.entities;

public enum LanguagesEnum {
	C("c"),CPP("cpp"),JAVA("java"),PYTHON("py"),RUBY("rb");

		private String extension;
		
		LanguagesEnum(String ext){
			setExtension(ext);
		}

		public String getExtension() {
			return extension;
		}

		public void setExtension(String extension) {
			this.extension = extension;
		}
	}

