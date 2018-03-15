package total.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import total.service.FllowRequestService;

@Controller
public class RequestListController {

	@Autowired
	FllowRequestService requestService;

	@RequestMapping("requestList")
	public String requestList(HttpSession session, Model model) {
		List list = requestService.fllowRequest((String) session.getAttribute("login"));
		model.addAttribute("list", list);
		return "requestList";
	}
}
