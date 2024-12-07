package com.example.websocket_chat.client;

import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.util.ArrayList;
import java.util.List;

public class MyStompClient {
    // allows us to connect to stomp servers (what the websocket server is based on)
    private StompSession session;
    private String username;

    public MyStompClient(String username){
        this.username = username;

        // create a list of transports (protocols) to use
        List<Transport> transports = new ArrayList<>();
        transports.add(new WebSocketTransport(new StandardWebSocketClient()));

        // define sockJS client to use STOMP protocol
        SockJsClient sockJsClient = new SockJsClient(transports);
        WebSocketStompClient stompClient = new WebSocketStompClient(sockJsClient);

        // allows stompClient to automatically serialize and deserialize Java objects and JSON for the websocket
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());
    }
}
