package total.service;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateMemberService {
	@Autowired
	SqlSessionTemplate template;

	public boolean createMember(Map map) {
		map.put("lv", 0);
		try {
			int r = template.insert("member.insertOne", map);
			if (r == 1) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}
}
