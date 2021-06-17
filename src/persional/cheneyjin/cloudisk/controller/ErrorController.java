package persional.cheneyjin.cloudisk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ErrorController {

	@RequestMapping("/403")
	public String error403() {
		return "403";
	}

	@RequestMapping("/404")
	public String error404() {
		return "404";
	}

	@RequestMapping("/500")
	public String error500() {
		return "500";
	}

	@RequestMapping("/503")
	public String error503() {
		return "503";
	}
}
