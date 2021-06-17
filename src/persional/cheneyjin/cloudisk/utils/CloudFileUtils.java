package persional.cheneyjin.cloudisk.utils;

public class CloudFileUtils {

	private String fileSizeStr = "";

	public String getFileSizeStr() {
		return fileSizeStr;
	}

	public void setFileSizeStr(String fileSizeStr) {
		this.fileSizeStr = fileSizeStr;
	}

	public void analysiSize(long size) {
		size /= 1024;
		if (size <= 0) {
			setFileSizeStr("小于1KB");
		} else if (size > 0 && size < 1024) {
			setFileSizeStr(toKB(size));
		} else if (size > 1024 && size / 1024 >= 0) {
			setFileSizeStr(toMB(size));
		} else {
			setFileSizeStr(toGB(size));
		}
	}

	private String toKB(long size) {
		return size + "KB";
	}

	private String toMB(long size) {
		return (size / 1024) + "MB";
	}

	private String toGB(long size) {
		return (size / 1024 / 1024) + "GB";
	}

	public void analysisFileName(String fileName) {
		if (!fileName.contains("\\.")) {
			String[] splitStr = fileName.split("\\.");
			setFileType(splitStr[splitStr.length - 1].toString());
		}
		setFileName(fileName);

	}

	public String mosaicFileName(String name, String type) {
		return name + type;
	}

	private String fileName = "";
	private String fileType = "";

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

}
