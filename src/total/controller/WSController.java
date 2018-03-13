package total.controller;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.google.gson.Gson;

/*
 * WebSocket����� ������ �뵵�� ��Ʈ�ѷ�
 * 1. WebcketHandle(i)�� implements�ɾ ������ �����ؼ� ���
 * 2. ������ �´� WebSocketHandler�� extneds�ɾ ��� 
 * - TextWebSocketHandler (����) / binaryWebSocketHandler (����)
 * 
 * WebSocket Handler�� ������ spring���� ���Ͽ��� �� �� �ִ�.
 */
@Controller
public class WSController extends TextWebSocketHandler {

	Set<WebSocketSession> wsSession;

	@Autowired
	Gson gson;

	@PostConstruct // init-method
	public void init() {
		wsSession = new LinkedHashSet<>();
	}

	// (Ŭ���̾�Ʈ��)���� �Ǿ��� ��
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.println("afterConnectionEstablished!" + session);
		// Set�� Ŭ���̾�Ʈ�ϳ� �߰�
		wsSession.add(session);
		Map map = new HashMap<>();
		map.put("cnt", wsSession.size());
		map.put("info", session.getRemoteAddress().getAddress().getHostAddress() + "�� �����߽��ϴ�.");
		for (WebSocketSession ws : wsSession) {
			ws.sendMessage(new TextMessage(gson.toJson(map)));
		}

	}

	// (Ŭ���̾�Ʈ��)�޽����� ���� ��
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		System.out.println("handleTextMessage!" + session + " / " + message);
	}

	// (Ŭ���̾�Ʈ��)���� ���� ��
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		System.out.println("afterConnectionClosed!" + session + " / " + status);
		wsSession.remove(session);
		Map map = new HashMap<>();
		map.put("cnt", wsSession.size());
		map.put("info", session.getRemoteAddress().getAddress().getHostAddress() + "�� �α׾ƿ� �߽��ϴ�.");
		for (WebSocketSession ws : wsSession) {
			ws.sendMessage(new TextMessage(gson.toJson(map)));
		}
	}
}
