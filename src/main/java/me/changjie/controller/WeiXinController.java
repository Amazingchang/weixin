package me.changjie.controller;

import com.alibaba.fastjson.JSONObject;
import me.changjie.common.Constant;
import me.changjie.domain.AccessToken;
import me.changjie.util.HttpUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

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
}
