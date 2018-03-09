package total.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import total.service.CreateMemberService;

@Controller
public class JoinController {

	@Autowired
	CreateMemberService createMemberService;

	@RequestMapping(path = "/join", method = RequestMethod.GET)
	public String joinGetHandle() {
		return "join";
	}

	@RequestMapping(path = "/join", method = RequestMethod.POST)
	public String joinPostHandle(@RequestParam Map map, HttpSession session, Model model) {
		boolean r = createMemberService.createMember(map);
		if (r) {
			session.setAttribute("login", map.get("id"));
			return "redirect:/index";
		} else {
			model.addAttribute("id", map.get("id"));
			model.addAttribute("email", map.get("email"));
			return "join";
		}
	}

	@RequestMapping("/out")
	public String outHandle(HttpSession session) {
		session.removeAttribute("login");
		return "redirect:/index";
	}
}
