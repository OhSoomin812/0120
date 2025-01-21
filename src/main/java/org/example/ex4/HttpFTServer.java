package org.example.ex4;

import lombok.Cleanup;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class HttpFTServer {

    public static void main(String[] args) throws Exception {

        @Cleanup ServerSocket serverSocket = new ServerSocket(5555);
        System.out.println("FTServer run........");

        // 파일 데이터를 읽어서 outputStream에 write() 한다. - byte[] 이용하면 1000배 빨라짐
        byte[] buffer = new byte[1024 * 8];

        while (true){
            // 브라우저와 연결해서 Socket을 생성해야 한다 - accept()
            @Cleanup Socket socket = serverSocket.accept();

            InputStream in = socket.getInputStream();
            Scanner scanner = new Scanner(in);

            String first = scanner.nextLine();

            if(!first.startsWith("GET /ring")) {
                scanner.close();
                socket.close();

                continue;
            }

            // 보내고 싶으니 OutputStream을 생성한다
            @Cleanup OutputStream out = socket.getOutputStream();

            // 읽고 싶은 파일을 빨대 InputStream을 준비한다.
            File target = new File("C:\\zzz\\ring.mp4");
            @Cleanup InputStream inputStream = new FileInputStream(target);


            // HTTP에 맞는 image/png 헤더 메세지를 outputStream에 기록
            out.write("HTTP/1.1 200 OK\r\n".getBytes());
            out.write("Content-Type: video/mp4\r\n".getBytes());
            out.write(("Content-Length: " + target.length() + "\r\n").getBytes());
            out.write("\r\n".getBytes()); // 헤더 끝 표시


            while (true) {
                try{
                    int count = inputStream.read(buffer);    // 내용물은 버퍼에 있고,

                    if (count == -1) break;

                    out.write(buffer, 0, count);

                }catch  (Exception e) {
                    System.out.println("Exception");
                    break;
                }
            }

            out.close();
            socket.close();
        }
    }
}
