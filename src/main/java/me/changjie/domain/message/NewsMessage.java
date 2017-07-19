package me.changjie.domain.message;

import java.util.List;

/**
 * Created by ChangJie on 2017/7/18.
 */
public class NewsMessage extends BaseMessage
{
    /**
     * 图文消息个数，限制为8条以内
     */
    private int ArticleCount;

    /**
     * 多条图文消息信息，默认第一个item为大图,注意，如果图文数超过8，则将会无响应
     */
    private List<News> Articles;

    public int getArticleCount()
    {
        return ArticleCount;
    }

    public void setArticleCount(int articleCount)
    {
        ArticleCount = articleCount;
    }

    public List<News> getArticles()
    {
        return Articles;
    }

    public void setArticles(List<News> articles)
    {
        Articles = articles;
    }
}
