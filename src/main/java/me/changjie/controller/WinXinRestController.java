package me.changjie.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import me.changjie.common.Constant;

/**
 * Created by ChangJie on 2017/7/18.
 */
@RestController
public class WinXinRestController
{
    @Resource
    private RestTemplate restTemplate;

    @GetMapping(path = "/getAccessToken")
    public Object getAccessToken()
    {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("APPID", Constant.APPID);
        map.put("APPSECRET", Constant.SECRET);
        return restTemplate.getForEntity("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={APPID}&secret={APPSECRET}", Object.class, map);

    }

    @GetMapping(path = "/getWeiXinServerIp")
    public Object getWeiXinServerIp()
    {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("ACCESS_TOKEN", "3eSMV9EvEardiVlLht0qp5AzRRlcM-V7zjIk_idpaBBV8kHs1FtOW4q0d7fv0uaMyd_PCgAVn33UlhnG9pImGV5o6NcPzacPUgqh9LM4WBDSCHZIitUqhLC6l3qXFKHSRFDbAIAHVT");
        return restTemplate.getForEntity("https://api.weixin.qq.com/cgi-bin/getcallbackip?access_token={ACCESS_TOKEN}", Object.class, map);

    }


}
