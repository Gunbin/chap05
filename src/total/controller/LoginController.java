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
			// ���̵� �Ǵ� �̸��� ����
			model.addAttribute("msg", "�ش� ���̵� �Ǵ� �̸����� �����ϴ�.");
			return "login";
		} else if (r == 1) {
			// �α��� ����
			session.setAttribute("login", map.get("id"));
			return "redirect:index";
		} else {
			// ��й�ȣ ����ġ
			model.addAttribute("msg", "��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
			model.addAttribute("id", map.get("id"));
			return "login";
		}
	}
}
