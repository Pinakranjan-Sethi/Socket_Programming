package com.demosocket;

import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(5003);
            System.out.println("✅ Server started. Waiting for client connection...");

            Socket socket = serverSocket.accept();
            System.out.println("🎉 Client connected!");

            BufferedReader inputFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter outputToClient = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));

            String clientMessage;
            while (true) {
                clientMessage = inputFromClient.readLine();
                if (clientMessage == null || clientMessage.equalsIgnoreCase("exit")) {
                    System.out.println("🚪 Client disconnected.");
                    break;
                }

                System.out.println("Client: " + clientMessage);

                System.out.print("You (Server): ");
                String serverReply = consoleInput.readLine();
                outputToClient.println(serverReply);

                if (serverReply.equalsIgnoreCase("exit")) {
                    System.out.println("🛑 Server closed connection.");
                    break;
                }
            }

            socket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
