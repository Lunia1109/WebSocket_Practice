package com.practice.websocket.hottalk.service;

import java.util.List;

import com.practice.websocket.dto.HotTalk;

public interface HotTalkService {
	List<HotTalk> selectHotTalkByNo(int no);
}
