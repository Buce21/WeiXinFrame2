package me.test.weixin.utils.managerutils;

import me.test.weixin.service.AccessToken;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by ZhaoTao on 2016/3/15.
 */
public class HttpUtil {

    public static String openUrl(String u,String json) throws IOException {
        URL url = new URL(u);    // 把字符串转换为URL请求地址
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();// 打开连接
        connection.connect();// 连接会话

        if(null!=json||!json.equals("")){

        }
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-type","application/json");
        connection.setDoOutput(true);
        OutputStream ou=connection.getOutputStream();
        ou.write(json.getBytes("utf8"));
        ou.flush();
        ou.close();
        int respCode = connection.getResponseCode();
        String temp = null;
        if (respCode == 200){
             temp = AccessToken.ConvertStream2Json(connection.getInputStream());
             System.out.println(temp);
        }
        return temp;
    }

    public static void main(String[] args) throws IOException {
        HttpUtil.openUrl("https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token="+AccessToken.getAccessToken(),"{\n" +
                "    \"touser\":\"OPENID\",\n" +
                "    \"msgtype\":\"text\",\n" +
                "    \"text\":\n" +
                "    {\n" +
                "         \"content\":\"Hello World\"\n" +
                "    }\n" +
                "}");
    }

}
