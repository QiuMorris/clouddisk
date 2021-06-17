package persional.cheneyjin.cloudisk.db;

import java.util.List;

import persional.cheneyjin.cloudisk.model.FileInCloud;

public interface FileInCloudInterface {
	public FileInCloud getFile(int file_Id);

	public int setFile(FileInCloud file);
	
	public List<FileInCloud> selectFileMD5(String md5);
}
