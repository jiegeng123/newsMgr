package Dao.impl;

import entity.User;
import util.BaseDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao extends BaseDB implements Dao.IUserDao {
    /**
     * 管理员用户登录
     */
    @Override
    public User adminlogin(User user) {
        String sql = "select * from t_user where uname=? and pwd=? and flag=0";
        Connection conn = getConn();
        PreparedStatement pst = null;
        ResultSet rs = null;
        User u = null;
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, user.getName());
            pst.setString(2, user.getPwd());
            rs = pst.executeQuery();
            if (rs.next()) {
                u = new User(rs.getInt("uid"), rs.getString("uname"), rs.getString("pwd"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(conn, pst, rs);
        }
        return u;
    }

    /**
     * 普通用户登录
     *
     * @param user
     * @return
     */
    @Override
    public User userlogin(User user) {
        String sql = "select * from t_user where uname=? and pwd=?";
        Connection conn = getConn();
        PreparedStatement pst = null;
        ResultSet rs = null;
        User u = null;
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, user.getName());
            pst.setString(2, user.getPwd());
            rs = pst.executeQuery();
            if (rs.next()) {
                u = new User(rs.getInt("uid"), rs.getString("uname"), rs.getString("pwd"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(conn, pst, rs);
        }
        return u;
    }

    /**
     * 普通用户注册
     *
     * @param name
     * @param pwd
     * @return
     */
    @Override
    public Boolean userReg(String name, String pwd) {
        Connection conn = getConn();
        PreparedStatement pst = null;
        ResultSet rs = null;
        boolean flag=false;
        try {
            String Sql = "insert into t_user (uname,pwd) values(?,?)";
            Object[] objects = {name, pwd};
            int result = ZSG(Sql, objects);
            if (result > 0)
                return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 用户校验
     */
    public boolean checkName(String name){
        boolean flag=false;
        Connection conn=getConn();
        String sql="select * from t_user where uname=?";
        PreparedStatement pst=null;
        ResultSet rs=null;
        try {
            pst=conn.prepareStatement(sql);
            pst.setString(1,name);
            rs=pst.executeQuery();
            if (rs.next()){
                flag=true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }
}