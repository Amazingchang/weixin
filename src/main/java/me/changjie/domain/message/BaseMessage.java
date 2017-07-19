package me.changjie.domain.message;

import java.io.Serializable;

/**
 * Created by ChangJie on 2017/7/18.
 */
public class BaseMessage implements Serializable
{
    private static final long serialVersionUID = -3095691001133456874L;

    /*发送方帐号（一个OpenID） */
    private String FromUserName;

    /*接收方微信号*/
    private String ToUserName;

    /*消息创建时间 （整型）*/
    private String CreateTime;

    /*text*/
    private String MsgType;

    public String getFromUserName()
    {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName)
    {
        FromUserName = fromUserName;
    }

    public String getToUserName()
    {
        return ToUserName;
    }

    public void setToUserName(String toUserName)
    {
        ToUserName = toUserName;
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
}
