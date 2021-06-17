package persional.cheneyjin.cloudisk.test;

import org.apache.ibatis.session.SqlSession;

import persional.cheneyjin.cloudisk.db.DBLink;
import persional.cheneyjin.cloudisk.db.FolderInterface;
import persional.cheneyjin.cloudisk.db.UserInterface;
import persional.cheneyjin.cloudisk.model.Folder;
import persional.cheneyjin.cloudisk.model.User;

public class TestUser {
	private static SqlSession session = DBLink.getSession().openSession();

	public static void main(String[] args) throws Exception {
		// testCheckLogin();
		//testRegist();
		//testRestPsd();
		//testSelectUserByEmail();
	}

	// checklogin
	private static void testCheckLogin() {
		try {
			UserInterface userInterface = session.getMapper(UserInterface.class);
			User user = userInterface.checkLogin("tom@live.com", "123456");
			System.out.println(user.getName());
		} finally {
			session.close();
		}
	}

	private static void testinitFolder() {
		try {
			FolderInterface folderInterface = session.getMapper(FolderInterface.class);
			Folder folder = new Folder();
			int insertState = folderInterface.initFolder(folder);
			System.out.println(folder.getId());
			if (insertState == 1)
				session.commit();

		} finally {
			session.close();
		}
	}
	
/*	private static void testRegist() throws Exception{
		UserService userService = new UserService();
		User user = new User();
		user.setName("Mas");
		user.setEmail("mas@live.com");
		user.setPsd("123456");
		user.setRole_id(2);
		user.setCreatime("2016-01-01");
		userService.regist(user);
	}
	
	private static void testRestPsd(){
		UserInterface userInterface = session.getMapper(UserInterface.class);
		System.out.print(userInterface.resetPsd("tom@live.com", "654321"));
		session.commit();
		UserService userService = new UserService();
		userService.resetPsd("tom@live.com", "123456");
	}
	
	private static void testSelectUserByEmail(){
		UserInterface userInterface = session.getMapper(UserInterface.class);
		System.out.print(userInterface.selectUserByEmail("tom@live.com").getName());
		
	}*/
}