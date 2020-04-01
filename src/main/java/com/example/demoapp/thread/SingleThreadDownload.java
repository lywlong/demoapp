package com.example.demoapp.thread;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class SingleThreadDownload {

    private static final String download_url = "http://desk.zol.com.cn/showpic/1366x768_33953_146.html";

    public static void main(String[] args) {
        singleThreadDownload(download_url);
    }

    public static void singleThreadDownload(String download_url){

        try {
            //创建URL对象
            URL url = new URL(download_url);
            //创建URLConnection，并打开一个连接（获得一个连接对象）
            URLConnection urlConnection = url.openConnection();
            //打开到URL引用资源的通信链接
            urlConnection.connect();
            //获得输入流对象
            InputStream in = urlConnection.getInputStream();
            //通过URL对象获得下载资源全路径
            String filePath = url.getFile();
            //获得路径中最后一个“/”的位置
            int index = filePath.lastIndexOf("/");
            //获取文件名
            String fileName = filePath.substring(index+1);
            //创建输出流对象
            FileOutputStream out = new FileOutputStream("D:\\load\\"+fileName);
            //声明存放下载资源的字节数组
            byte [] bytes = new byte[1024];
            //从输入流中读取内容
            int len = in.read(bytes);
            while (len!=-1){
                //将读取的内容写入到输出流
                out.write(bytes,0,len);
                //继续从输入流读取内容
                len = in.read(bytes);
            }
            out.close();//关闭输出 流
            in.close();//关团输入流
            System.out.println("下载完毕！");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
