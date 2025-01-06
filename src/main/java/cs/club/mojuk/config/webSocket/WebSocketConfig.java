package cs.club.mojuk.config.webSocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import cs.club.mojuk.menu.talk.TalkRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final TalkRoomRepository talkRoomRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public WebSocketConfig(TalkRoomRepository talkRoomRepository, ObjectMapper objectMapper) {
        this.talkRoomRepository = talkRoomRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketTalkHandler(), "/ws/talk").setAllowedOrigins("*");
    }

    @Bean
    public WebSocketTalkHandler webSocketTalkHandler() {
        return new WebSocketTalkHandler(talkRoomRepository, objectMapper);
    }
}
