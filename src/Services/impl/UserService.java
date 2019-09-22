package Services.impl;

import Dao.IUserDao;
import Dao.impl.UserDao;
import entity.User;

public class UserService implements Services.IUserService {
    IUserDao userDao = new UserDao();

    /**
     * 管理员登录
     */
    @Override
    public User adminlogin(User user) {
        return userDao.adminlogin(user);
    }

    /**
     * 普通用户登录
     *
     * @param u
     * @return
     */
    @Override
    public User userlogin(User u) {
        return userDao.userlogin(u);
    }

    /**
     * 普通用户注册
     *
     * @param name
     * @param pwd
     * @return
     */
    @Override
    public Boolean UserReg(String name, String pwd) {
        return userDao.userReg(name, pwd);
    }

    /**
     * 校验用户
     * @param name
     * @return
     */
    @Override
    public boolean checkName(String name) {
        return userDao.checkName(name);
    }
}
