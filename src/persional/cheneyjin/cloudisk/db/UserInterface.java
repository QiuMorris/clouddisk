package persional.cheneyjin.cloudisk.db;

import persional.cheneyjin.cloudisk.model.User;

public interface UserInterface {
	public int selectUserByEmail(String email);
	
	public User checkLogin(String email,String psd);
	
	public int regist(User user);
	
	public int resetPsd(String email,String newPsd);
	
}


