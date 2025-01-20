package org.example.ex1.assign;

import lombok.Cleanup;

import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuoteServer {

    private static final List<String> quotes = new ArrayList<>();

    static {
        quotes.add("삶의 진정한 가치는 어떻게 살았는가에 달려 있다.");
        quotes.add("어제보다 나은 오늘을 만들어라.");
        quotes.add("꿈을 꾸지 않은 사람은 사라진다.");
        quotes.add("성공은 준비된 자에게 찾아온다.");
        quotes.add("멈추지 않은 사람이 목표를 이룬다.");
    }

    public static void main(String[] args) throws Exception {

        @Cleanup ServerSocket serverSocket = new ServerSocket(5555);
        System.out.println("명언 서버 스타트 ....");

        while (true) {
            @Cleanup
            Socket clientSocket = serverSocket.accept();
            System.out.println("클라이언트 연결: " + clientSocket);

            // 랜덤 명언 선택
            Random random = new Random();
            String randomQuote = quotes.get(random.nextInt(quotes.size()));

            // 명언 전송
            @Cleanup OutputStream outputStream = clientSocket.getOutputStream();
            outputStream.write((randomQuote + "\n").getBytes());
        }
    }
}
