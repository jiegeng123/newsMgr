package adminServlet;

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

@WebServlet("/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IUserService userService = new UserService();

        String name = request.getParameter("loginId");
        String pwd = request.getParameter("password");

        User user = userService.adminlogin(new User(name, pwd));
        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("User", user);
            request.getRequestDispatcher("/admin/mainform.html").forward(request, response);
        } else {
            response.sendRedirect("index.html");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
