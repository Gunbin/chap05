package total.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import total.service.FllowRequestService;
import total.service.GreetService;

@Controller
public class IndexController {

	@Autowired
	GreetService greetService;
	@Autowired
	FllowRequestService requestService;

	@RequestMapping("/index")
	public String IndexHandle(Model model, @RequestParam(required = false) String login, HttpSession session) {
		model.addAttribute("ment", greetService.make());
		if (login != null) {
			String id = (String) session.getAttribute("login");
			List list = requestService.fllowRequest(id);
			if (list == null) {
				session.setAttribute("fllowNum", 0);
			} else {
				session.setAttribute("fllowNum", list.size());
			}
			model.addAttribute("logon", true);
		}
		return "index";
	}
}
