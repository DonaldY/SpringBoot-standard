package com.donaldy.utils;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.*;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Calendar;

public class AliyunOSSUtils {

    private static Logger logger = LoggerFactory.getLogger(AliyunOSSUtils.class);
    private static final String endpoint = "oss-cn-hangzhou.aliyuncs.com";
    private static final String accessKeyId = "";
    private static final String accessKeySecret = "";
    private static final String bucketName = "";



    private AliyunOSSUtils(){}

    public static OSSClient getInstance() {
        return OSSClientEnum.INSTANCE.getInstance();
    }

    private enum OSSClientEnum {
        INSTANCE;

        private OSSClient instance;

        OSSClientEnum() {
            instance = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        }

        public OSSClient getInstance() {
            return this.instance;
        }
    }


    /**
     * upload file
     * @param file
     * @param fileDir
     * @return
     * @throws IOException
     */
    public static String upload(MultipartFile file, String fileDir) throws IOException {

        OSSClient ossClient = getInstance();
        createBucket(ossClient, bucketName, CannedAccessControlList.Private);
        String fileURL = uploadFile(ossClient, file, fileDir);

        return fileURL;
    }

    private static void createBucket(OSSClient ossClient, String _bucketName, CannedAccessControlList controlList) {
        if(!ossClient.doesBucketExist(_bucketName)){
            CreateBucketRequest bucketRequest = new CreateBucketRequest(_bucketName);
            bucketRequest.setCannedACL(controlList);
            ossClient.createBucket(bucketRequest);
            logger.info("create oss bucket {}", _bucketName);
        }
    }

    private static String uploadFile(OSSClient ossClient, MultipartFile file, String fileDir) throws IOException {
        /**
         * format : project/1533644522355_test.txt
         */
        String fileURL = fileDir +  File.separator
                + Calendar.getInstance().getTimeInMillis() + "_"
                + file.getOriginalFilename();
        try {
            PutObjectResult result = ossClient.putObject(bucketName, fileURL, file.getInputStream());
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new IOException("上传异常");
        } catch (OSSException e) {
            logger.error(e.getMessage());
            throw new OSSException("OSS异常");
        }
        return fileURL;
    }

    /**
     * download file
     * @param response
     * @param fileKey
     * @throws IOException
     */
    public static void download(HttpServletResponse response, String fileKey) throws IOException {
        OSSClient ossClient = getInstance();

        OSSObject ossObject = ossClient.getObject(bucketName, fileKey);

        String fileName = fileKey.substring(fileKey.lastIndexOf("_") + 1);

        try(
                InputStream inputStream = new DataInputStream(ossObject.getObjectContent());
                OutputStream outputStream = response.getOutputStream()
        ) {

            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
            IOUtils.copy(inputStream, outputStream);
            outputStream.flush();
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new IOException("下载文件异常");
        } catch (OSSException e) {
            logger.error(e.getMessage());
            throw new OSSException("找不到对应文件");
        }
    }

    /**
     * get some OSS object
     * @param _bucketName
     */
    public static void listObjects(String _bucketName) {
        OSSClient ossClient = getInstance();

        ObjectListing listing = ossClient.listObjects(_bucketName);

        for (OSSObjectSummary ossObjectSummary : listing.getObjectSummaries()) {
            System.out.println(ossObjectSummary.getKey());
        }

    }

}