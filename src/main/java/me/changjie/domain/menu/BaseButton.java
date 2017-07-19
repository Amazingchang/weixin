package me.changjie.domain.menu;

/**
 * Created by ChangJie on 2017/7/19.
 */
public class BaseButton
{

    /**
     * 菜单的响应动作类型，view表示网页类型，click表示点击类型，miniprogram表示小程序类型
     */
    private String type;

    /**
     * 菜单标题，不超过16个字节，子菜单不超过60个字节
     */
    private String name;

    /**
     * 二级菜单数组，个数应为1~5个
     */
    private BaseButton[] sub_Base_button;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public BaseButton[] getSub_Base_button()
    {
        return sub_Base_button;
    }

    public void setSub_Base_button(BaseButton[] sub_Base_button)
    {
        this.sub_Base_button = sub_Base_button;
    }

    public String getType()
    {

        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }
}
