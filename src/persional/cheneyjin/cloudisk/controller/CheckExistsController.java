package persional.cheneyjin.cloudisk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import persional.cheneyjin.cloudisk.db.MS;
import persional.cheneyjin.cloudisk.db.UserInterface;

@Controller
@RequestMapping("/check-exist")
public class CheckExistsController {
	
	private UserInterface userInterface;
	//private FolderInterface folderInterface;
	// warning about user when input login-inf that there email
	// whether correctly.
	@RequestMapping(value="/email",method=RequestMethod.GET)
	public @ResponseBody int emailExistsCheck(@RequestParam("email") String email){
		try(MS ms = new MS()){
			userInterface = ms.getSession().getMapper(UserInterface.class);
			return userInterface.selectUserByEmail(email);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}
	
	
}
