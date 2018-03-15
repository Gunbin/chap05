package total.service;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

	@Autowired
	SqlSessionTemplate template;

	public int login(Map map) {
		Map m = template.selectOne("member.selectOne", map);
		if (m == null) {
			// 아이디 또는 없음
			return 0;
		} else if (m.get("PASSWORD").equals(map.get("pass"))) {
			// 로그인성공
			return 1;
		} else {
			// 비밀번호 불일치
			return 2;
		}
	}

	public String loginId(Map map) {
		Map m = template.selectOne("member.selectOne", map);
		return (String) m.get("ID");
	}
}
