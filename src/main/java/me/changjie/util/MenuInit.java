package me.changjie.util;

import me.changjie.domain.menu.BaseButton;
import me.changjie.domain.menu.ClickButton;
import me.changjie.domain.menu.Menu;
import me.changjie.domain.menu.ViewButton;

/**
 * Created by ChangJie on 2017/7/19.
 */
public class MenuInit
{

    /**
     * 菜单组装
     * @return
     */
    public static Menu initMenu()
    {
        Menu menu = new Menu();

        ClickButton button11 = new ClickButton();
        button11.setName("click菜单");
        button11.setType("click");
        button11.setKey("11");

        ViewButton button21 = new ViewButton();
        button21.setName("view菜单");
        button21.setType("view");
        button21.setUrl("http://www.changjie.me");

        ClickButton button31 = new ClickButton();
        button31.setName("扫码菜单");
        button31.setType("scancode_push");
        button31.setKey("31");

        ClickButton button32 = new ClickButton();
        button32.setName("地理位置菜单");
        button32.setType("location_select");
        button32.setKey("32");

        BaseButton baseButton = new BaseButton();
        baseButton.setName("菜单");
        baseButton.setSub_Base_button(new BaseButton[]{button31,button32});

        menu.setButton(new BaseButton[]{button11, button21, baseButton});
        return menu;

    }
}
