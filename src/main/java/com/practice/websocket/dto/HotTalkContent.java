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
public class HotTalkContent {
	private int hotTalkContentNo;
	private int hotTalkNo;
	private int employeeNo;
	private String hotTalkContent;
	private LocalDateTime hotTalkContentDate;
}
