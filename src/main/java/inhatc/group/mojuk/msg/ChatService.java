package inhatc.group.mojuk.msg;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class ChatService {
	
	ChatMapper chatMapper;
	
	public List<Map<String, String>> selectChat(){
		return chatMapper.chatSel();
	}
	
}
