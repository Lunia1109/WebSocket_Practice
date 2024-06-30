package com.practice.websocket.server;

import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.websocket.dto.Employee;
import com.practice.websocket.dto.HotTalk;
import com.practice.websocket.dto.HotTalkContent;
import com.practice.websocket.dto.HotTalkMessage;
import com.practice.websocket.hottalk.service.HotTalkService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class ChattingHandler extends TextWebSocketHandler{

	private Map<Integer, WebSocketSession> employees = new HashMap<>();
	private final HotTalkService service;
	@Autowired
	private ObjectMapper mapper;
	// 입장 시 Employee 추가 Method
	private void addEmployee(WebSocketSession session, int employeeNo) {
		employees.put(employeeNo, session);
	}
	// 채팅방 갯수 변경 시 채팅방 renewal Method
    private void renewalChattingRoom(WebSocketSession session, int employeeNo) {
        try {
            List<HotTalk> chattingRoom = service.selectHotTalkByNo(employeeNo);
            // log.debug("{}",chattingRoom);
            String rooms = mapper.writeValueAsString(chattingRoom);
            session.sendMessage(new TextMessage(rooms));
        } catch (IOException e) {
            log.error("채팅방 갱신 중 오류 발생: {}", e.getMessage(), e);
            try {
                session.sendMessage(new TextMessage("채팅방 정보를 불러오는 데 실패했습니다."));
            } catch (IOException ex) {
                log.error("에러 메시지 전송 실패: {}", ex.getMessage(), ex);
            }
        }
    }

    // 특정 채팅방 기존 내용 가져오는 메소드
    private void getChattingContent(WebSocketSession session, int hotTalkNo, int employeeNo) {
		try {
			List<HotTalkContent> contents = service.selectHotTalkContentByHotTalkNo(hotTalkNo, employeeNo);
			log.debug("contents : {}",contents);
			session.sendMessage(new TextMessage(mapper.writeValueAsString(contents)));
		} catch (IOException e) {
			log.error("채팅방 내용 불러오는데 문제 발생 : {}", e.getMessage());
			try {
				session.sendMessage(new TextMessage("채팅방 내용을 불러오는데 실패했습니다"));
			}catch(IOException ex) {
				log.error("에러 메세지 전송 실패 : {}", ex.getMessage());
			}
		}
	}


	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		// log.debug("{}",enterTime);
		// log.debug("afterConnectionEstablished 메소드 실행");
	}

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        try {
            HotTalkMessage msg = mapper.readValue(message.getPayload(), HotTalkMessage.class);
            switch(msg.getType()) {
                case "open":
                	log.debug("{}",session, msg.getEmployeeNo());
                	addEmployee(session, msg.getEmployeeNo());
                    renewalChattingRoom(session, msg.getEmployeeNo());
                    break;
                case "enter":
                    // log.debug("더블클릭 누름");
                    log.debug("msg : {}", msg);;
                	getChattingContent(session, msg.getHotTalkNo(), msg.getEmployeeNo());
                    break;
                default:
                    log.warn("알 수 없는 메시지 타입: {}", msg.getType());
            }
        } catch (Exception e) {
            log.error("메시지 처리 중 오류 발생: {}", e.getMessage(), e);
        }
    }

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {

	}

	private void sendMessage(HotTalk talk) {
		for(Map.Entry<Integer, WebSocketSession> employee : employees.entrySet()) {
			WebSocketSession eSession = employee.getValue();
			try {

			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

}


