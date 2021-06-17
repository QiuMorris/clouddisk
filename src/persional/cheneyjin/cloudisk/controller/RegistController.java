package persional.cheneyjin.cloudisk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import persional.cheneyjin.cloudisk.db.FolderInterface;
import persional.cheneyjin.cloudisk.db.MS;
import persional.cheneyjin.cloudisk.db.UserInterface;
import persional.cheneyjin.cloudisk.interceptor.Token;
import persional.cheneyjin.cloudisk.model.Folder;
import persional.cheneyjin.cloudisk.model.User;

@Controller
@RequestMapping("/regist")
public class RegistController {

	private UserInterface userInterface;
	private FolderInterface folderInterface;
	
	@RequestMapping("")
	@Token(save=true)
	public String registPage() {
		return "sign-up";
	}
	
	@Token(remove=true)
	@RequestMapping(value = "/do", method = RequestMethod.POST)
	public String doRegist(@ModelAttribute("user") User user, Model model) {
		try (MS ms = new MS()) {
			folderInterface = ms.getSession().getMapper(FolderInterface.class);
			Folder folder = new Folder();
			if (folderInterface.initFolder(folder) != 1) {
				ms.getSession().rollback();
				return "500";
			}				
			ms.getSession().commit();
			folder.setFolderName("root");
			user.setRoot_id(folder.getId());
			userInterface = ms.getSession().getMapper(UserInterface.class);
			if (userInterface.regist(user) != 1) {
				ms.getSession().rollback();
				return "500";
			}
			ms.getSession().commit();
			if (folderInterface.setFolderOwn(user.getId(), folder.getId()) != 1) {
				ms.getSession().rollback();
				return "500";
			}
			ms.getSession().commit();

			return "sign-in";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "500";
	}

	// future function： check-img ...
}
