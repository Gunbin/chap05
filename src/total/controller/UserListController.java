package total.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import total.service.UserListService;

@Controller
public class UserListController {

	@Autowired
	UserListService listService;

	@RequestMapping("/userList")
	public String userListHandle(Model model, @RequestParam(required = false) String fllow) {
		List<Map> list = listService.userList();
		model.addAttribute("list", list);
		if (fllow != null) {
			model.addAttribute("fllow", fllow);
		}
		return "/userSearch";
	}
}
