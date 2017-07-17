package me.changjie.servlet;

import java.io.IOException;
import java.io.Writer;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentException;

import me.changjie.common.Constant;
import me.changjie.common.MessageType;
import me.changjie.util.MessageUtil;


/**
 * Created by ChangJie on 2017/7/13.
 */
@WebServlet(urlPatterns = "/weixinServlet")
public class WeiXinServlet extends HttpServlet
{

    private final static String token = "changjie";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        Writer writer = resp.getWriter();
        String signature = req.getParameter("signature");
        String timestamp = req.getParameter("timestamp");
        String nonce = req.getParameter("nonce");
        String echostr = req.getParameter("echostr");

        String[] strings = new String[]{token,timestamp,nonce};

        Arrays.sort(strings);

        StringBuffer sb = new StringBuffer();
        for(String s : strings){
            sb.append(s);
        }

        //sha1加密
        String temp = getSha1(sb.toString());

        if(signature.equals(temp))
        {
            writer.write(echostr);
        }
        writer.close();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        Writer writer = resp.getWriter();

        //回复内容
        String message = null;
        try
        {
            Map<String,String> map = MessageUtil.xmlToMap(req);
            String fromUserName = map.get("FromUserName");
            String toUserName = map.get("ToUserName");
            String msgType = map.get("MsgType");
            String content = map.get("Content");
            String picUrl = map.get("PicUrl");


            //文本消息
            if(MessageType.MESSAGE_TEXT.equals(msgType)){
                //内容为1
                if(Constant.subscribe_1.equals(content))
                {
                    message = MessageUtil.initText(fromUserName, toUserName, MessageType.MESSAGE_TEXT, Constant.menu_1);
                }
                //内容为2
                else if(Constant.subscribe_2.equals(content))
                {
                    message = MessageUtil.initText(fromUserName, toUserName, MessageType.MESSAGE_TEXT, Constant.menu_2);
                }
                //内容为?或？
                else if(Constant.subscribe_3.equals(content) || Constant.subscribe_4.equals(content))
                {
                    message = MessageUtil.initText(fromUserName, toUserName, MessageType.MESSAGE_TEXT, MessageUtil.menuText());
                }
                //内容为其他
                else
                {
                    message = MessageUtil.initText(fromUserName, toUserName, MessageType.MESSAGE_TEXT, content);
                }
            }
            //图片消息
            else if(MessageType.MESSAGE_IMAGE.equals(msgType))
            {
                message = MessageUtil.initText(fromUserName, toUserName, MessageType.MESSAGE_IMAGE, content, picUrl);
            }
            else if(MessageType.MESSAGE_EVENT.equals(msgType))
            {
                String eventType = map.get("Event");
                //关注
                if(MessageType.MESSAGE_SUBSCRIBE.equals(eventType))
                {
                    message = MessageUtil.initText(fromUserName, toUserName, MessageType.MESSAGE_TEXT, MessageUtil.menuText());
                }

            }

            System.out.println(message);
            writer.write(message);

        }
        catch (DocumentException e)
        {
            e.printStackTrace();
        }finally
        {
            writer.close();
        }

    }

    /**
     * sha1加密
     * @param str
     * @return
     */
    public String getSha1(String str ) {
        if (str == null || str.length() == 0) {
            return null ;
        }
        char hexDigits [] = { '0' , '1' , '2' , '3' , '4', '5' , '6' , '7' , '8' , '9' ,
                'a', 'b', 'c', 'd', 'e', 'f' };
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update( str.getBytes( "UTF-8"));
            byte[] md = mdTemp .digest();
            int j = md .length ;
            char buf [] = new char[ j * 2];
            int k = 0;
            for (int i = 0; i < j ; i ++) {
                byte byte0 = md [i ];
                buf[ k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[ k++] = hexDigits[byte0 & 0xf];
            }
            return new String(buf );
        } catch (Exception e ) {
            return null ;
        }
    }

}
