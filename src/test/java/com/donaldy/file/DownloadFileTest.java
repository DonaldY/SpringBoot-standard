package com.donaldy.file;

import org.apache.commons.io.FileUtils;
import org.asynchttpclient.*;
import org.junit.Test;

import java.io.*;
import java.net.*;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;


/**
 * @author donald
 * @date 2021/08/12
 */
public class DownloadFileTest {

    String FILE_NAME = "test.dmg";
    String FILE_URL = "http://172.18.1.23:7480/groupchat/unknown/202108/0052863e-ade4-4683-896c-cf0ff7d98495_original.dmg";

    @Test
    public void testDownloadFileByJavaIO() {
        long now = System.currentTimeMillis();

        try (BufferedInputStream in = new BufferedInputStream(new URL(FILE_URL).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(FILE_NAME)
        ) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
        } catch (IOException e) {
            System.out.println("error");
            // handle exception
        }
        System.out.println(System.currentTimeMillis() - now);
    }

    @Test
    public void testDownloadFileNIO() throws IOException {

        long now = System.currentTimeMillis();

        ReadableByteChannel readableByteChannel = Channels.newChannel(new URL(FILE_URL).openStream());

        FileOutputStream fileOutputStream = new FileOutputStream(FILE_NAME);

        fileOutputStream.getChannel()
                .transferFrom(readableByteChannel, 0, Long.MAX_VALUE);

        System.out.println(System.currentTimeMillis() - now);
    }

    @Test
    public void testDownloadFileHttpClient() throws FileNotFoundException, InterruptedException {
        long now = System.currentTimeMillis();
        AsyncHttpClient client = Dsl.asyncHttpClient();

        FileOutputStream stream = new FileOutputStream(FILE_NAME);

        client.prepareGet(FILE_URL).execute(new AsyncCompletionHandler<FileOutputStream>() {

            @Override
            public State onBodyPartReceived(HttpResponseBodyPart bodyPart)
                    throws Exception {
                stream.getChannel().write(bodyPart.getBodyByteBuffer());
                return State.CONTINUE;
            }

            @Override
            public FileOutputStream onCompleted(Response response) {
                System.out.println(System.currentTimeMillis() - now);
                return stream;
            }
        });
        Thread.sleep(100000);
    }

    @Test
    public void testDownloadFileIOUtils() throws IOException {

        FileUtils.copyURLToFile(
                new URL(FILE_URL),
                new File(FILE_NAME));
    }

    @Test
    public void tesResumableDownload() throws IOException, URISyntaxException {

        File outputFile = new File(FILE_NAME);

        URLConnection downloadFileConnection = addFileResumeFunctionality(FILE_URL, outputFile);
        long size = transferDataAndGetBytesDownloaded(downloadFileConnection, outputFile);
        System.out.println("size : " + size);
    }

    private URLConnection addFileResumeFunctionality(String downloadUrl, File outputFile)
            throws IOException, URISyntaxException {
        long existingFileSize = 0L;
        URLConnection downloadFileConnection = new URI(downloadUrl).toURL()
                .openConnection();

        if (outputFile.exists() && downloadFileConnection instanceof HttpURLConnection) {
            HttpURLConnection httpFileConnection = (HttpURLConnection) downloadFileConnection;

            HttpURLConnection tmpFileConn = (HttpURLConnection) downloadFileConnection;
            tmpFileConn.setRequestMethod("HEAD");
            long fileLength = tmpFileConn.getContentLengthLong();
            existingFileSize = outputFile.length();

            if (existingFileSize < fileLength) {
                httpFileConnection.setRequestProperty("Range", "bytes=" + existingFileSize + "-" + fileLength);
            } else {
                throw new IOException("File Download already completed.");
            }
        }
        return downloadFileConnection;
    }

    private long transferDataAndGetBytesDownloaded(URLConnection downloadFileConnection, File outputFile)
            throws IOException {

        long bytesDownloaded = 0;
        try (InputStream is = downloadFileConnection.getInputStream();
             OutputStream os = new FileOutputStream(outputFile, true)
        ) {

            byte[] buffer = new byte[1024];

            int bytesCount;
            while ((bytesCount = is.read(buffer)) > 0) {
                os.write(buffer, 0, bytesCount);
                bytesDownloaded += bytesCount;
            }
        }
        return bytesDownloaded;
    }
}
