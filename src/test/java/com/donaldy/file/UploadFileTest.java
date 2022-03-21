package com.donaldy.file;

import com.donaldy.common.ServerResponse;
import com.donaldy.dto.MultipartParamDTO;
import com.donaldy.utils.JsonUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;



/**
 * @author donald
 * @date 2022/02/09
 */
@Slf4j
public class UploadFileTest {

    private OkHttpClient client;
    private File file;
    private static final String BASE_URL = "https://bling-api-dev.percent.cn/api/oss";
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static final int chunkSize = 2097152;

    @Before
    public void before() {

        System.setProperty("http.proxyHost", "127.0.0.1");
        System.setProperty("http.proxyPort", "8888");

        client = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build();

        file = new File("/home/donald/Downloads/shardingsphere_docs_cn.pdf");
    }

    @Test
    public void testMultiUpload() {

        this.multiUpload("");
    }

    @Test
    public void upload() throws Exception {

        UploadInfo uploadInfo = prepare();

        multiUpload(uploadInfo.getUploadId());

        finish(uploadInfo.getUploadId());
    }

    private void finish(String uploadId) throws Exception {

        MultipartFinishDTO multipartFinishDTO = new MultipartFinishDTO();

        String requestJson = JsonUtils.objectToStr(multipartFinishDTO);

        String result = this.post(requestJson, "/multiUpload/finish");

        ServerResponse<MultipartFinishVO> serverResponse = JsonUtils.strToObject(result,
                new TypeReference<ServerResponse<MultipartFinishVO>>(){});

        log.info("finish return info : {}", serverResponse);
    }

    private void multiUpload(String uploadId) {

        int chunks = (int) Math.ceil(file.length() / chunkSize);

        for (int i = 0; i < chunks; ++i) {

            log.info("开始上传 {} 块", i);

            multiUploadInner(uploadId);

            log.info("结束上传 {} 块", i);
        }
    }

    private void multiUploadInner(String uploadId) {

        final HttpUrl.Builder urlBuilder = HttpUrl.parse(BASE_URL + "/multiUpload").newBuilder();
        urlBuilder.addQueryParameter("uploadId", uploadId);
        final String url = urlBuilder.build().toString();

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("files", file.getName(),
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                file))
                .build();

        Request request = new Request.Builder()
                .addHeader("token", "")
                .addHeader("deviceId", "257e119c33d283583ba4ce02ca15718f9")
                .addHeader("osType", "0")
                .addHeader("deviceType", "0")
                .addHeader("deviceOS", "Android samsung 10")
                .addHeader("Range", "0-")
                .url(url)
                .post(requestBody)
                .build();

        Call call = client.newCall(request);

        try {
            Response response = call.execute();

            log.info("upload info : {}", response.toString());

        } catch (IOException e) {

            log.info("post error : ", e);
        }
    }

    private UploadInfo prepare() throws Exception {

        MultipartParamDTO multipartParamDTO = new MultipartParamDTO(file.getName(), file.length(),
                chunkSize, 2);

        String requestJson = JsonUtils.objectToStr(multipartParamDTO);

        String result = this.post(requestJson, "/multiUpload/prepare/chat");

        ServerResponse<UploadInfo> serverResponse = JsonUtils.strToObject(result,
                new TypeReference<ServerResponse<UploadInfo>>(){});

        log.info("prepare return info : {}", serverResponse);

        if (Objects.isNull(serverResponse.getData())) {

            throw new Exception("");
        }

        return serverResponse.getData();
    }

    private String post(String json, String relativeUrl) {

        RequestBody requestBody = RequestBody.create(JSON, json);

        Request request = new Request.Builder()
                .url(BASE_URL + relativeUrl)
                .post(requestBody)
                .build();

        Call call = client.newCall(request);

        try {
            Response response = call.execute();

            return response.body().string();

        } catch (IOException e) {

            log.info("post error : ", e);
        }

        return "";
    }
}

@Data
class UploadInfo {
    /**
     * 分块大小
     */
    private Long blockSize;
    /**
     * 分块数量
     */
    private Long numBlocks;
    /**
     * 上传Id
     */
    private String uploadId;
}

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
class MultipartFinishVO {
    /**
     * 文件名
     */
    private String fileName;
    /**
     * 文件大小
     */
    private Long fileSize;
    /**
     * 原文件
     */
    private String originalKey;
    /**
     * 压缩文件
     */
    private String compressedKey;
}

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
class MultipartFinishDTO {
    /**
     * 上传Id
     */
    private String uploadId;
    /**
     * 块数量
     */
    private Integer numBlocks;
}
