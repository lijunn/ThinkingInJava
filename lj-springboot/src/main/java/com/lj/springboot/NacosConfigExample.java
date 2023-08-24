package com.lj.springboot;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.Executor;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;


/**
 * @author : LiJun
 * @date : 2023-08-24 14:27
 **/
public class NacosConfigExample {
    public static void main(String[] args) throws NacosException, InterruptedException {
        String serverAddr = "172.16.3.154";
        String dataId = "application-dev.yml";
        String group = "DEFAULT_GROUP";
//        String serverAddr = "139.224.1.155";
//        String dataId = "datasource-config.yaml";
//        String group = "integrated-example";

        try {
            Properties properties = new Properties();
            properties.put("serverAddr", serverAddr);
            ConfigService configService = NacosFactory.createConfigService(properties);
            String content = configService.getConfig(dataId, group, 5000);
            System.out.println(content);
        } catch (NacosException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


//    public static void main(String[] args) {
////        String url = "http://172.16.3.154:8848/nacos/v1/cs/configs?dataId=application-dev.yml&group=DEFAULT_GROUP"; // Specify the URL you want to send the request to
//        String url = "https://nacos.foursmile.cn/nacos/v1/cs/configs?dataId=datasource-config.yaml&group=integrated-example"; // Specify the URL you want to send the request to
//
//        try {
//            URL urlObj = new URL(url);
//            HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
//            connection.setRequestMethod("GET");
//
//            int responseCode = connection.getResponseCode();
//            System.out.println("Response Code: " + responseCode);
//
//            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//            String line;
//            StringBuilder response = new StringBuilder();
//
//            while ((line = reader.readLine()) != null) {
//                response.append(line);
//            }
//            reader.close();
//
//            System.out.println("Response Body: " + response.toString());
//
//            connection.disconnect();
//        } catch (Exception e) {
//            System.out.println("Error occurred: " + e.getMessage());
//        }
//    }
}
