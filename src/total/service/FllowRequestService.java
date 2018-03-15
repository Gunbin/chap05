package total.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FllowRequestService {

	@Autowired
	SqlSessionTemplate template;

	public List<Map> fllowRequest(String id) {
		return template.selectList("friend.selectRequest", id);
	}
}
