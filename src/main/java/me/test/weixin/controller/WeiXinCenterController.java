package me.test.weixin.controller;

import com.sun.istack.internal.logging.Logger;
import me.test.weixin.service.IWeiXinCenterService;
import me.test.weixin.utils.weixinutils.FormatXmlProcess;
import me.test.weixin.utils.weixinutils.ReceiveXmlEntity;
import me.test.weixin.utils.weixinutils.ReceiveXmlProcess;
import me.test.weixin.utils.weixinutils.message.Message;
import org.aspectj.weaver.ast.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;

/**
 * Created by ZhaoTao on 2016/2/18.
 */
@Controller
@RequestMapping("/center")
@Scope("prototype")
public class WeiXinCenterController {

    @Autowired
    private IWeiXinCenterService weiXinCenterService;


    private static String TOKEN = "suibian";
    private static Logger logger = Logger.getLogger(Test.class);
    private ReceiveXmlEntity xmlMsg;    //xml实体对象
    private ReceiveXmlProcess receiveXmlProcess = new ReceiveXmlProcess();  //解析xml为对象
    private FormatXmlProcess formXML =new FormatXmlProcess();   //将实体对象封装为xml

    String result = new String();
    /**
     * 验证
     */

    @RequestMapping(value="/checkSignatureFirst",produces = "application/json; charset=utf-8")
    public @ResponseBody
    String checkSignatureFirst(HttpServletRequest request) throws IOException {
            logger.info("---------Begin----------");
            /** 读取接收到的xml消息 */
            StringBuffer sb = new StringBuffer();
            InputStream is = request.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String s;
            while ((s = br.readLine()) != null) {
                sb.append(s);
            }
            String xml = sb.toString(); //微信端发送过来的xml数据

            xmlMsg =  receiveXmlProcess.getMsgEntity(xml);  //xml-->实体对象
            logger.info("接收信息为:"+xmlMsg.toString());

            /**---------------根据消息类型分发任务-------------------**/
            Message message = null;    //返回值
            switch (xmlMsg.getMsgType()){
                case "text":
                    try {
                        message = weiXinCenterService.textResponse(xmlMsg);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                //// TODO: 2016/2/22  其它类型任务
            }

            //消息类-->XML
            try {
                result = formXML.formatXmlAnswer(message);
            } catch (Exception e) {
                result = "<xml><ToUserName><![CDATA[" +
                        xmlMsg.getFromUserName() +
                        "]]></ToUserName><FromUserName><![CDATA[" +
                        xmlMsg.getToUserName() +
                        "]]></FromUserName><CreateTime>" +
                        new Date().getTime() +
                        "</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[出现未知错误=.=]]></Content></xml>";   //异常返回结果
                e.printStackTrace();
            }
            logger.info("返回消息为:"+result);
            return result;
        }

}

