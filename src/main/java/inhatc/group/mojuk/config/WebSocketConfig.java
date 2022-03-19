package inhatc.group.mojuk.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration //Bean 설정 명시
@EnableWebSocketMessageBroker //WebSocket 서버 활성화
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer { //WebSocket 연결 구성 메서드 제공

    //클라이언트에서 다른 클라이언트로 메시지를 라우팅 할 때 사용될 메시지 브로커 구성
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
    	/*
    	 * '/app'으로 시작되는 메시지가 message-handling methods로 라우팅 되어야 하는것을 명시
    	 * 메시지를 수신하는 handler의 메시지 prefix 설정
    	 * 간단하게 url 맨 앞에 설정 값(여기서는 /app)이 붙어야 함
    	 */
        registry.setApplicationDestinationPrefixes("/app");
        
        /* 
         * '/topic'으로 시작되는 메시지가 메시지 브로커로 라우팅 되도록 정의
         * in-memory message-broker, topic에 대한 prefix 설정
         * 간단하게, 설정 값(여기서는 '/topic')으로 시작되야 수신자 정의 가능하다는 의미
         */
        registry.enableSimpleBroker("/topic");
    }
    
	//클라이언트가 웹 소켓 서버에 연결하는 데 사용할 웹 소켓 엔드포인트 등록
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //registry.addEndpoint("/ws").withSockJS();
    	
    	//CORS등 접근 제한 때문에 다른 도메인에서 접근하지 못할 경우, 접근 가능하게 하려면 setAllowedOrigins("*") 설정을 추가
    	registry.addEndpoint("/ws").setAllowedOrigins("*").withSockJS();
    }
}