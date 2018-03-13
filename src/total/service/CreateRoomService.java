package total.service;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateRoomService {

	@Autowired
	SqlSessionTemplate template;

	public Map MemberInfo(String id) {
		Map map = template.selectOne("member.selectOne", id);
		return map;
	}
}
