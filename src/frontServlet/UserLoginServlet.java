package frontServlet;

import Services.IUserService;
import Services.impl.UserService;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/front/UserLoginServlet")
public class UserLoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();
        String name = request.getParameter("username");
        String pwd = request.getParameter("password");
        IUserService userService = new UserService();
        User user = userService.userlogin(new User(name, pwd));
        if (user!=null){
            HttpSession session=request.getSession();
            session.setAttribute("user",user);
            //out.print(user.getName());
            request.getRequestDispatcher("index.html").forward(request,response);
        }else {
            out.print("<script>alert('账户名或密码错误！');location.href='index.jsp'</script>");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
