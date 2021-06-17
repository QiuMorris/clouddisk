package persional.cheneyjin.cloudisk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user-space")
public class UserSpaceController {
	@RequestMapping("")
	public String userSpacePage(){
		return "user-space";
	}
}
