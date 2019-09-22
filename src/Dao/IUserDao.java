package Dao;

import com.sun.org.apache.xpath.internal.operations.Bool;
import entity.User;

public interface IUserDao {
    User adminlogin(User user);
    User userlogin(User user);
    Boolean userReg(String name, String pwd);
    boolean checkName(String name);
}
