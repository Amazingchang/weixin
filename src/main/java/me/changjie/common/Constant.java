package me.changjie.common;

/**
 * Created by ChangJie on 2017/7/13.
 */
public interface Constant
{
    String APPID = "wx05c122195c088198";

    String SECRET = "2558022380ff355f40124ce1ab2d5141";

    String APPID_TEST = "wx8a00b920fa76cf13";

    String SECRET_TEST = "4a3f55aa93b72c28983216c4126f3c46";

    String subscribe0 = "欢迎关注,回复相应数字进行操作\n\n";

    String subscribe1 = "1. 主菜单介绍\n";

    String subscribe2 = "2. 最新文章\n";

    String subscribe3 = "3. 听个歌吧\n\n";

    String subscribe4 = "回复？调出此菜单";

    String subscribe_1 = "1";

    String subscribe_2 = "2";

    String subscribe_3 = "3";

    String subscribe_4 = "?";

    String subscribe_5 = "？";


    String ERROR_MESSAGE = "操作有误,回复相应数字进行操作\n\n";

    String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    String UPLOAD_URL = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";

    String MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

    String QUERY_MENU = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";







}
