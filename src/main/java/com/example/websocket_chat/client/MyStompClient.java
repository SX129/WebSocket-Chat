package com.example.websocket_chat.client;

import com.example.websocket_chat.Message;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MyStompClient {
    // allows us to connect to stomp servers (what the websocket server is based on)
    private StompSession session;
    private String username;

    public MyStompClient(String username) throws ExecutionException, InterruptedException {
        this.username = username;

        // create a list of transports (protocols) to use
        List<Transport> transports = new ArrayList<>();
        transports.add(new WebSocketTransport(new StandardWebSocketClient()));

        // define sockJS client to use STOMP protocol
        SockJsClient sockJsClient = new SockJsClient(transports);
        WebSocketStompClient stompClient = new WebSocketStompClient(sockJsClient);

        // allows stompClient to automatically serialize and deserialize Java objects and JSON for the websocket
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());

        // session handler to handle the new user
        StompSessionHandler sessionHandler = new MyStompSessionHandler(username);

        //URL for websocket uses ws://
        String url = "ws://localhost:8080/ws";
        session = stompClient.connectAsync(url, sessionHandler).get();

        //session.send("/app/message", new Message(username, "Hello World!"));
    }

    public void sendMessage(Message message){
        try {
            session.send("/app/message", message);
            System.out.println("Sent message: " + message.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
