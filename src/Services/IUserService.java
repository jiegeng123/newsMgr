package Services;

import entity.User;


public interface IUserService {
    User adminlogin(User u);
    User userlogin(User u);
    Boolean UserReg(String name, String pwd);
    boolean checkName(String name);
}
