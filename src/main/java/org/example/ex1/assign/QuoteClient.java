package org.example.ex1.assign;

import lombok.Cleanup;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class QuoteClient {

    public static void main(String[] args) throws IOException {

        @Cleanup
        Socket socket = new Socket("127.0.0.1", 5555);
        System.out.println("서버에 연결되었습니다.");

        // 서버에서 명언 수신
        @Cleanup InputStream inputStream = socket.getInputStream();
        byte[] buffer = new byte[1024];
        int bytesRead = inputStream.read(buffer);

        // 명언 출력
        String quote = new String(buffer, 0, bytesRead).trim();
        System.out.println("명언: " + quote);
    }
}
