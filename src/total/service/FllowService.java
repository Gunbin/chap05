package total.service;

import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FllowService {

	@Autowired
	SqlSessionTemplate template;

	public boolean fllow(String one, String other) {
		Map map = new HashMap<>();
		map.put("one", one);
		map.put("other", other);
		Map m = template.selectOne("friend.selectOne", map);
		int r;
		map.put("status", 0);
		if (m == null) {
			r = template.insert("friend.insertOne", map);
		} else {
			r = 0;
		}
		return r == 1;
	}
}
