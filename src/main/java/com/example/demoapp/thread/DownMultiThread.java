package com.example.demoapp.thread;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class DownMultiThread implements Runnable {

    private String sUrl = "";//网络资源地址
    private File desFile;//需要写入的目标文件对象
    private long startPos;//写入的开始集团
    private long endPos;//写入的结束位置

    public DownMultiThread(String sUrl, File desFile, long startPos, long endPos) {
        this.sUrl = sUrl;
        this.desFile = desFile;
        this.startPos = startPos;
        this.endPos = endPos;
    }

    @Override
    public void run() {
        try {
            URL url = new URL(sUrl);
            URLConnection conn = url.openConnection();
            //创建可读写的流对象
            RandomAccessFile randomAccessFile = new RandomAccessFile(desFile,"rw");
            //指定读写的开关标记
            randomAccessFile.seek(startPos);
            //获得网络的输入流对象
            InputStream in = conn.getInputStream();
            //创建输入缓冲流对象,并读入输入流对象
            BufferedInputStream bin = new BufferedInputStream(in);
            //通过URL对象获得下载资源全路径
            String filePath = url.getFile();
            //获得路径中最后一个“/”的位置
            int index = filePath.lastIndexOf("/");
            //获取文件名
            String fileName = filePath.substring(index+1);
            //创建输出流对象
            FileOutputStream out = new FileOutputStream("D:\\load\\"+fileName);
            //创建字节数组
            byte [] bytes = new byte[1024];
            //
            int len = -1;
            len = bin.read(bytes);
            while (len != -1){
                out.write(bytes,0,len);
                len = bin.read(bytes);
            }
            out.close();
            bin.close();
            in.close();
        }  catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
