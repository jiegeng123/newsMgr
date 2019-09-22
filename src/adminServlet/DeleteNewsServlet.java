package adminServlet;

import Services.INewsService;
import Services.impl.NewsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/admin/DeleteNewsServlet")
public class DeleteNewsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int id=Integer.parseInt(request.getParameter("newsId"));
        INewsService newsService=new NewsService();
        boolean flag=newsService.deteNews(id);
        if (flag){
            response.sendRedirect("editNews.html");
        }else {
            PrintWriter out=response.getWriter();
            out.print("<script>alert('删除失败！');</script>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
