package total.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import total.service.LoginService;

@Controller
public class LoginController {

	@Autowired
	LoginService loginService;

	@RequestMapping(path = "/login", method = RequestMethod.GET)
	public String loginGetHandle() {
		return "login";
	}

	@RequestMapping(path = "/login", method = RequestMethod.POST)
	public String loginPostHandle(@RequestParam Map map, Model model, HttpSession session) {
		int r = loginService.login(map);

		if (r == 0) {
			// 아이디 또는 이메일 없음
			model.addAttribute("msg", "해당 아이디 또는 이메일이 없습니다.");
			return "login";
		} else if (r == 1) {
			// 로그인 성공
			session.setAttribute("login", map.get("id"));
			return "redirect:index";
		} else {
			// 비밀번호 불일치
			model.addAttribute("msg", "비밀번호가 일치하지 않습니다.");
			model.addAttribute("id", map.get("id"));
			return "login";
		}
	}
}
