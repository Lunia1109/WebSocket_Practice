let chatServer;

 document.querySelector("#hotTalkImg").addEventListener("click", e=>{
	const $hotTalkDiv = document.querySelector("#hotTalkMenu");

	$hotTalkDiv.style.display = "flex";

 })

 // 핫톡 목록 눌렀을 때 실행함수(목록 리스트 open)
 const initializeChatList = () =>{
	const $result = document.querySelector("div.result");
	$result.style.display="flex";	// 리스트 출력해줄 div 보여주기
	renewalChatting();
	// 목록 리스트 출력(Http → WebSocket 업그레이드 후 해당 사번으로 들어가있는 채팅방 가져옴)
 }

 const renewalChatting = () => {
	// WebSocket으로 Upgrade
	chatServer = new WebSocket("ws://localhost:9999/chat");
	// WebSocket으로 Upgrade 되자마자 필요한 정보 Server로 전달 및 함수 실행
	chatServer.onopen = () =>{
		const msg = new HotTalkMessage("open","",2);
		chatServer.send(msg.convert());	// 필요한 정보 : 핫톡 open / 사번 = 2
		// 추후 사번(현재는 2)은 session에 저장되어 있는 정보로 변경 예정
		// 사번 기준으로 가입되어있는 채팅방 목록 가져온 후 div append해서 Listup하는 함수 호출
		openChattingRooms(msg);	// Server에서 전달받은 msg를 매개변수로 전달
	}
	// WebSocket 연결 error 시 실행
	chatServer.onerror = (error) =>{
		console.log("WebSocket 연결 에러 : ",error);
	}
	// WebSocket 닫혔을 때 실행
	chatServer.onclsoe = (e) =>{
		console.log("WebSocket 연결 종료 : ", e);
	}
 }

 const openChattingRooms = (msg) =>{
	chatServer.onmessage = response =>{
		let receiveTalk = HotTalkMessage.deconvert(response.data); // Server에서 전달받은 response를 JSON형식으로 변환
		if(!Array.isArray(receiveTalk)){	// 채팅방이 1개만 있는 사원이거나 없는 사원 예외처리
				receiveTalk = [receiveTalk];	// JSON형식의 데이터를 배열로 변환
		}
		const $result = document.querySelector("div.result");

		if(msg.type=="open"){	// 핫톡 목록 클릭 시 실행
			$result.innerHTML="";
			$result.innerHTML=" <h2>HOT TALK 채팅</h2>";
			// 변환 후 forEach 사용
			console.log(receiveTalk);
		 	receiveTalk.forEach(t=>{
				const $chatRoom = document.createElement("div");
				const $chatTitle = document.createElement("h3");
				$chatRoom.classList.add("chatRoom");
				$chatTitle.innerText=t.hotTalkTitle;
				$chatRoom.append($chatTitle);
				$result.append($chatRoom);
				$chatRoom.addEventListener("dblclick", ()=>{
					const $specRoom = document.querySelector("div.specRoom");
					$specRoom.style.display = "block";
					console.log(t);
					const room = new HotTalkMessage("enter",t.hotTalkTitle, 2, t.hotTalkNo);	// 사원번호 Session에서 가져와서 2대신 기입해야함
					console.log(room);
					openSpecChattingRoom(room.convert());
				})
		  })
		}
 	}
 }
 const openSpecChattingRoom=(data)=>{
	chatServer.send(data);
	chatServer.onmessage = response =>{
		console.log(HotTalkMessage.deconvert(response.data));
	}
 };

 class HotTalkMessage {	// 서버로 전달할 클래스
	constructor(type="",hotTalkTitle="",employeeNo="", hotTalkNo="", eventTime=new Date().toISOString()){
		this.type=type;
		this.hotTalkTitle=hotTalkTitle;
		this.employeeNo=employeeNo;
		this.hotTalkNo=hotTalkNo;
		this.eventTime=eventTime;
	}
	convert(){
		return JSON.stringify(this);
	}
	static deconvert(data){
		return JSON.parse(data);
	}
 }

// Server → HotTalkMessage.deconvert() 하기 위한 클래스 설계
 class HotTalk{	// 서버로 전달받은 HotTalk 방의 정보
	constructor(employeeNo="", hotTalkIsGroup="", hotTalkNo="",hotTalkTitle="", hotTalkMakeDate="", contents="", members="", attachments=""){
		this.employeeNo=employeeNo;
		this.hotTalkIsGroup=hotTalkIsGroup;
		this.hotTalkNo=hotTalkNo;
		this.hotTalkTitle=hotTalkTitle;
		this.hotTalkMakeDate=hotTalkMakeDate;
		this.contents=[];
		this.members=[];
		this.attachments=[];
	}
	inviteReceivers(member) {	// 핫톡 초대 → 수신자들에 추가
		if(member instanceof HotTalkMember){
			this.receivers.push(member);
		} else {
			console.error("employee는 Employee 타입이 아닙니당");
		}
	}
 }

 class HotTalkContent{
	constructor(hotTalkContentNo="", hotTalkNo="", hotTalkContentSender="", hotTalkContent="", hotTalkContentIsread="", hotTalkContentDate="", receivers=""){
		this.hotTalkContentNo=hotTalkContentNo;	// 고유번호
		this.hotTalkNo=hotTalkNo;	// 방번호
		this.hotTalkContentSender=hotTalkContentSender;	// Sender
		this.hotTalkContent=hotTalkContent;	// 채팅내용
		this.hotTalkContentIsread=hotTalkContentIsread;
		this.hotTalkContentDate=hotTalkContentDate;
		this.receivers=[];	// 수신자들
	}

	convert(){
		return JSON.stringify(this);
	}
	static deconvert(data){
		return JSON.parse(data);
	}
 }

 class HotTalkMember{
	constructor(hotTalkNo="", employeeNo="", hotTalkLeader="", hotTalkEnterDate=""){
		this.hotTalkNo=hotTalkNo;
		this.employeeNo=employeeNo;
		this.hotTalkLeader=hotTalkLeader;
		this.hotTalkEnterDate=hotTalkEnterDate;
	}
 }

 class Employee {
	constructor(employeeNo="", employeeName=""){
		this.employeeNo=employeeNo;
		this.employeeName=employeeName;
	}
 }
