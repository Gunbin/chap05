package total.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Controller("ac")
public class AlertController extends TextWebSocketHandler {

	// �� ���� Ŀ�ؼ��� ���� �� ������ Ű�� �ؼ� ��� ������ �Ϸ���
	Map<String, List<WebSocketSession>> sessions;

	@PostConstruct
	public void init() {
		sessions = new HashMap<>();
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		Map<String, Object> map = session.getAttributes();
		System.out.println("AlertController Connected ! ");
		String sessionId = (String) map.get("HTTP.SESSION.ID");

		if (!sessions.containsKey(sessionId)) {
			sessions.put(sessionId, new ArrayList<WebSocketSession>());
		}
		sessions.get(sessionId).add(session);

		for (String key : sessions.keySet()) {
			System.out.println(key + "�� ��ϵ� WebSocketSession");
			ArrayList<WebSocketSession> list = (ArrayList) sessions.get(key);
			for (WebSocketSession ws : list) {
				System.out.println("=> " + ws.toString());
			}
		}

	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		String sessionId = (String) session.getAttributes().get("HTTP.SESSION.ID");
		sessions.get(sessionId).remove(session);
		if (sessions.get(sessionId).isEmpty()) {
			sessions.remove(sessionId);
		}
	}
}
