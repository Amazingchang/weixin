package me.changjie.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

import me.changjie.common.Constant;
import me.changjie.domain.AccessToken;
import me.changjie.util.HttpUtil;
import me.changjie.util.MenuInit;

/**
 * Created by ChangJie on 2017/7/18.
 */
@RestController
public class WeiXinController
{
    @GetMapping(path = "getAccessToken")
    public AccessToken getAccessToken()
    {
        AccessToken accessToken = new AccessToken();
        String url = Constant.ACCESS_TOKEN_URL.replace("APPID", Constant.APPID).replace("APPSECRET", Constant.SECRET);
        try {

            JSONObject jsonObject = HttpUtil.doGetStr(url);

            String access_token = jsonObject.getString("access_token");
            int expires_in = jsonObject.getIntValue("expires_in");

            accessToken.setAccess_token(access_token);
            accessToken.setExpires_in(expires_in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return accessToken;

    }

    @GetMapping(path = "createMenu")
    public Object createMenu() throws IOException
    {
        String url = Constant.MENU_URL.replace("ACCESS_TOKEN", "x0-b-2F2mQcwek-rPMyPOqFcDZ-rABAQJzZdpfkHtglvapDWR8Q0wcMRiU0dYurq8GZLFXI8ViQPn73F3ncwp9z0DOHcirPolsaSARAc1Q30wLr_-HmoN5YG4Mx6Rk1KGQZfAJARBK");
        String menu = JSONObject.toJSONString(MenuInit.initMenu());
        JSONObject jsonObject = HttpUtil.doPostStr(url, menu);
        String errcode = null;
        if(jsonObject != null)
        {
            errcode = jsonObject.getString("errcode");

        }
        return errcode;


    }
}
