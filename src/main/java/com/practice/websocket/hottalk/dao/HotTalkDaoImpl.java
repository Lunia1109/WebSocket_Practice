package com.practice.websocket.hottalk.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.practice.websocket.dto.HotTalk;

@Repository
public class HotTalkDaoImpl implements HotTalkDao {

	@Override
	public List<HotTalk> selectHotTalkByNo(SqlSession session, int employeeNo) {
		return session.selectList("chatting.selectHotTalkByEmployeeNo", employeeNo);
	}

}
