package adminServlet;

import Services.INewsService;
import Services.impl.NewsService;
import entity.News;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/QueryNewsByIdServlet")
public class QueryNewsByIdServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        INewsService newsService=new NewsService();
        int id= Integer.parseInt(request.getParameter("newsId"));
        News news= newsService.queryNewsByNid(id);
        request.setAttribute("news",news);
        request.getRequestDispatcher("queryNewsById.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
