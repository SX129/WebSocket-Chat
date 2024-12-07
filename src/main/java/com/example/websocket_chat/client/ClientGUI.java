package com.example.websocket_chat.client;

import com.example.websocket_chat.Message;

import java.util.concurrent.ExecutionException;

public class ClientGUI {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyStompClient myStompClient = new MyStompClient("user1");
        myStompClient.sendMessage(new Message("user1", "Hello World!"));
    }
}
