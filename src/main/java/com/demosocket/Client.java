package com.demosocket;

import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 5003);
            System.out.println("✅ Connected to server.");

            BufferedReader inputFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter outputToServer = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));

            String messageToSend;
            String messageFromServer;

            while (true) {
                System.out.print("You (Client): ");
                messageToSend = consoleInput.readLine();
                outputToServer.println(messageToSend);

                if (messageToSend.equalsIgnoreCase("exit")) {
                    System.out.println("🚪 Disconnected from server.");
                    break;
                }

                messageFromServer = inputFromServer.readLine();
                if (messageFromServer == null || messageFromServer.equalsIgnoreCase("exit")) {
                    System.out.println("🛑 Server closed connection.");
                    break;
                }

                System.out.println("Server: " + messageFromServer);
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
