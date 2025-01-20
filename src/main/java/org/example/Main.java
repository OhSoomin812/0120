package org.example;

import java.io.*;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws IOException {

        String path = "http://mp4.cine21.com/cine21/multi/trailer/a-b/banzi_pre_2.mp4";
        URL url = new URL(path);
        InputStream in = url.openStream();

        // InputStream in = new FileInputStream("C:\\zzz\\dog.jpg");
        OutputStream out = new FileOutputStream("C:\\zzz\\copy.mp4");

        while(true) {
            int data = in.read();
//            System.out.println(data);

            if(data == -1) {    // 더 이상 읽어야 하는 데이터가 없는 경우
                break;
            }
        }

        in.close();
        out.close();
    }
}