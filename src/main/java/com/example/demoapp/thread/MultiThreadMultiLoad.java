package com.example.demoapp.thread;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MultiThreadMultiLoad {

    public static void main(String[] args) {

    }

    public static void download(String url,String dest,int threadNum){
        try {
            URL downUrl = new URL(url);
            HttpURLConnection httpConn = (HttpURLConnection) downUrl.openConnection();
            httpConn.connect();
            //用于存储文件长度的变量
            long fileLength = -1;
            //获得连接状态标记代码
            int stateFlagCode = httpConn.getResponseCode();
            //
            if(stateFlagCode == 200){
                //获得文件长度
                fileLength = httpConn.getContentLength();
                //取消网络连接
                httpConn.disconnect();
            }
            if(fileLength > 0){
                //计算每个线程的字节数
                long byteCounts = fileLength / threadNum + 1;
                //创建目标文件的File对象
                File fle = new File(dest);
                int i = 0;
                while (i < threadNum){
                    long startPosition = byteCounts * i;
                    long endPosition = byteCounts * (i+1);
                    if (i == threadNum -1){
                        DownMultiThread downMultiThread = new DownMultiThread(url,fle,startPosition,endPosition);
                        Thread thread = new Thread(downMultiThread);
                        thread.start();
                    }else{
                        DownMultiThread downMultiThread = new DownMultiThread(url,fle,startPosition,endPosition);
                        new Thread(downMultiThread).start();
                    }
                    i++;
                }

            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
