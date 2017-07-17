package me.changjie.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.thoughtworks.xstream.XStream;

import me.changjie.common.Constant;
import me.changjie.domain.TextMessage;

/**
 * Created by ChangJie on 2017/7/13.
 */
public class MessageUtil
{

    /**

     <xml>
     <ToUserName><![CDATA[toUser]]></ToUserName>
     <FromUserName><![CDATA[fromUser]]></FromUserName>
     <CreateTime>1348831860</CreateTime>
     <MsgType><![CDATA[text]]></MsgType>
     <Content><![CDATA[this is a test]]></Content>
     <MsgId>1234567890123456</MsgId>
     </xml>

     * xml转Map
     * 需要dom4j依赖
     * @param request
     * @return
     */
    public static Map<String,String> xmlToMap(HttpServletRequest request) throws IOException, DocumentException
    {
        Map<String, String> map = new HashMap<String, String>();

        SAXReader reader = new SAXReader();
        InputStream inputStream = request.getInputStream();
        Document document = reader.read(inputStream);

        Element root = document.getRootElement();
        List<Element> list = root.elements();

        for(Element e : list)
        {
            map.put(e.getName(), e.getText());

        }
        inputStream.close();

        return map;

    }

    /**
     * 文本消息对象转换为xml
     * 需要XStream依赖
     * @param textMessage
     * @return
     */
    public static String textMessageToXml(TextMessage textMessage){
        XStream xStream = new XStream();
        xStream.alias("xml", textMessage.getClass());
        return xStream.toXML(textMessage);
    }

    public static String menuText(){
        StringBuffer sb = new StringBuffer();
        sb.append(Constant.subscribe1);
        sb.append(Constant.subscribe2);
        sb.append(Constant.subscribe3);
        sb.append(Constant.subscribe4);
        return sb.toString();

    }

    public static String initText(String fromUserName, String toUserName, String msgType, String content){
        return initText(fromUserName, toUserName, msgType, content, null);
    }

    public static String initText(String fromUserName, String toUserName, String msgType, String content, String picUrl){
        TextMessage textMessage = new TextMessage();
        textMessage.setFromUserName(toUserName);
        textMessage.setToUserName(fromUserName);
        textMessage.setMsgType(msgType);
        textMessage.setContent(content);
        textMessage.setCreateTime(String.valueOf(new Date().getTime()));
        textMessage.setPicUrl(picUrl);
        return textMessageToXml(textMessage);
    }
}
