package com.practice.websocket.hottalk.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.practice.websocket.dto.HotTalk;
import com.practice.websocket.hottalk.dao.HotTalkDao;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class HotTalkServiceImpl implements HotTalkService {

	private final HotTalkDao dao;
	private final SqlSession session;
	@Override
	public List<HotTalk> selectHotTalkByNo(int no) {
		return dao.selectHotTalkByNo(session, no);
	}

}
