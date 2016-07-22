package me.test.weixin.service;

import me.test.weixin.mapper.UsersMapper;
import me.test.weixin.utils.weixinutils.ReceiveXmlEntity;
import me.test.weixin.utils.weixinutils.message.Article;
import me.test.weixin.utils.weixinutils.message.Message;
import me.test.weixin.utils.weixinutils.message.NewsMessage;
import me.test.weixin.utils.weixinutils.message.TextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZhaoTao on 2016/2/19.
 */
@Service
public class WeiXinCenterServiceImpl implements IWeiXinCenterService {
    @Autowired
    private UsersMapper usersMapper;

    public Message textResponse(ReceiveXmlEntity xmlEntity) {
        String msgContent = xmlEntity.getContent();        //接收的信息
        Message msg = null;


        switch (msgContent){
            case "hello":{
                TextMessage textMessage = new TextMessage();
                textMessage.setToUserName(xmlEntity.getFromUserName());
                textMessage.setFromUserName(xmlEntity.getToUserName());
                textMessage.setCreateTime(new Long(0));
                textMessage.setMsgType("text");
                textMessage.setContent("hello");
                msg = textMessage;
                break;
            }
            case "1":{
            NewsMessage newMsg = new NewsMessage();
            newMsg.setArticleCount("2");
            newMsg.setCreateTime(new Long(0));
            newMsg.setFromUserName(xmlEntity.getToUserName());
            newMsg.setMsgType("news");
            newMsg.setToUserName(xmlEntity.getFromUserName());

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
            msg = newMsg;
             break;
            }
            default:{
                    TextMessage textMessage = new TextMessage();
                    textMessage.setToUserName(xmlEntity.getFromUserName());
                    textMessage.setFromUserName(xmlEntity.getToUserName());
                    textMessage.setCreateTime(new Long(0));
                    textMessage.setMsgType("text");
                    textMessage.setContent("欢迎访问!!!");
                    msg = textMessage;
                }
            }


        return msg;
    }

}
