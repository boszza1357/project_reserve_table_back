package com.project.project_reserve_table.apieasyslip;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


@Service
public class EasyslipService {

    private static final String API_URL = "https://developer.easyslip.com/api/v1/verify";
    private static final String API_KEY = "c1ba60e8-b70d-455c-9f45-a93326c57dd3";

    public String verifySlip(MultipartFile file) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(API_URL);
            httpPost.setHeader("Authorization", "Bearer " + API_KEY);

            File convFile = convertMultiPartToFile(file);

            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            builder.addPart("file", new FileBody(convFile));

            httpPost.setEntity(builder.build());

            HttpResponse response = httpClient.execute(httpPost);
            return EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }
}
