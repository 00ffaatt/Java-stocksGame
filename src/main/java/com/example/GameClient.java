package com.example;

import java.io.*;
import java.net.*;
import javafx.application.Platform;

public class GameClient {
    private String serverAddress;
    private int serverPort;
    private App app;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public GameClient(String serverAddress, int serverPort, App app) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
        this.app = app;
    }

    public void connect() {
        try {
            socket = new Socket(serverAddress, serverPort);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            Thread readThread = new Thread(this::processServerMessages);
            readThread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message) {
        if (out != null) {
            out.println(message);
        }
    }

    private void processServerMessages() {
        try {
            String message;
            while ((message = in.readLine()) != null) {
                final String msg = message;
                Platform.runLater(() -> handleServerMessage(msg));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleServerMessage(String message) {
        // Implement logic to process server messages and update game state/UI
        // Example:
        // if (message.startsWith("UPDATE_HAND")) {
        // // Update handListView, etc.
        // }

    }

    public void disconnect() {
        try {
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
