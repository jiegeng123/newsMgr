package Services.impl;

import Dao.INewsDao;
import Dao.impl.NewsDao;
import entity.News;

import java.util.List;

public class NewsService implements Services.INewsService {
    INewsDao newsDao = new NewsDao();

   /* *//**
     * 查询新闻
     *//*
    @Override
    public List<News> queryNews() {
        return newsDao.queryNews();
    }*/

    /**
     * 添加新闻
     */
    @Override
    public boolean addNews(String title, String content, String pic, int tid, int uid) {
        return newsDao.addNews(title, content, pic, tid, uid);
    }

    /**
     * 修改新闻
     */
    @Override
    public boolean updateNews(News news) {
        return newsDao.updateNews(news);
    }


    /**
     * 通过新闻编号查询新闻
     */
    @Override
    public News queryNewsByNid(int newsid) {
        return newsDao.queryNewsByNid(newsid);
    }

    /**
     * 通过新闻编号删除新闻
     */
    @Override
    public boolean deteNews(int id) {
        return newsDao.deteNews(id);
    }

    /**
     * 查询新闻总条数
     */
    @Override
    public int getNewsCount() {
        return newsDao.getNewsCount();
    }

    /**
     * 分页查询新闻
     */
    @Override
    public List<News> queryNewsByPage(String keyword,int currPage, int pageSize) {
        return newsDao.queryNewsByPage(keyword,currPage, pageSize);
    }

    /**
     * 通过主题id查询指定条新闻
     *
     * @param tid
     * @param num
     * @return
     */
    @Override
    public List<News> queryNewsByTidNum(int tid, int num) {
        return newsDao.queryNewsByTidNum(tid, num);
    }

    /**
     * 根据关键字查询新闻标题,把list转换为String类型
     *
     * @param keyword
     * @return
     */
    @Override
    public String queryTitleByKeyword(String keyword) {
        List<String> list = newsDao.queryTitleByKeyword(keyword);
        String titles = "";
        if (list.size() > 0) {
            for (String title : list) {
                titles += title + ",";
            }
            return titles.substring(0, titles.length() - 1);
        } else {
            return "";
        }
    }

}
