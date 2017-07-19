package me.changjie.util;

import me.changjie.domain.menu.Button;
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

        ClickButton button1 = new ClickButton();
        button1.setName("主菜单");
        button1.setType("click");
        button1.setKey("1");


        ClickButton menu2_1 = new ClickButton();
        menu2_1.setName("历史文章");
        menu2_1.setType("click");
        menu2_1.setKey("21");

        ClickButton menu2_2 = new ClickButton();
        menu2_2.setName("关于我");
        menu2_2.setType("click");
        menu2_2.setKey("22");

        ViewButton menu2_3 = new ViewButton();
        menu2_3.setName("博客地址");
        menu2_3.setType("view");
        menu2_3.setUrl("http://www.changjie.me");

        Button button2 = new Button();
        button2.setName("个人主页");
        button2.setSub_button(new Button[]{menu2_1, menu2_2, menu2_3});


        ClickButton menu3_1 = new ClickButton();
        menu3_1.setName("扫码");
        menu3_1.setType("scancode_push");
        menu3_1.setKey("31");

        ClickButton menu3_2 = new ClickButton();
        menu3_2.setName("地理位置");
        menu3_2.setType("location_select");
        menu3_2.setKey("32");

        Button button3 = new Button();
        button3.setName("生活服务");
        button3.setSub_button(new Button[]{menu3_1,menu3_2});

        menu.setButton(new Button[]{button1, button2, button3});
        return menu;

    }
}
