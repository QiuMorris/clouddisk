package persional.cheneyjin.cloudisk.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import persional.cheneyjin.cloudisk.db.MS;
import persional.cheneyjin.cloudisk.db.UserInterface;
import persional.cheneyjin.cloudisk.model.User;
import persional.cheneyjin.cloudisk.utils.MD5;

@Controller
@RequestMapping("/login")
public class LoginController {
	private UserInterface userInterface;

	// logining check email and psd.
	@RequestMapping(value = "/check", method = RequestMethod.POST)
	public String loginCheck(@RequestParam("email") String email, @RequestParam("psd") String psd, HttpSession session) {
		try (MS ms = new MS()) {
			userInterface = ms.getSession().getMapper(UserInterface.class);
			User user = userInterface.checkLogin(email, MD5.getString(psd));
			if (user!=null) {
				session.setAttribute("USER_ID", user.getId());
				session.setAttribute("USER_NAME", user.getName());
				// 初始化用户当前目录为根目录
				session.setAttribute("DIRECT", user.getRoot_id());
				return "user-space";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.setAttribute("LOGIN_ERROR", "请检查账号密码！");
		return "sign-in";
	}

	@RequestMapping("/out")
	public String loginOut(HttpSession session) {
		session.invalidate();
		return "sign-in";
	}

	// future function： check-img ...
}
