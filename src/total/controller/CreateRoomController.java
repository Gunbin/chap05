package total.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import total.service.CreateRoomService;
import total.service.MailService;

@Controller
public class CreateRoomController {

	@Autowired
	CreateRoomService roomService;

	@Autowired
	MailService mailService;

	@RequestMapping("/create")
	public String createHandle(HttpSession session, Model model) {
		String id = (String) session.getAttribute("login");
		Map map = roomService.MemberInfo(id);
		model.addAttribute("map", map);
		if (map.get("LV").equals("0")) {
			boolean b = mailService.sendWelcomMail((String) map.get("EMAIL"));
			return "createRoom";
		} else {
			return "createRoom";
		}
	}
}
