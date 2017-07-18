package me.changjie.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.thoughtworks.xstream.XStream;

import me.changjie.domain.ImageMessage;
import me.changjie.domain.News;
import me.changjie.domain.NewsMessage;
import me.changjie.domain.TextMessage;

/**
 * Created by ChangJie on 2017/7/18.
 */
public class ConvertUtil
{
    /**
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

    /**
     * 图片消息对象转换为xml
     * 需要XStream依赖
     * @param imageMessage
     * @return
     */
    public static String imageMessageToXml(ImageMessage imageMessage){
        XStream xStream = new XStream();
        xStream.alias("xml", imageMessage.getClass());
        return xStream.toXML(imageMessage);
    }

    /**
     * 图文消息对象转换为xml
     * 需要XStream依赖
     * @param newsMessage
     * @return
     */
    public static String newsMessageToXml(NewsMessage newsMessage){
        XStream xStream = new XStream();
        xStream.alias("xml", newsMessage.getClass());
        xStream.alias("item", News.class);
        return xStream.toXML(newsMessage);
    }
}
