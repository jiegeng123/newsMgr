package entity;

import java.sql.Timestamp;

public class News {
    private int id;
    private String title;
    private String content;
    private String createTime;
    private String pic;
    private Topic topic;
    private User user;

    public News(int id, String title, String content, String createTime, String pic, Topic topic, User user) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createTime = createTime;
        this.pic = pic;
        this.topic = topic;
        this.user = user;
    }

    public News(String title, String content, String pic, Topic topic, User user) {
        this.title = title;
        this.content = content;
        this.pic = pic;
        this.topic = topic;
        this.user = user;
    }

    public News(int id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public News(int id, String title, String createTime, Topic topic, User user) {
        this.id = id;
        this.title = title;
        this.createTime = createTime;
        this.topic = topic;
        this.user = user;
    }

    public News(String title, String content, Topic topic, User user) {
        this.title = title;
        this.content = content;
        this.topic = topic;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
