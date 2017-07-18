package me.changjie.domain;


/**
 * Created by ChangJie on 2017/7/18.
 */
public class ImageMessage extends BaseMessage
{
    private Image Image;

    public me.changjie.domain.Image getImage()
    {
        return Image;
    }

    public void setImage(me.changjie.domain.Image image)
    {
        Image = image;
    }
}
