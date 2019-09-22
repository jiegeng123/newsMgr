package Dao.impl;

import entity.Topic;
import util.BaseDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TopicDao extends BaseDB implements Dao.ITopicDao {
    /**
     * 添加主题
     */
    @Override
    public boolean addTopic(Topic topic) {
        String sql = "insert into t_topic (tname) values (?)";
        Object[] obj = {topic.getName()};
        int result = ZSG(sql, obj);
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 显示所有主题
     */
    @Override
    public List<Topic> queryTopic() {
        Connection conn = getConn();
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<Topic> list = new ArrayList<>();
        try {
            String sql = "select * from t_topic order by tid desc ";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                Topic topic = new Topic(rs.getInt("tid"), rs.getString("tname"));
                list.add(topic);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(conn, pst, rs);
        }
        return list;
    }

    /**
     * 修改主题
     */
    @Override
    public boolean editTopic(Topic topic) {
        String sql = "update t_topic set tname=? where tid=?";
        Connection conn = getConn();
        Object[] obj = {topic.getName(), topic.getId()};
        int result = ZSG(sql, obj);
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 通过Id获得主题
     */
    @Override
    public Topic getTopicById(int id) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        Topic topic = null;
        try {
            String sql = "select * from t_topic where tid=?";
            conn = getConn();
            pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            if (rs.next()) {
                topic = new Topic(rs.getInt("tid"), rs.getString("tname"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeAll(conn, pst, rs);
        }
        return topic;
    }

    /**
     * 删除主题
     */
    @Override
    public boolean delTopic(int id) {
        String sql = "delete from t_topic where tid=?";
        Object[] obj = {id};
        int result = ZSG(sql, obj);
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 模糊搜索主题分页显示
     *
     * @param keyword
     * @param pageSize
     * @param pageIndex
     * @return
     */
    @Override
    public List<Topic> queryTopicBykeyword(String keyword, int pageSize, int pageIndex) {
        List<Topic> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = getConn();
            String sql = "select * from t_topic where tname like ? order by tid desc limit ?,?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, "%" + keyword + "%");
            pst.setInt(2, pageSize * (pageIndex - 1));
            pst.setInt(3, pageSize);
            rs = pst.executeQuery();
            while (rs.next()) {
                Topic topic = new Topic(rs.getInt("tid"), rs.getString("tname"));
                list.add(topic);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeAll(conn, pst, rs);
        }
        return list;
    }

    /**
     * 获得主题的数量
     */
    @Override
    public int getCount() {
        Connection conn = getConn();
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "select count(1) from t_topic";
        int count = 0;
        try {
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }


}
