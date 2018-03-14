package total.controller;

import java.util.ArrayList;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import total.service.WebSocketMap;

@Controller("ac")
public class AlertController extends TextWebSocketHandler {

	// 웹 소켓 커넥션이 열릴 때 세션을 키로 해서 묶어서 관리를 하려함
	@Autowired
	WebSocketMap sessions;

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		Map<String, Object> map = session.getAttributes();
		System.out.println("Connection !");
		String sessionId = (String) map.get("HTTP.SESSION.ID");

		if (!sessions.containsKey(sessionId)) {
			sessions.put(sessionId, new ArrayList<WebSocketSession>());
		}
		sessions.get(sessionId).add(session);
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		System.out.println("Closed!");
		String sessionId = (String) session.getAttributes().get("HTTP.SESSION.ID");
		sessions.get(sessionId).remove(session);
		if (sessions.get(sessionId).isEmpty()) {
			sessions.remove(sessionId);
		}
	}
}
