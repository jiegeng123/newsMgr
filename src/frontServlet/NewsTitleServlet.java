package frontServlet;

import Services.INewsService;
import Services.impl.NewsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/front/NewsTitleServlet")
public class NewsTitleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        String keyword = request.getParameter("keyword");
        INewsService newsService = new NewsService();
        String titles = newsService.queryTitleByKeyword(keyword);
        if (titles!=""){
            out.print(titles);
        }else {
            out.print("");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
