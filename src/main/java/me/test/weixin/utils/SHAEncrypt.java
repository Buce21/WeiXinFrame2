package me.test.weixin.utils;

import java.security.MessageDigest;

/**
 * Created by ZhaoTao on 2015/10/12.
 */
public class SHAEncrypt {
    /**
     * SHA1加密主程�?
     * @param s
     * @return
     * @throws Exception
     */
    public final static String stringToSHA1(String s) {
        String retStr = "";
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f','g','h','i','j','k','l','m','n',
                'o','p','q','r','s','t','u','v','w','x','y','z','A','B','C',
                'D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R',
                'S','T','U','V','W','X','Y','Z','~','`','!','@','#','$','%',
                '^','&','*','(',')','{','}','[',']','|','\\','"','\'',':'
                ,';','<','>',',','.','?','/',' '};
        try {
            byte[] strTemp = s.getBytes();
            //
            MessageDigest sha1Temp = MessageDigest.getInstance("SHA-1");
            sha1Temp.update(strTemp);
            byte[] md = sha1Temp.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte b = md[i];
                //
                str[k++] = hexDigits[b >> 4 & 0xf];
                str[k++] = hexDigits[b & 0xf];
            }
            retStr = new String(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retStr ;
    }
    public static void main(String[] args) {
        try {
            System.out.println(stringToSHA1("zhaotao"));
            System.out.println("32位---"+stringToSHA1("zhaotao").substring(0,32));
            System.out.println("43位---"+stringToSHA1("zhaotao")+"123");
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
