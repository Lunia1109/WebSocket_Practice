package com.practice.websocket.hottalk.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.practice.websocket.dto.HotTalk;
import com.practice.websocket.dto.HotTalkContent;

public interface HotTalkDao {
	List<HotTalk> selectHotTalkByNo(SqlSession session, int employeeNo);
	List<HotTalkContent> selectHotTalkContentByHotTalkNo(SqlSession session, int roomNo, int employeeNo);
}
