package me.changjie.servlet;

import java.io.IOException;
import java.io.Writer;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import me.changjie.common.Constant;
import me.changjie.common.MessageType;
import me.changjie.controller.WeiXinController;
import me.changjie.domain.Article;
import me.changjie.util.ConvertUtil;
import me.changjie.util.MessageInit;
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
            Map<String,String> map = ConvertUtil.xmlToMap(req);
            String fromUserName = map.get("FromUserName");
            String toUserName = map.get("ToUserName");
            String msgType = map.get("MsgType");

            //文本消息
            if(MessageType.TEXT.equals(msgType)){
                String content = map.get("Content");
                //内容为1、?、？调出主菜单
                if(Constant.subscribe_1.equals(content) || Constant.subscribe_4.equals(content) || Constant.subscribe_5.equals(content))
                {
                    message = MessageInit.initText(toUserName, fromUserName, MessageUtil.menuText());
                }
                //内容为2 回复最新文章
                else if(Constant.subscribe_2.equals(content))
                {
                    List<Article> list = getHistoryArticle(req, 1);
                    message = MessageInit.initHistoryNewsMessage(toUserName, fromUserName, list);
                }
                //内容为3 返回音乐
                else if(Constant.subscribe_3.equals(content))
                {
                    message = MessageInit.initMusicMessage(toUserName, fromUserName);
                }
                //内容为其他
                else
                {
                    StringBuffer sb = new StringBuffer();
                    sb.append(Constant.ERROR_MESSAGE);
                    sb.append(Constant.subscribe1);
                    sb.append(Constant.subscribe2);
                    sb.append(Constant.subscribe3);
                    sb.append(Constant.subscribe4);
                    message = MessageInit.initText(toUserName, fromUserName, sb.toString());
                }
            }
            //图片消息
            else if(MessageType.IMAGE.equals(msgType))
            {
                String mediaId = map.get("MediaId");
                message = MessageInit.initPicture(toUserName, fromUserName, mediaId);
            }
            //事件
            else if(MessageType.EVENT.equals(msgType))
            {
                String eventType = map.get("Event");
                //关注
                if(MessageType.SUBSCRIBE.equals(eventType))
                {
                    message = MessageInit.initText(toUserName, fromUserName, MessageUtil.menuText());
                }
                //click 菜单
                else if(MessageType.CLICK.equals(eventType))
                {
                    String EventKey = map.get("EventKey");
                    //点击主菜单
                    if("1".equals(EventKey))
                    {
                        message = MessageInit.initText(toUserName, fromUserName, MessageUtil.menuText());
                    }
                    //历史文章
                    else if("21".equals(EventKey))
                    {
                        List<Article> list = getHistoryArticle(req, 4);
                        message = MessageInit.initHistoryNewsMessage(toUserName, fromUserName, list);
                    }
                    //关于我
                    else if("22".equals(EventKey))
                    {
                        StringBuffer sb = new StringBuffer();
                        sb.append("93年的java小菜鸡\n");
                        message = MessageInit.initText(toUserName, fromUserName, sb.toString());
                    }
                }
                //view 菜单
                else if(MessageType.VIEW.equals(eventType))
                {
                    String url = map.get("EventKey");
                    message = MessageInit.initText(toUserName, fromUserName, url);
                }
                //扫码 菜单
                else if(MessageType.SCANCODE_PUSH.equals(eventType))
                {
                    String key = map.get("EventKey");
                    message = MessageInit.initText(toUserName, fromUserName, key);
                }
            }
            else if(MessageType.LOCATION.equals(msgType))
            {
                String Label = map.get("Label");//西湖风景名胜区
                String Location_X = map.get("Location_X");//30.230524
                String Location_Y = map.get("Location_Y");//120.069588
                String Scale = map.get("Scale");//16
                message = MessageInit.initText(toUserName, fromUserName, Label);
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
     *
     * @return
     */
    public List<Article> getHistoryArticle(HttpServletRequest request, Integer count)
    {
        ServletContext servletContext = request.getServletContext();
        ApplicationContext ac1 = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        WeiXinController weiXinController = (WeiXinController) ac1.getBean("weiXinController");

//        ApplicationContext ac2 = WebApplicationContextUtils.getWebApplicationContext(ServletContext sc);
//        ac2.getBean("beanId");

        return weiXinController.getHistoryArticle(count);


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
