package com.donaldy.controller;

import com.donaldy.model.FileInfo;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;

/**
 * Created by DonaldY on 2018/7/14.
 */
@RestController
@RequestMapping("/file")
public class FileController {
    
    @PostMapping
    public FileInfo uplooad(MultipartFile file) throws IOException {
        System.out.println(file.getName());
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize());
        
        String folder = "D:\\tools\\Code\\Y_Repository\\Standard\\SpringBoot-standard\\src\\main\\resources";

        File localFile = new File(folder, new Date().getTime()+".txt");
        
        file.transferTo(localFile);
        
        return new FileInfo(localFile.getAbsolutePath());
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
}
