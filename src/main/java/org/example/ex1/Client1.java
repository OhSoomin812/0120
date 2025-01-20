package org.example.ex1;

import lombok.Cleanup;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Client1 {

    // bad code
    public static void main(String[] args) throws Exception {

        @Cleanup
        Socket socket = new Socket("127.0.0.1", 5555);

        System.out.println(socket);

        @Cleanup
        InputStream inputStream = socket.getInputStream();

        @Cleanup OutputStream outputStream = socket.getOutputStream();

        String msg = "삶의 진정한 가치는 어떻게 살았는가에 달려 있다.\\n";
        byte[] arr = msg.getBytes();

        outputStream.write(arr);
    }
}
