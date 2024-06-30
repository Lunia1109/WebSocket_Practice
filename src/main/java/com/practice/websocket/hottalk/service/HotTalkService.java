package com.practice.websocket.hottalk.service;

import java.util.List;

import com.practice.websocket.dto.HotTalk;
import com.practice.websocket.dto.HotTalkContent;

public interface HotTalkService {
	List<HotTalk> selectHotTalkByNo(int emnployeeNo);
	List<HotTalkContent> selectHotTalkContentByHotTalkNo(int roomNo, int employeeNo);
}
