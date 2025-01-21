package org.example.ex4;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ErrorTest {

    public static void main(String[] args) throws Exception {

        try (ServerSocket serverSocket = new ServerSocket(5555)) {
            System.out.println("FTServer is running...");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected: " + socket.getInetAddress());

                // 클라이언트를 별도 쓰레드로 처리
                new Thread(() -> handleClient(socket)).start();
            }
        } catch (Exception e) {
            System.err.println("Server error: " + e.getMessage());
        }
    }

    private static void handleClient(Socket socket) {
        try (OutputStream out = socket.getOutputStream();
                InputStream in = new FileInputStream(new File("C:\\zzz\\ring.mp4"))) {

            File target = new File("C:\\zzz\\ring.mp4");
            if (!target.exists()) {
                System.err.println("File not found: " + target.getPath());
                return;
            }

            long targetLength = target.length();

            // HTTP 헤더 전송
            out.write("HTTP/1.1 200 OK\r\n".getBytes());
            out.write("Content-Type: video/mp4\r\n".getBytes());
            out.write(("Content-Length: " + targetLength + "\r\n").getBytes());
            out.write("\r\n".getBytes()); // 헤더 끝

            // 파일 데이터 전송
            byte[] buffer = new byte[1024 * 8];
            int count;
            while ((count = in.read(buffer)) != -1) {
                out.write(buffer, 0, count);
            }
            out.flush();
            System.out.println("File sent successfully to client: " + socket.getInetAddress());

        } catch (Exception e) {
            System.err.println("Error handling client: " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (Exception e) {
                System.err.println("Error closing socket: " + e.getMessage());
            }
        }
    }
}
