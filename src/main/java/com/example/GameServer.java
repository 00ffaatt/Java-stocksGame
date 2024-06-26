package com.example;

import java.io.*;
import java.net.*;
import java.util.*;

public class GameServer {
    private static final int PORT = 12345;
    private static Set<PrintWriter> clientWriters = new HashSet<>();
    private static Game game;

    public static void main(String[] args) {
        System.out.println("Game server is running...");
        game = new Game(2); // Initialize game with 2 players

        try (ServerSocket listener = new ServerSocket(PORT)) {
            while (true) {
                new ClientHandler(listener.accept()).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ClientHandler extends Thread {
        private Socket socket;
        private PrintWriter out;
        private BufferedReader in;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

                synchronized (clientWriters) {
                    clientWriters.add(out);
                }

                String message;
                while ((message = in.readLine()) != null) {
                    System.out.println("Received: " + message);
                    processMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                synchronized (clientWriters) {
                    clientWriters.remove(out);
                }
            }
        }

        private void broadcast(String message) {
            synchronized (clientWriters) {
                for (PrintWriter writer : clientWriters) {
                    writer.println(message);
                }
            }
        }

        private void processMessage(String message) {
            // Example processing logic
            if (message.equals("DRAW")) {
                // Implement draw logic
            } else if (message.startsWith("DISCARD ")) {
                String companyName = message.substring(8);
                // Implement discard logic using companyName
            } else if (message.startsWith("PUBLICIZE ")) {
                String companyName = message.substring(10);
                // Implement publicize logic using companyName
            } else if (message.equals("ENDTURN")) {
                // Implement end turn logic
            }
            broadcast(message);
        }

    }
}
