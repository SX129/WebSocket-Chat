package com.example.websocket_chat.client;

import com.example.websocket_chat.Message;
import java.util.ArrayList;

public interface MessageListener {
    void onMessageRecieve(Message message);
    void onActiveUsersUpdated(ArrayList<String> users);
}
