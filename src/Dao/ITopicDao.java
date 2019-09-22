package Dao;

import entity.Topic;

import java.util.List;

public interface ITopicDao {
    boolean addTopic(Topic topic);

    List<Topic> queryTopic();

    boolean editTopic(Topic topic);

    Topic getTopicById(int id);

    boolean delTopic(int id);

    List<Topic> queryTopicBykeyword(String keyword, int pageSize, int pageIndex);

    int getCount();
}
