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

@WebServlet("/front/CheckNameServlet")
public class CheckNameServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out=response.getWriter();
        String name = request.getParameter("username");
        if (name!=""){
            IUserService userService=new UserService();
            boolean flag=userService.checkName(name);
            if (flag){
                out.print("true");
            }else {
                out.print("false");
            }
        }else{
            out.print("refuse");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
