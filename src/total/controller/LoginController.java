package total.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import total.service.LoginService;
import total.service.WebSocketMap;

@Controller
public class LoginController {

	@Autowired
	LoginService loginService;

	@Autowired
	WebSocketMap sessions;

	@RequestMapping(path = "/login", method = RequestMethod.GET)
	public String loginGetHandle() {
		return "login";
	}

	@RequestMapping(path = "/login", method = RequestMethod.POST)
	public String loginPostHandle(@RequestParam Map map, Model model, HttpSession session) throws IOException {
		int r = loginService.login(map);

		if (r == 0) {
			// 아이디 또는 이메일 없음
			model.addAttribute("msg", "해당 아이디 또는 이메일이 없습니다.");
			return "login";
		} else if (r == 1) {
			// 로그인 성공
			session.setAttribute("login", loginService.loginId(map));
			List<WebSocketSession> list = sessions.get(session.getId());
			for (WebSocketSession ws : list) {
				ws.sendMessage(new TextMessage("login"));
			}
			return "redirect:index?login=true";
		} else {
			// 비밀번호 불일치
			model.addAttribute("msg", "비밀번호가 일치하지 않습니다.");
			model.addAttribute("id", map.get("id"));
			return "login";
		}
	}
}
