package persional.cheneyjin.cloudisk.db;

import java.util.List;

import persional.cheneyjin.cloudisk.model.UserFile;

public interface UserFileInterface {	
	public UserFile fileInfo(int file_id);
	
	public int uploadFile(UserFile userFile);
	
	public int deleteFile(int userFile_id);
	
	public int shareFile(int file_id);
	
	public List<UserFile> selectFilesInShare(int user_id);
	
	public int cancelShareFile(int file_id);
}
