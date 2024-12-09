package com.example.websocket_chat.client;

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ClientGUI clientGUI = new ClientGUI("user1");

                clientGUI.setVisible(true);
            }
        });
    }
}
