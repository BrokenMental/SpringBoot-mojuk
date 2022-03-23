package inhatc.group.mojuk.msg;

import inhatc.group.mojuk.model.ChatMessage;
import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor //생성자 주입을 통해 Autowired를 일일히 입력해주지 않아도 다른 class 사용 가능
public class ChatController {
	
	ChatService chatService;

	//바로 Mapper interface로 넘겨도 됨
	//@Autowired
	//ChatMapper chatMapper;
	
	/* 
	 * @MessageMapping으로 메시지를 수신 할 경우,
	 * @SendTo를 통해 해당 토픽을 구독하는 클라이언트에게 메시지를 보낼 수 있음
	 * @Payload : 전송되는 데이터 자체를 의미, 여기서는 script에서 보내 온 객체를 ChatMessage Class에 매핑
	 * 단일 메시지만 받는다면 @payload 어노테이션 없이 String으로 매개변수를 받을 수 있다.
	 */
	
    @MessageMapping("/sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        return chatMessage;
    }

    @MessageMapping("/addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor){
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        chatService.selectChat();
        //chatMapper.chatSel();
        return chatMessage;
    }
}
