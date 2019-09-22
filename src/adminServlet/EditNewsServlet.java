package adminServlet;


import Services.INewsService;
import Services.impl.NewsService;
import entity.News;
import util.JsonServlet;
import util.PageUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/admin/EditNewsServlet")
public class EditNewsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        int pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
        INewsService newsService = new NewsService();
        List<News> list = newsService.queryNewsByPage(keyword, pageIndex, pageSize);
        int count=newsService.getNewsCount();
        int totalPage= PageUtil.getTotalPage(count,pageSize);
        if(pageIndex<1){
            pageIndex=1;
        }
        if (pageIndex>totalPage){
            pageIndex=totalPage;
        }
        Map<String,Object> map=new HashMap<>();
        map.put("newsList",list);
        map.put("pageIndex",pageIndex);
        map.put("totalPage",totalPage);
        JsonServlet.getJson(request,response,map);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
