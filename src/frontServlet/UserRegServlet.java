package frontServlet;

import Services.IUserService;
import Services.impl.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/front/UserRegServlet")
public class UserRegServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();


        String name = request.getParameter("username");
        String pwd = request.getParameter("pwd");
        IUserService userService = new UserService();
        boolean flag = userService.UserReg(name, pwd);
        if (flag) {
            out.print("<script>alert('注册成功！');location.href='index.html'</script>");
        } else {
            out.print("<script>alert('用户名已存在！注册失败！');location.href='index.jsp'</script>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
