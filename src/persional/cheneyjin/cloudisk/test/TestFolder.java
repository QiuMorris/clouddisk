package persional.cheneyjin.cloudisk.test;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import persional.cheneyjin.cloudisk.db.DBLink;
import persional.cheneyjin.cloudisk.db.FolderInterface;
import persional.cheneyjin.cloudisk.model.Folder;
import persional.cheneyjin.cloudisk.model.UserFile;

public class TestFolder {
	private static SqlSession session = DBLink.getSession().openSession();

	public static void main(String[] args) {
		//testSelectChFiles();
		testSelectChFolders();
	}
	
	private static void testSelectChFiles(){
		FolderInterface folderInterface = session.getMapper(FolderInterface.class);
		List<UserFile> list = folderInterface.selectChFiles(1);
		System.out.print(list.toString());
	}
	
	private static void testSelectChFolders(){
		FolderInterface folderInterface = session.getMapper(FolderInterface.class);
		List<Folder> list = folderInterface.selectChFolders(1);
		System.out.print(list.toString());
	}
}
