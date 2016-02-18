package me.test.weixin.utils.weixinutils;

/**
 * Created by ZhaoTao on 2015/10/13.
 */

import me.test.weixin.utils.weixinutils.message.Article;
import me.test.weixin.utils.weixinutils.message.Music;
import me.test.weixin.utils.weixinutils.message.Voice;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;


/**
 * 封装最终的xml格式结果
 *
 */
public class FormatXmlProcess {

    /**
     *
     * @param xmlEntity
     * @return  xml格式结果 String
     * @throws Exception
     */
    public String formatXmlAnswer(Object xmlEntity) throws  Exception {
        StringBuffer sb = new StringBuffer();

        sb.append("<xml>");
        sb.append(formatOBJtoSB(xmlEntity));
        sb.append("</xml>");
        System.out.println("return:"+sb.toString());
        return sb.toString();
    }

    @SuppressWarnings("unchecked")
    public StringBuffer formatOBJtoSB(Object xmlEntity)throws  Exception{
        StringBuffer sb = new StringBuffer();
        String name = new String();
        String value = new String();
        String type = new String();
        Date date = new Date();
        Field[] field = xmlEntity.getClass().getDeclaredFields();        //获取实体类的所有属性，返回Field数组
        for(int j=0 ; j<field.length ; j++){     //遍历所有属性
            name = field[j].getName();    //获取属性的名字
            type = field[j].getGenericType().toString();    //获取属性的类型

            name = name.substring(0,1).toUpperCase()+name.substring(1); //将属性的首字符大写，方便构造get，set方法

            Method m = xmlEntity.getClass().getMethod("get"+name);
            if(type.equals("long")&& name.equals("CreateTime")){        //时间参数
                sb.append("<CreateTime>"+date.getTime()+"</CreateTime>");
            }else if(type.equals("java.util.List<zhaotao.util.messageResp.Articel>")){    //图文
                List<Article> articles =  (List<Article>) m.invoke(xmlEntity);
                sb.append("<Articles>");
                for(Article article:articles){
                    sb.append("<item>");
                    sb.append(formatOBJtoSB(article));
                    sb.append("</item>");
                }
                sb.append("</Articles>");
            }else if(type.equals("class java.lang.String")){        //String
                value = (String) m.invoke(xmlEntity);
                sb.append("<"+name+"><![CDATA["+value+"]]></"+name+">");
            }else if(type.equals("zhaotao.util.messageResp.Music")){    //音乐
               Music music = (Music) m.invoke(xmlEntity);
                sb.append("<Music>");
                sb.append(formatOBJtoSB(music));
                sb.append("</Music>");
            }else if(type.equals("zhaotao.util.messageResp.Voice")){    //声音
                Voice voice = (Voice) m.invoke(xmlEntity);
                sb.append("<Voice>");
                sb.append(formatOBJtoSB(voice));
                sb.append("</Voice>");
            }
        }

        return sb;
    }
}