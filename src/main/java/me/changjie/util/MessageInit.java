package me.changjie.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import me.changjie.common.MessageType;
import me.changjie.domain.*;

/**
 * Created by ChangJie on 2017/7/18.
 */
public class MessageInit
{
    /**
     * 文本消息初始化
     * @param fromUserName
     * @param toUserName
     * @param content
     * @return
     */
    public static String initText(String fromUserName, String toUserName, String content){
        TextMessage textMessage = new TextMessage();
        textMessage.setFromUserName(fromUserName);
        textMessage.setToUserName(toUserName);
        textMessage.setMsgType(MessageType.TEXT);
        textMessage.setContent(content);
        textMessage.setCreateTime(String.valueOf(new Date().getTime()));
        return ConvertUtil.textMessageToXml(textMessage);
    }

    /**
     * 图片消息初始化
     * @param fromUserName
     * @param toUserName
     * @param mediaId
     * @return
     */
    public static String initPicture(String fromUserName, String toUserName, String mediaId){
        ImageMessage imageMessage = new ImageMessage();
        imageMessage.setFromUserName(fromUserName);
        imageMessage.setToUserName(toUserName);
        imageMessage.setMsgType(MessageType.IMAGE);
        imageMessage.setCreateTime(String.valueOf(new Date().getTime()));

        Image image = new Image();
        image.setMediaId(mediaId);
        imageMessage.setImage(image);
        return ConvertUtil.imageMessageToXml(imageMessage);
    }

    /**
     * 图文消息初始化
     * @param fromUserName
     * @param toUserName
     * @return
     */
    public static String initNewsMessage(String fromUserName, String toUserName){
        NewsMessage newsMessage = new NewsMessage();
        newsMessage.setFromUserName(fromUserName);
        newsMessage.setToUserName(toUserName);
        newsMessage.setMsgType(MessageType.NEWS);
        newsMessage.setCreateTime(String.valueOf(new Date().getTime()));


        List<News> articles = new ArrayList<News>();
        for(int i=0; i<4; i++)
        {
            News news = new News();
            news.setTitle("慕课网介绍");
            news.setDescription("慕课网描述");
            news.setPicUrl("http://mmbiz.qpic.cn/mmbiz_jpg/fdqCXpnBKk2gJRE9q56Vk26OUr28DNhUUgr4BCodjU8rGgksw1eic8Lcwwds04WicBqEDJQc3FLxR7wVLHetJKOQ/0");
            news.setUrl("www.changjie.me");
            articles.add(news);
        }

        newsMessage.setArticles(articles);
        newsMessage.setArticleCount(articles.size());
        return ConvertUtil.newsMessageToXml(newsMessage);
    }
}
