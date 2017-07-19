package me.changjie.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;

import me.changjie.common.Constant;
import me.changjie.domain.AccessToken;
import me.changjie.domain.Article;
import me.changjie.util.HttpUtil;
import me.changjie.util.MenuInit;

/**
 * Created by ChangJie on 2017/7/18.
 */
@RestController
public class WeiXinController
{
    @Resource
    private RestTemplate restTemplate;

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private MongoTemplate mongoTemplate;

    @GetMapping(path = "getHistoryArticle")
    public List<Article> getHistoryArticle()
    {
        Query query = new Query();
        //第一个参数从0开始,0表示第一页
        //只排序query.with(new Sort(new Sort.Order(Sort.Direction.DESC,"createdTime")));
        Pageable pageableRequest = new PageRequest(0, 4, new Sort(new Sort.Order(Sort.Direction.DESC,"createdTime")));
        query.with(pageableRequest);
        query.addCriteria(Criteria.where("status").is(0));
        return mongoTemplate.find(query, Article.class);

    }





//    @GetMapping(path = "getAccessToken")
//    public AccessToken getAccessToken()
//    {
//        AccessToken accessToken = new AccessToken();
//        String url = Constant.ACCESS_TOKEN_URL.replace("APPID", Constant.APPID_TEST).replace("APPSECRET", Constant.SECRET_TEST);
//        try {
//
//            JSONObject jsonObject = HttpUtil.doGetStr(url);
//
//            String access_token = jsonObject.getString("access_token");
//            int expires_in = jsonObject.getIntValue("expires_in");
//
//            accessToken.setAccess_token(access_token);
//            accessToken.setExpires_in(expires_in);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return accessToken;
//
//    }

    /**
     * 获取access_token
     * @return
     */
    public String getToken()
    {
        String access_token = null;
        long expires_in = 0;

        Boolean isHave = redisTemplate.hasKey("access_token");
        if(isHave)
        {
            access_token = (String) redisTemplate.opsForValue().get("access_token");
        }
        else
        {
            Map<String,Object> map = new HashMap<>();
            map.put("APPID", Constant.APPID_TEST);
            map.put("APPSECRET", Constant.SECRET_TEST);
            ResponseEntity responseEntity = restTemplate.getForEntity("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={APPID}&secret={APPSECRET}", Object.class, map);
            if(HttpStatus.OK.equals(responseEntity.getStatusCode()))
            {
                LinkedHashMap<String,Object> linkedHashMap = (LinkedHashMap) responseEntity.getBody();
                access_token = (String)linkedHashMap.get("access_token");
                expires_in = (int)linkedHashMap.get("expires_in");
                redisTemplate.opsForValue().set("access_token", access_token, expires_in, TimeUnit.SECONDS);
            }

        }
        return access_token;

    }

    /**
     * 创建自定义菜单
     * @return
     * @throws IOException
     */
    @GetMapping(path = "createMenu")
    public Object createMenu() throws IOException
    {
        String token = getToken();

        String url = Constant.MENU_URL.replace("ACCESS_TOKEN", token);
        String menu = JSONObject.toJSONString(MenuInit.initMenu());
        JSONObject jsonObject = HttpUtil.doPostStr(url, menu);
        String errcode = null;
        if(jsonObject != null)
        {
            errcode = jsonObject.getString("errcode");
            if("0".equals(errcode))
            {
                System.out.println("菜单创建成功");
            }
            else
            {
                System.out.println("错误码:" + errcode);

            }

        }
        return errcode;
    }

    @GetMapping(path = "queryMenu")
    public AccessToken queryMenu()
    {
        AccessToken accessToken = new AccessToken();
        String url = Constant.QUERY_MENU.replace("ACCESS_TOKEN", "BmEh8jUYdRbabtZv0gq8-gzTzmhITVNvZqBzNePNUeFo6udZG9We32VPceWn1D6LgGnCRavlCNPbHuetwI7ipt6742MMTG57T1-Zvske4lZ0fEvXSUSeTlExp3LnDsLWUBRhABAPWM");
        try {

            JSONObject jsonObject = HttpUtil.doGetStr(url);
            System.out.println(jsonObject.toJSONString());

//            String access_token = jsonObject.getString("access_token");
//            int expires_in = jsonObject.getIntValue("expires_in");
//
//            accessToken.setAccess_token(access_token);
//            accessToken.setExpires_in(expires_in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return accessToken;

    }
}
