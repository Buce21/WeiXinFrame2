package me.test.weixin.service;

import me.test.weixin.utils.weixinutils.ReceiveXmlEntity;
import me.test.weixin.utils.weixinutils.message.Message;

/**
 * Created by ZhaoTao on 2016/2/19.
 */
public interface IWeiXinCenterService {
    /**
     * 文本消息处理
     * @param xmlEntity
     * @return
     */
    Message textResponse(ReceiveXmlEntity xmlEntity);
}
