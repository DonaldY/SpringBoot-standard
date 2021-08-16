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
