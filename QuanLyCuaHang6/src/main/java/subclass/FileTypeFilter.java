package subclass;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class FileTypeFilter extends FileFilter {
	private final String extension;
	private final String description;

	public FileTypeFilter(String extension, String description) { // Nhận vào ".jpg", "JPG" chẳng hạn
		this.extension = extension;
		this.description = description;
	}

	@Override
	public boolean accept(File file) {
		if (file.getName().endsWith(extension)) {
			return true;
		}
		return false;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return description;
	}
}
