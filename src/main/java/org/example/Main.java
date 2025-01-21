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

        byte[] buffer = new byte[4096];
        int bytesRead;

        while((bytesRead = in.read(buffer)) != -1) {
            out.write(buffer, 0, bytesRead);
        }

        in.close();
        out.close();
    }
}