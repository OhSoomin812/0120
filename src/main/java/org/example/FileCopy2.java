package org.example;

import lombok.Cleanup;

import java.io.*;

public class FileCopy2 {

    // try ~ catch
    public static void main(String[] args) throws Exception{

        // 첫 번째 방밥 try ~ catch ~ finally
        InputStream in = null;

        try {
            in = new FileInputStream("C:\\zzz\\copy.jpg");
        } catch (Exception e) {

        }finally {
            try { in.close(); } catch (IOException e) {}
        }

        // 2 번째 방법 try with resource // auto close
        try(InputStream in2 = new FileInputStream("")) {

        }catch(Exception e) {

        }

        // 3번째 방식
        @Cleanup
        InputStream in3 = new FileInputStream("C:\\zzz\\dog.jpg");
        @Cleanup
        OutputStream out = new FileOutputStream("C:\\zzz\\copy.jpg");

        while(true) {
            int data = in3.read();
//            System.out.println(data);

            if(data == -1) {    // 더 이상 읽어야 하는 데이터가 없는 경우
                break;
            }
        }

        System.out.println(in3);
    }
}
