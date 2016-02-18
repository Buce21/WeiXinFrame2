package me.test.weixin.controller;

import com.sun.istack.internal.logging.Logger;
import me.test.weixin.utils.weixinutils.FormatXmlProcess;
import me.test.weixin.utils.weixinutils.ReceiveXmlEntity;
import me.test.weixin.utils.weixinutils.ReceiveXmlProcess;
import me.test.weixin.utils.weixinutils.message.Article;
import me.test.weixin.utils.weixinutils.message.NewsMessage;
import org.aspectj.weaver.ast.Test;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZhaoTao on 2016/2/18.
 */
@Controller
@RequestMapping("/center")
@Scope("prototype")
public class WeiXinCenterController {
    private static String TOKEN = "suibian";
    private static Logger logger = Logger.getLogger(Test.class);
    private ReceiveXmlEntity xmlMsg;    //xml对象
    private ReceiveXmlProcess receiveXmlProcess = new ReceiveXmlProcess();  //解析xml为对象
    private FormatXmlProcess formXML ;
    String result = new String();
    /**
     * 验证
     */

    @RequestMapping(value="/checkSignatureFirst",produces = "application/json; charset=utf-8")
    public @ResponseBody
    String checkSignatureFirst(HttpServletRequest request) throws IOException {

        if(request.getAttribute("echostr")!=null){
                return (String) request.getAttribute("echostr");
        }else{
            /** 读取接收到的xml消息 */
            StringBuffer sb = new StringBuffer();
            InputStream is = request.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String s = "";
            while ((s = br.readLine()) != null) {
                sb.append(s);
            }
            String xml = sb.toString(); //次即为接收到微信端发送过来的xml数据


            /**------------------------------------**/
            xmlMsg =  receiveXmlProcess.getMsgEntity(xml);

            NewsMessage newMsg = new NewsMessage();
            newMsg.setArticleCount("2");
            newMsg.setCreateTime(new Long(0));
            newMsg.setFromUserName(xmlMsg.getToUserName());
            newMsg.setMsgType("news");
            newMsg.setToUserName(xmlMsg.getFromUserName());

            List<Article> list = new ArrayList<Article>();

            Article article = new Article();
            article.setDescription("baidu");
            article.setPicUrl("http://pic1a.nipic.com/2008-12-04/2008124215522671_2.jpg");
            article.setTitle("你好");
            article.setUrl("www.baidu.com");

            Article article2 = new Article();
            article2.setDescription("163");
            article2.setPicUrl("http://img.taopic.com/uploads/allimg/130501/240451-13050106450911.jpg");
            article2.setTitle("不好");
            article2.setUrl("http://www.163.com/");

            list.add(article);
            list.add(article2);

            newMsg.setArticles(list);


            try {
                String result = formXML.formatXmlAnswer(newMsg);
            } catch (Exception e) {
                result = "";
                e.printStackTrace();
            }
            return result;
        }

}
}
