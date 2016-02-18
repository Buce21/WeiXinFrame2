package me.test.weixin.utils.weixinutils;

/**
 * Created by ZhaoTao on 2015/10/19.
 */
public  class AccessToken {
    private static String ACCESS_TOKEN = "";
    private static String  createTime;  //从服务器获取access_token的时间
    private String pingTime = "7200"; //凭证时间

    public static String getAccessToken() {
        if(ACCESS_TOKEN.equals("")){

        }
        return ACCESS_TOKEN;
    }



}
