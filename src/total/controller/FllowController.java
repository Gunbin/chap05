package total.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import total.service.FllowService;

@Controller
public class FllowController {

	@Autowired
	FllowService fllowService;

	@RequestMapping(path = "/fllow", method = RequestMethod.POST)
	public String fllowHandle(@RequestParam String id, HttpSession session, Model model) {
		String one = (String) session.getAttribute("login");
		String other = id;
		boolean b = fllowService.fllow(one, other);
		if (b) {
			return "redirect:/userList?fllow=true";
		} else {
			return "redirect:/userList?fllow=false";
		}
	}
}
