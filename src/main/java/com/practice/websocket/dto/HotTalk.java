package com.practice.websocket.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class HotTalk {
	private String hotTalkIsGroup;
	private int hotTalkNo;
	private String hotTalkTitle;
	private LocalDateTime hotTalkMakedate;
}
