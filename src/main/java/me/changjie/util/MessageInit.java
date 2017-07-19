package me.changjie.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import me.changjie.common.MessageType;
import me.changjie.domain.Article;
import me.changjie.domain.message.*;

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

    /**
     * 历史图文
     * @param fromUserName
     * @param toUserName
     * @param list
     * @return
     */
    public static String initHistoryNewsMessage(String fromUserName, String toUserName, List<Article> list){
        NewsMessage newsMessage = new NewsMessage();
        newsMessage.setFromUserName(fromUserName);
        newsMessage.setToUserName(toUserName);
        newsMessage.setMsgType(MessageType.NEWS);
        newsMessage.setCreateTime(String.valueOf(new Date().getTime()));


        List<News> articles = new ArrayList<News>();
        for(Article article : list)
        {
            News news = new News();
            news.setTitle(article.getTitle());
            news.setDescription(article.getCategory());
            news.setPicUrl("http://mmbiz.qpic.cn/mmbiz_jpg/fdqCXpnBKk2gJRE9q56Vk26OUr28DNhUUgr4BCodjU8rGgksw1eic8Lcwwds04WicBqEDJQc3FLxR7wVLHetJKOQ/0");
            news.setUrl("www.changjie.me/detail/" + article.getId());
            articles.add(news);
        }

        newsMessage.setArticles(articles);
        newsMessage.setArticleCount(articles.size());
        return ConvertUtil.newsMessageToXml(newsMessage);
    }


    /**
     * 音乐消息初始化
     * @param fromUserName
     * @param toUserName
     * @return
     */
    public static String initMusicMessage(String fromUserName, String toUserName){
        MusicMessage musicMessage = new MusicMessage();
        musicMessage.setFromUserName(fromUserName);
        musicMessage.setToUserName(toUserName);
        musicMessage.setMsgType(MessageType.MUSIC);
        musicMessage.setCreateTime(String.valueOf(new Date().getTime()));

        Music music = new Music();
        music.setTitle("天空之城");
        music.setDescription("飞机飞过天空");
        music.setMusicUrl("http://zvkzs5.natappfree.cc/mp3/lizhi.mp3");
        music.setHQMusicUrl("http://zvkzs5.natappfree.cc/mp3/lizhi.mp3");
        music.setThumbMediaId("nVXjcFy4c_49Ur_8mxKzkb3YA4sb0ueWnx608Psu5idtETjEWZZKalys_byEn2Kf");

        musicMessage.setMusic(music);


        return ConvertUtil.musicMessageToXml(musicMessage);
    }
}
