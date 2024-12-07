package com.example.websocket_chat.client;

import java.util.concurrent.ExecutionException;

public class ClientGUI {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyStompClient myStompClient = new MyStompClient("user1");
    }
}
