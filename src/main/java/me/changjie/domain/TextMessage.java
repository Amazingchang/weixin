package me.changjie.domain;

import java.io.Serializable;

/**
 * Created by ChangJie on 2017/7/13.
 */
public class TextMessage implements Serializable
{
    private static final long serialVersionUID = 8532079440354196018L;

    /*开发者微信号*/
    private String ToUserName;

    /*发送方帐号（一个OpenID） */
    private String FromUserName;

    /*消息创建时间 （整型）*/
    private String CreateTime;

    /*text*/
    private String MsgType;

    /*文本消息内容*/
    private String Content;

    /*图片url*/
    private String PicUrl;

    private String MediaId;


    /*消息id，64位整型*/
    private String MsgId;


    public String getToUserName()
    {
        return ToUserName;
    }

    public void setToUserName(String toUserName)
    {
        ToUserName = toUserName;
    }

    public String getFromUserName()
    {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName)
    {
        FromUserName = fromUserName;
    }

    public String getCreateTime()
    {
        return CreateTime;
    }

    public void setCreateTime(String createTime)
    {
        CreateTime = createTime;
    }

    public String getMsgType()
    {
        return MsgType;
    }

    public void setMsgType(String msgType)
    {
        MsgType = msgType;
    }

    public String getContent()
    {
        return Content;
    }

    public void setContent(String content)
    {
        Content = content;
    }

    public String getMsgId()
    {
        return MsgId;
    }

    public void setMsgId(String msgId)
    {
        MsgId = msgId;
    }

    public String getPicUrl()
    {
        return PicUrl;
    }

    public void setPicUrl(String picUrl)
    {
        PicUrl = picUrl;
    }

    public String getMediaId()
    {
        return MediaId;
    }

    public void setMediaId(String mediaId)
    {
        MediaId = mediaId;
    }

    @Override
    public String toString()
    {
        return "TextMessage{" +
                "ToUserName='" + ToUserName + '\'' +
                ", FromUserName='" + FromUserName + '\'' +
                ", CreateTime='" + CreateTime + '\'' +
                ", MsgType='" + MsgType + '\'' +
                ", Content='" + Content + '\'' +
                ", PicUrl='" + PicUrl + '\'' +
                ", MediaId='" + MediaId + '\'' +
                ", MsgId='" + MsgId + '\'' +
                '}';
    }
}
