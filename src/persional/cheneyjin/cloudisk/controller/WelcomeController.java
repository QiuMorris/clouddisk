package persional.cheneyjin.cloudisk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class WelcomeController {
	@RequestMapping("")
	public String defaultPage() {
		return "sign-in";
	}

	@RequestMapping("/index")
	public String index() {
		return "sign-in";
	}
}
