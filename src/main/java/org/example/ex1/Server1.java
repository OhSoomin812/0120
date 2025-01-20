package org.example.ex1;

import lombok.Cleanup;

import javax.swing.*;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server1 {

    // bad code
    public static void main(String[] args) throws Exception {

        // 5555번으로 서버 열기
        @Cleanup
        ServerSocket serverSocket = new ServerSocket(5555);
        System.out.println("Server Opened...");
        for (int i = 0; i < 100; i++) {
            @Cleanup
            Socket clientSocket = serverSocket.accept();
            System.out.println(clientSocket);

            @Cleanup
            InputStream inputStream = clientSocket.getInputStream();

            @Cleanup
            Scanner scanner = new Scanner(inputStream);
            String line = scanner.nextLine();

            System.out.println(line);
        }

    }
}
