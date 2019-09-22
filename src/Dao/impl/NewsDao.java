package Dao.impl;

import entity.News;
import entity.Topic;
import entity.User;
import util.BaseDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NewsDao extends BaseDB implements Dao.INewsDao {
    /**
     * 查询所有新闻
     *//*
    @Override
    public List<News> queryNews() {
        Connection conn = getConn();
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<News> list = new ArrayList<>();
        try {
            String sql = "select u.*,n.*,t.* from t_user u,t_news n,t_topic t where u.uid=n.uid and t.tid=n.tid order by n.time desc ";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                Topic topic = new Topic(rs.getString("tname"));
                User user = new User(rs.getString("uname"));
                News news = new News(rs.getInt("nid"), rs.getString("title"), rs.getTimestamp("time"), topic, user);
                list.add(news);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }*/

    /**
     * 添加新闻
     */
    @Override
    public boolean addNews(String title, String content, String pic, int tid, int uid) {
        String sql = "insert into t_news (title,content,pic,tid,uid) values (?,?,?,?,?)";
        Object[] objects = {title, content, pic, tid, uid};
        int result = ZSG(sql, objects);
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 修改新闻
     */
    @Override
    public boolean updateNews(News news) {
        String sql = "update t_news set title=?,content=? where nid=?";
        Object[] objects = {news.getTitle(), news.getContent(), news.getId()};
        int result = ZSG(sql, objects);
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 通过新闻编号查询新闻
     */
    @Override
    public News queryNewsByNid(int newsid) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        News news = null;
        try {
            conn = getConn();
            String sql = "select n.*,t.*,u.* from t_news n,t_topic t,t_user u where n.tid=t.tid and n.uid=u.uid and n.nid=?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, newsid);
            rs = pst.executeQuery();
            if (rs.next()) {
                Topic topic = new Topic(rs.getInt("tid"), rs.getString("tname"));
                User user = new User(rs.getInt("uid"), rs.getString("uname"));
                news = new News(rs.getInt("nid"), rs.getString("title"), rs.getString("content"), rs.getString("time"), rs.getString("pic"), topic, user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeAll(conn, pst, rs);
        }
        return news;
    }

    /**
     * 通过新闻编号删除新闻
     */
    @Override
    public boolean deteNews(int id) {
        String sql = "delete from t_news where nid=?";
        Object[] objects = {id};
        int result = ZSG(sql, objects);
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 查询新闻总条数
     */
    @Override
    public int getNewsCount() {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        int count = 0;
        try {
            conn = getConn();
            String sql = "select count(1) from t_news";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeAll(conn, pst, rs);
        }
        return count;
    }

    /**
     * 根据关键字分页查询新闻
     */
    @Override
    public List<News> queryNewsByPage(String keyword, int currPage, int pageSize) {
        List<News> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = getConn();
            String sql = "select n.*,t.*,u.* from t_news n,t_topic t,t_user u where n.tid=t.tid and n.uid=u.uid and n.title like ? order by n.nid desc limit ?,?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, "%" + keyword + "%");
            pst.setInt(2, pageSize * (currPage - 1));
            pst.setInt(3, pageSize);
            rs = pst.executeQuery();
            while (rs.next()) {
                Topic topic = new Topic(rs.getString("tname"));
                User user = new User(rs.getString("uname"));
                News news = new News(rs.getInt("nid"), rs.getString("title"), rs.getString("content"), rs.getString("time"), rs.getString("pic"), topic, user);
                list.add(news);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 通过主题id查询指定条新闻
     */
    @Override
    public List<News> queryNewsByTidNum(int tid, int num) {
        List<News> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = getConn();
            String sql = "select n.*,t.*,u.* from t_news n,t_topic t,t_user u where n.tid=t.tid and n.uid=u.uid and n.tid=? order by nid desc limit ?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, tid);
            pst.setInt(2, num);
            rs = pst.executeQuery();
            while (rs.next()) {
                Topic topic = new Topic(rs.getInt("tid"), rs.getString("tname"));
                User user = new User(rs.getString("uname"));
                News news = new News(rs.getInt("nid"), rs.getString("title"), rs.getString("content"), rs.getString("time"), rs.getString("pic"), topic, user);
                list.add(news);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeAll(conn, pst, rs);
        }
        return list;

    }

    /**
     * 根据关键字查询新闻标题
     *
     * @param keyword
     * @return
     */
    @Override
    public List<String> queryTitleByKeyword(String keyword) {
        Connection conn = getConn();
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<String> titles = new ArrayList<>();
        String sql = "select title from t_news where title like ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, "%" + keyword + "%");
            rs = pst.executeQuery();
            while (rs.next()) {
                String title = rs.getString("title");
                titles.add(title);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(conn, pst, rs);
        }
        return titles;
    }

    public static void main(String[] args) {
        NewsDao newsDao = new NewsDao();
        List<News> list = newsDao.queryNewsByTidNum(4, 2);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getCreateTime());
        }
    }

}
