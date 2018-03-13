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
 * WebSocket통신을 제어할 용도의 컨트롤러
 * 1. WebcketHandle(i)를 implements걸어서 목적에 개조해서 사용
 * 2. 목적에 맞는 WebSocketHandler를 extneds걸어서 사용 
 * - TextWebSocketHandler (문자) / binaryWebSocketHandler (파일)
 * 
 * WebSocket Handler의 매핑은 spring설정 파일에서 할 수 있다.
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

	// (클라이언트와)연결 되었을 때
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.println("afterConnectionEstablished!" + session);
		// Set에 클라이언트하나 추가
		wsSession.add(session);
		Map map = new HashMap<>();
		map.put("cnt", wsSession.size());
		map.put("info", session.getRemoteAddress().getAddress().getHostAddress() + "가 접속했습니다.");
		for (WebSocketSession ws : wsSession) {
			ws.sendMessage(new TextMessage(gson.toJson(map)));
		}

	}

	// (클라이언트와)메시지를 보낼 떄
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		System.out.println("handleTextMessage!" + session + " / " + message);
	}

	// (클라이언트와)연결 해제 시
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		System.out.println("afterConnectionClosed!" + session + " / " + status);
		wsSession.remove(session);
		Map map = new HashMap<>();
		map.put("cnt", wsSession.size());
		map.put("info", session.getRemoteAddress().getAddress().getHostAddress() + "가 로그아웃 했습니다.");
		for (WebSocketSession ws : wsSession) {
			ws.sendMessage(new TextMessage(gson.toJson(map)));
		}
	}
}
