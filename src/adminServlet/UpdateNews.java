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

@WebServlet("/admin/UpdateNews")
public class UpdateNews extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        INewsService newsService=new NewsService();
        String strId=request.getParameter("newsId");
        int id=Integer.parseInt(strId==null||"".equals(strId)?"0":strId);
        News news= newsService.queryNewsByNid(id);
        request.setAttribute("news",news);
        request.getRequestDispatcher("updateNews.jsp").forward(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
