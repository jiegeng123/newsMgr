package util;

import java.sql.*;

public class BaseDB {
    /**
     * 获得数据库连接
     */
    public static Connection getConn() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/news", "root", "123456");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 关闭所有
     */
    public static void closeAll(Connection conn, Statement st, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * 通用的增删改方法
     */
    public static int ZSG(String sql,Object[] objects){
        Connection conn=getConn();
        PreparedStatement pst=null;
        int num=0;
        try{
            pst=conn.prepareStatement(sql);
            if (objects!=null){
                for (int i = 0; i < objects.length; i++) {
                    pst.setObject(i+1,objects[i]);
                }
            }
            num = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeAll(conn,pst,null);
        }
        return num;
    }

}
