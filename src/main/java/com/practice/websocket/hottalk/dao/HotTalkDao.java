package com.practice.websocket.hottalk.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.practice.websocket.dto.HotTalk;

public interface HotTalkDao {
	List<HotTalk> selectHotTalkByNo(SqlSession session, int no);
}
