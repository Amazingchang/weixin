package me.changjie.domain.menu;

/**
 * Created by ChangJie on 2017/7/19.
 */
public class ClickButton extends BaseButton
{

    /**
     * 菜单KEY值，用于消息接口推送，不超过128字节
     */
    private String key;

    public String getKey()
    {
        return key;
    }

    public void setKey(String key)
    {
        this.key = key;
    }
}
