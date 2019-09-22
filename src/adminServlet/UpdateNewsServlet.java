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

@WebServlet("/admin/UpdateNewsServlet")
public class UpdateNewsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String strId=request.getParameter("newsId");
        int id=Integer.parseInt(strId==null||"".equals(strId)?"0":strId);
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        News news = new News(id, title, content);
        INewsService newsService=new NewsService();
        boolean result = newsService.updateNews(news);
        if (result){
            response.sendRedirect("editNews.html");
        }else{
            response.sendRedirect("UpdateNews");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
