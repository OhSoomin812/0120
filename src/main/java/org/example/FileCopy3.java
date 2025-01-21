package org.example;

import lombok.Cleanup;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class FileCopy3 {

    //bad code
    public static void main(String[] args) throws Exception {

        @Cleanup
        InputStream fin = new FileInputStream("C:\\zzz\\aaa.jpg");
        
        byte[] buffer = new byte[1024 * 8];

        @Cleanup
        OutputStream fos = new FileOutputStream("copy.jpg");

        long start = System.currentTimeMillis();
        
        while (true) {
            
            int count = fin.read(buffer);   // 계란판을 이용한 읽기

            if (count == -1) break;

            fos.write(buffer, 0, count);    // 맨 처음부터, 새롭게 채워진 숫자만큼만
        } // end while

        long end = System.currentTimeMillis();

        System.out.println(end - start               );
    }
}
