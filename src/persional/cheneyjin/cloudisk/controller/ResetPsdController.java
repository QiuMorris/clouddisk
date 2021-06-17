package persional.cheneyjin.cloudisk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import persional.cheneyjin.cloudisk.db.MS;
import persional.cheneyjin.cloudisk.db.UserInterface;
import persional.cheneyjin.cloudisk.utils.SendEmail;

@Controller
@RequestMapping("/reset-password")
public class ResetPsdController {
	private UserInterface userInterface;

	@RequestMapping("")
	public String resetPsdPage() {
		return "reset-password";
	}

	@RequestMapping(value = "/send-email", method = RequestMethod.POST)
	public String sendResetEmail(@RequestParam("email") String email) {
		if (email.equals("")) {
			return "reset-password";
		}
		SendEmail sendEmail = new SendEmail();
		sendEmail.send(email);
		return "email-send-ok";
	}

	@RequestMapping(value = "/do", method = RequestMethod.POST)
	public String resetPsd(@RequestParam("email") String email, @RequestParam("newPsd") String newPsd, 
			@RequestParam("checkCode") String checkCode) {
		// TODO check code
		try (MS ms = new MS()) {
			userInterface = ms.getSession().getMapper(UserInterface.class);
			if (userInterface.resetPsd(email, newPsd) == 1) {
				return "reset-psd-ok";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "500";
	}
}
