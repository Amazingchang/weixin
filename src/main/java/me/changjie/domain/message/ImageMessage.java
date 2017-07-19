package me.changjie.domain.message;


/**
 * Created by ChangJie on 2017/7/18.
 */
public class ImageMessage extends BaseMessage
{
    private Image Image;

    public me.changjie.domain.message.Image getImage()
    {
        return Image;
    }

    public void setImage(me.changjie.domain.message.Image image)
    {
        Image = image;
    }
}
