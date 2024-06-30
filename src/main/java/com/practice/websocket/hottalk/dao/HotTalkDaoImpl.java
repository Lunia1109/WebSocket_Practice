package com.practice.websocket.hottalk.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.practice.websocket.dto.HotTalk;
import com.practice.websocket.dto.HotTalkContent;

@Repository
public class HotTalkDaoImpl implements HotTalkDao {

	@Override
	public List<HotTalk> selectHotTalkByNo(SqlSession session, int employeeNo) {
		return session.selectList("chatting.selectHotTalkByEmployeeNo", employeeNo);
	}

	@Override
	public List<HotTalkContent> selectHotTalkContentByHotTalkNo(SqlSession session, int hotTalkNo, int employeeNo) {
		Map<String, Integer> param = new HashMap<>();
		param.put("hotTalkNo", hotTalkNo);
		param.put("employeeNo", employeeNo);
		return session.selectList("chatting.selectHotTalkContentByHotTalkNo", param);
	}

}
