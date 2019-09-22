package Dao;

import Dao.impl.NewsDao;
import entity.News;

import java.util.List;

public interface INewsDao {
   /* List<News> queryNews();*/

    boolean addNews(String title, String content, String pic, int tid, int uid);

    boolean updateNews(News news);

    News queryNewsByNid(int newsid);

    boolean deteNews(int id);

    int getNewsCount();

    List<News> queryNewsByPage(String keyword, int currPage, int pageSize);

    List<News> queryNewsByTidNum(int tid, int num);

    List<String> queryTitleByKeyword(String keyword);
}
