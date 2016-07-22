package me.test.weixin.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by ZhaoTao on 2015/10/19.
 */
@Service
@Scope("singleton")
public  class AccessToken {
    private static  String ACCESS_TOKEN;//获取到的凭证 公众号
    private static  String expires_in; //凭证有效时间，单位：秒
    private final String APPID = "wxd1e31639d8707bab";
    private final String APPSECRET="2c59337b2aad4fe15fb5f93be2c91776";
    String u ="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+APPID+"&secret="+APPSECRET;

    public static String getAccessToken() {
        return ACCESS_TOKEN;
    }

    /**
     * 固定分钟执行一次
     */
    @Scheduled(fixedRate = 7200000)
    public void task(){
        try {
            this.findAccessToken();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void findAccessToken() throws IOException {
        URL url = new URL(u);    // 把字符串转换为URL请求地址
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();// 打开连接
        connection.connect();// 连接会话
        // 获取输入流
        int respCode = connection.getResponseCode();
        if (respCode == 200)
        {
            String temp = ConvertStream2Json(connection.getInputStream());
            System.out.println(temp);
            JSONObject jsonObject = JSON.parseObject(temp);
            try{
                this.setAccessToken((String) jsonObject.get("access_token"));
            }catch (Exception e){
                System.out.println("请求access_token失败");
            }
        }
        connection.disconnect();// 断开连接
    }


//    /**
//     * 上次任务结束后一分钟后再次执行
//     */
//    @Scheduled(fixedRate = 100)
//    public void task2(){
//        System.out.println("2");
//    }

    public static void setAccessToken(String accessToken) {
        ACCESS_TOKEN = accessToken;
    }


    public static String ConvertStream2Json(InputStream inputStream)
    {
        String jsonStr = "";
        // ByteArrayOutputStream相当于内存输出流
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        // 将输入流转移到内存输出流中
        try
        {
            while ((len = inputStream.read(buffer, 0, buffer.length)) != -1)
            {
                out.write(buffer, 0, len);
            }
            // 将内存流转换为字符串
            jsonStr = new String(out.toByteArray());
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return jsonStr;
    }
}
