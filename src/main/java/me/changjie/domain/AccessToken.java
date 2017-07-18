package me.changjie.domain;

/**
 * Created by ChangJie on 2017/7/18.
 */
public class AccessToken
{

    private String access_token;

    /**
     * 凭证有效时间，单位：秒
     */
    private int expires_in;

    public String getAccess_token()
    {
        return access_token;
    }

    public void setAccess_token(String access_token)
    {
        this.access_token = access_token;
    }
}
