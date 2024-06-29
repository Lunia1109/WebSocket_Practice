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
public class HotTalkMessage {
	private String type;
	private int hotTalkNo;
	private int employeeNo;
	private String content;
	private LocalDateTime eventTime;
}
