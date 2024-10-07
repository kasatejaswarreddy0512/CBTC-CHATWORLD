package com.cbt.chatworld.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;


import com.cbt.chatworld.handler.ChatWebSocketHandler;

@Configuration
@EnableWebSocket
public class WebSocketConfig  implements WebSocketConfigurer{

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		// TODO Auto-generated method stub
		registry.addHandler(new ChatWebSocketHandler(), "/chat")
					.setAllowedOrigins("*");
		
		
	}

}
