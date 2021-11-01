package com.donaldy.controller;

import com.donaldy.model.FileInfo;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by DonaldY on 2018/7/14.
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @GetMapping("/ping")
    public String ping() {

        return "pong";
    }

    @PostMapping("/post")
    public String post(@RequestBody Object object) {

        System.out.println(object.toString());

        return "post";
    }
    
    @PostMapping
    public FileInfo upload(MultipartFile file) throws IOException, InterruptedException {
        System.out.println(file.getName());
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize());
        System.out.println("contentType : " + file.getContentType());

        Thread.sleep(1000);

        System.out.println("start upload");

        try (BufferedInputStream in = new BufferedInputStream(file.getInputStream());
             FileOutputStream fileOutputStream = new FileOutputStream(file.getOriginalFilename())) {

            byte dataBuffer[] = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
    
    @GetMapping("/{id}")
    public void download(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String folder = "D:\\tools\\Code\\Y_Repository\\Standard\\SpringBoot-standard\\src\\main\\resources";
        try(
            InputStream inputStream = new FileInputStream(new File(folder, id+".txt"));
            OutputStream outputStream = response.getOutputStream();
            ) {

            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition", "attachment;filename=test.txt");

            IOUtils.copy(inputStream, outputStream);
            outputStream.flush();
        }
    }

    @GetMapping("")
    public void download(HttpServletResponse response) throws IOException {

        long startTime = System.currentTimeMillis();

        System.out.println("开始下载： " + startTime);

        String filePath = "/home/donald/Documents/Percent/File/BLing/Docs/ceph-script/office-template/template.docx";
        try(
                InputStream inputStream = new FileInputStream(new File(filePath));
                OutputStream outputStream = response.getOutputStream()
        ) {

            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition", "attachment;filename=template.docx");

            IOUtils.copy(inputStream, outputStream);
            outputStream.flush();
        }

        System.out.println("下载结束, 花费时间： " + (System.currentTimeMillis() - startTime) / 1000);
    }

    @GetMapping("/chunk/download")
    public void chunkDownload(@RequestHeader(value = "Range") String range,
                              HttpServletRequest request, HttpServletResponse response) {

        String filePath = "/home/donald/Documents/Percent/File/BLing/Docs/ceph-script/office-template/template.docx";

        File file = new File(filePath);

        long startTime = System.currentTimeMillis();

        //开始下载位置
        long startByte = 0;
        //结束下载位置
        long endByte = file.length() - 1;

        //有range的话
        if (range != null && range.contains("bytes=") && range.contains("-")) {
            range = range.substring(range.lastIndexOf("=") + 1).trim();
            String ranges[] = range.split("-");
            try {
                //根据range解析下载分片的位置区间
                if (ranges.length == 1) {
                    //情况1，如：bytes=-1024  从开始字节到第1024个字节的数据
                    if (range.startsWith("-")) {
                        endByte = Long.parseLong(ranges[0]);
                    }
                    //情况2，如：bytes=1024-  第1024个字节到最后字节的数据
                    else if (range.endsWith("-")) {
                        startByte = Long.parseLong(ranges[0]);
                    }
                }
                //情况3，如：bytes=1024-2048  第1024个字节到2048个字节的数据
                else if (ranges.length == 2) {
                    startByte = Long.parseLong(ranges[0]);
                    endByte = Long.parseLong(ranges[1]);
                }

            } catch (NumberFormatException e) {
                startByte = 0;
                endByte = file.length() - 1;
            }
        }

        //要下载的长度
        long contentLength = endByte - startByte + 1;
        //文件名
        String fileName = file.getName();
        //文件类型
        String contentType = request.getServletContext().getMimeType(fileName);

        //响应头设置
        response.setHeader("Accept-Ranges", "bytes");
        //Content-Type 表示资源类型，如：文件类型
        response.setHeader("Content-Type", contentType);
        //Content-Disposition 表示响应内容以何种形式展示，是以内联的形式（即网页或者页面的一部分），还是以附件的形式下载并保存到本地。
        // 这里文件名换成下载后你想要的文件名，inline表示内联的形式，即：浏览器直接下载
        response.setHeader("Content-Disposition", "inline;filename=pom.xml");
        //Content-Length 表示资源内容长度，即：文件大小
        response.setHeader("Content-Length", String.valueOf(contentLength));
        //Content-Range 表示响应了多少数据，格式为：[要下载的开始位置]-[结束位置]/[文件总大小]
        response.setHeader("Content-Range", "bytes " + startByte + "-" + endByte + "/" + file.length());

        response.setStatus(response.SC_OK);
        response.setContentType(contentType);

        BufferedOutputStream outputStream = null;
        RandomAccessFile randomAccessFile = null;
        //已传送数据大小
        long transmitted = 0;
        try {
            randomAccessFile = new RandomAccessFile(file, "r");
            outputStream = new BufferedOutputStream(response.getOutputStream());
            byte[] buff = new byte[2048];
            int len = 0;
            randomAccessFile.seek(startByte);
            //判断是否到了最后不足2048（buff的length）个byte
            while ((transmitted + len) <= contentLength && (len = randomAccessFile.read(buff)) != -1) {
                outputStream.write(buff, 0, len);
                transmitted += len;
            }
            //处理不足buff.length部分
            if (transmitted < contentLength) {
                len = randomAccessFile.read(buff, 0, (int) (contentLength - transmitted));
                outputStream.write(buff, 0, len);
                transmitted += len;
            }

            outputStream.flush();
            response.flushBuffer();
            randomAccessFile.close();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (randomAccessFile != null) {
                    randomAccessFile.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 是否可以用上 NIO 之后测试
     * @param request 请求
     * @param response 返回
     */
    @GetMapping("/download")
    public void downloadFiles(HttpServletRequest request, HttpServletResponse response){

        List<String> list = new ArrayList<>();
        list.add("/home/donald/Downloads/consul-guide.pdf");
        list.add("/home/donald/Downloads/flink.pdf");

        //响应头的设置
        response.reset();
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/octet-stream;charset=utf-8");// 设置response内容的类型

        //设置压缩包的名字
        //解决不同浏览器压缩包名字含有中文时乱码的问题
        String downloadName = "test.zip";
        String agent = request.getHeader("USER-AGENT");
        ZipOutputStream zipos = null;
        //循环将文件写入压缩流
        DataOutputStream os = null;
        try {
            if (agent.contains("MSIE")||agent.contains("Trident")) {
                downloadName = java.net.URLEncoder.encode(downloadName, "UTF-8");
            } else {
                downloadName = new String(downloadName.getBytes("UTF-8"),"ISO-8859-1");
            }
            response.setHeader("Content-Disposition", "attachment;fileName=\"" + downloadName + "\"");

            //设置压缩流：直接写入response，实现边压缩边下载
            zipos = new ZipOutputStream(new BufferedOutputStream(response.getOutputStream()));
            zipos.setMethod(ZipOutputStream.DEFLATED); //设置压缩方法

            for(int i = 0; i < list.size(); i++ ){

                InputStream is = null;
                try{
                    File file = new File(list.get(i));
                    if(file.exists()){
                        //添加ZipEntry，并ZipEntry中写入文件流
                        //这里，加上i是防止要下载的文件有重名的导致下载失败
                        zipos.putNextEntry(new ZipEntry(i + "_" + file.getName()));
                        os = new DataOutputStream(zipos);
                        is = new FileInputStream(file);
                        byte[] b = new byte[1024];
                        int length = 0;
                        while((length = is.read(b))!= -1){
                            os.write(b, 0, length);
                        }
                    }
                } finally {
                    if(null != is){
                        is.close();
                    }
                    zipos.closeEntry();
                }

            }
            if(null != os){
                os.flush();
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭流
            try {
                if(null != os){
                    os.close();
                }
                if(null != zipos){
                    zipos.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
