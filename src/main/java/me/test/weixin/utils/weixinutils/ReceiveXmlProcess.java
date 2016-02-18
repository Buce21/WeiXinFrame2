package me.test.weixin.utils.weixinutils;

/**
 * Created by ZhaoTao on 2015/10/13.
 * 解析xml
 */
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Iterator;
public class ReceiveXmlProcess {


    public  ReceiveXmlEntity getMsgEntity(String strXml){
        ReceiveXmlEntity msg = null;
        try {
            if (strXml.length() <= 0 || strXml == null)
                return null;

            // ���ַ���ת��ΪXML�ĵ�����
            Document document = DocumentHelper.parseText(strXml);
            // ����ĵ��ĸ��ڵ�
            Element root = document.getRootElement();
            // �������ڵ��������ӽڵ�
            Iterator<?> iter = root.elementIterator();

            // �������н��
            msg = new ReceiveXmlEntity();
            //���÷�����ƣ�����set����
            //��ȡ��ʵ���Ԫ����
            Class<?> c = Class.forName("me.test.weixin.utils.weixinutils.ReceiveXmlEntity");
            msg = (ReceiveXmlEntity)c.newInstance();//�������ʵ��Ķ���

            while(iter.hasNext()){
                Element ele = (Element)iter.next();
                //��ȡset�����еĲ����ֶΣ�ʵ��������ԣ�
                Field field = c.getDeclaredField(ele.getName());
                //��ȡset������field.getType())��ȡ���Ĳ�����������
                Method method = c.getDeclaredMethod("set"+ele.getName(), field.getType());
                //����set����
                method.invoke(msg, ele.getText());
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return msg;
    }
}
