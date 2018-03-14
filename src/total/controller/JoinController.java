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

import total.service.CreateMemberService;
import total.service.WebSocketMap;

@Controller
public class JoinController {

	@Autowired
	CreateMemberService createMemberService;

	@Autowired
	WebSocketMap sessions;

	@RequestMapping(path = "/join", method = RequestMethod.GET)
	public String joinGetHandle() {
		return "join";
	}

	@RequestMapping(path = "/join", method = RequestMethod.POST)
	public String joinPostHandle(@RequestParam Map map, HttpSession session, Model model) throws IOException {
		boolean r = createMemberService.createMember(map);
		// 로그인성공
		if (r) {
			session.setAttribute("login", map.get("id"));
			List<WebSocketSession> list = sessions.get(session.getId());
			for (WebSocketSession ws : list) {
				ws.sendMessage(new TextMessage("login"));
			}
			return "redirect:/index";
		} else { // 로그인 실패
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
