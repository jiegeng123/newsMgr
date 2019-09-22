package frontServlet;

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

@WebServlet("/front/CenterNewsServlet")
public class CenterNewsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //中间所有新闻的分页展示
        INewsService newsService=new NewsService();
        String title=request.getParameter("title");
        int pageSize= Integer.parseInt(request.getParameter("pageSize"));
        int pageIndex= Integer.parseInt(request.getParameter("pageIndex"));

        int count=newsService.getNewsCount();
        int totalPage= PageUtil.getTotalPage(count,pageSize);
        List<News> list3=newsService.queryNewsByPage(title,pageIndex,pageSize);

        if (pageIndex<1){
            pageIndex=1;
        }
        if (pageIndex>totalPage){
            pageIndex=totalPage;
        }

        //国内新闻板块
        List<News> list = newsService.queryNewsByTidNum(4, 12);

        //国际新闻
        List<News> list1 = newsService.queryNewsByTidNum(5, 12);

        //娱乐新闻
        List<News> list2 = newsService.queryNewsByTidNum(6, 12);

        //右侧图片新闻 生活主题
        List<News> list4 = newsService.queryNewsByTidNum(11, 4);

        Map<String,Object> maps=new HashMap<>();
        maps.put("newsList",list3);
        maps.put("pageIndex",pageIndex);
        maps.put("totalPage",totalPage);
        maps.put("pageSize",16);
        maps.put("list",list); //国内
        maps.put("list1",list1);//国际
        maps.put("list2",list2);//娱乐
        maps.put("list4",list4);//右侧图片

        JsonServlet.getJson(request,response,maps);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
