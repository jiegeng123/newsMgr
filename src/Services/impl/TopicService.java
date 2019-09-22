package Services.impl;

import Dao.ITopicDao;
import Dao.impl.TopicDao;
import entity.Topic;

import java.util.List;

public class TopicService implements Services.ITopicService {
    ITopicDao topicDao=new TopicDao();
    /**
     * 添加主题
     */
    @Override
    public boolean addTopic(Topic topic) {
        return topicDao.addTopic(topic);
    }

    /**
     * 显示所有主题
     */
    @Override
    public List<Topic> queryTopic() {
        return topicDao.queryTopic();
    }

    /**
     * 修改主题
     */
    @Override
    public boolean editTopic(Topic topic) {
        return topicDao.editTopic(topic);
    }

    /**
     * 通过id获取主题
     */
    @Override
    public Topic getTopicById(int id) {
        return topicDao.getTopicById(id);
    }

    /**
     * 删除主题
     */
    @Override
    public boolean delTopic(int id) {
        return topicDao.delTopic(id);
    }

    /**
     * 模糊搜索主题分页显示
     * @param keyword
     * @param pageSize
     * @param pageIndex
     * @return
     */
    @Override
    public List<Topic> queryTopicBykeyword(String keyword, int pageSize, int pageIndex) {
        return topicDao.queryTopicBykeyword(keyword,pageSize,pageIndex);
    }

    @Override
    public int getCount() {
        return topicDao.getCount();
    }

}


