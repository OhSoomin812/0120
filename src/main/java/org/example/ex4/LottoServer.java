package org.example.ex4;

import lombok.Cleanup;
import org.example.ex2.WiseSayingService;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class LottoServer {

    public static void main(String[] args) throws Exception {

        @Cleanup
        ServerSocket serverSocket = new ServerSocket(5555);
        System.out.println("서버 가동중...");

        byte[] buffer = new byte[1024 * 8];

        while (true) {
            @Cleanup Socket socket = serverSocket.accept();
            @Cleanup InputStream in = socket.getInputStream();
            @Cleanup OutputStream out = socket.getOutputStream();

            int count = in.read(buffer);

            if (count == -1) {break;}

            // 요청을 문자열로 변환
            String message = new String(buffer, 0, count);
            System.out.println("클라이언트 요청: \n" + message);

            // 로또 번호 생성 및 HTML 응답 작성
            String lottoNumbers = getLottoBalls();
            String response = """
                    HTTP/1.1 200 OK
                    Content-Type: text/html; charset=UTF-8
                    
                    """;
            response += "<html><body><h1>로또 번호</h1><p>" + lottoNumbers + "</p></body></html>";

            // 응답을 클라이언트로 전송
            out.write(response.getBytes());
            out.flush();

        }
    }

    public static String getLottoBalls() {
        int[] lottoBalls = new int[6];
        for (int i = 0; i < 6; i++) {
            lottoBalls[i] = (int) (Math.random() * 45) + 1;
            for (int j = 0; j < i; j++) {
                if (lottoBalls[i] == lottoBalls[j]) {
                    i--; // 중복이 발생하면 다시 뽑기
                    break;
                }
            }
        }
        // 정렬 후 문자열로 반환
        Arrays.sort(lottoBalls);
        return Arrays.toString(lottoBalls);
    }
}
