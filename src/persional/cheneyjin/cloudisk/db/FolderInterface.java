package persional.cheneyjin.cloudisk.db;

import java.util.List;

import persional.cheneyjin.cloudisk.model.Folder;
import persional.cheneyjin.cloudisk.model.UserFile;

public interface FolderInterface {
	
	public int initFolder(Folder folder);
		
	public int createFolder(Folder folder);
	
	public Folder selectFolderById(int folder_id);
	
	public int setFolderOwn(int user_id,int folder_id);

	public List<UserFile> selectChFiles(int folder_id);
	
	public List<Folder> selectChFolders(int folder_id);
	
	//public int copyFolder(int folder_id);
	
	public int deleteFolder(int folder_id);
	
	public int recoveryFolder(int user_id,int folder_id);
	
}
