package me.changjie.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;

/**
 * Created by ChangJie on 2017/5/4.
 */
@Document(collection = "article")
public class Article extends JdkSerializationRedisSerializer implements Serializable {
    private static final long serialVersionUID = -6725277155523196695L;

    private String id;

    private String title;

    private String content;

    private String category;

    private Date createdTime;

    private Date updatedTime;

    private Integer status;



    public Article() {
    }

    public Article(String id, String title, String content, String category, Date createdTime, Date updatedTime, Integer status)
    {
        this.id = id;
        this.title = title;
        this.content = content;
        this.category = category;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
        this.status = status;
    }

    public String getId()
    {

        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public String getCategory()
    {
        return category;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }

    public Date getCreatedTime()
    {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime)
    {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime()
    {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime)
    {
        this.updatedTime = updatedTime;
    }

    public Integer getStatus()
    {
        return status;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
    }

    @Override
    public String toString()
    {
        return "Article{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", category='" + category + '\'' +
                ", createdTime=" + createdTime +
                ", updatedTime=" + updatedTime +
                ", status=" + status +
                '}';
    }
}
