package me.changjie.util;

import me.changjie.common.Constant;

/**
 * Created by ChangJie on 2017/7/13.
 */
public class MessageUtil
{

    public static String menuText(){
        StringBuffer sb = new StringBuffer();
        sb.append(Constant.subscribe0);
        sb.append(Constant.subscribe1);
        sb.append(Constant.subscribe2);
        sb.append(Constant.subscribe3);
        sb.append(Constant.subscribe4);
        return sb.toString();

    }

}
