package adminServlet;

import Services.ITopicService;
import Services.impl.TopicService;
import entity.Topic;
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

@WebServlet("/admin/EditTopicServlet")
public class EditTopicServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ITopicService topicService=new TopicService();
        String keyword=request.getParameter("title");
        int pageIndex= Integer.parseInt(request.getParameter("pageIndex"));
        int pageSize= Integer.parseInt(request.getParameter("pageSize"));

        int count=topicService.getCount();
        int totalpage= PageUtil.getTotalPage(count,4);

        List<Topic> list=topicService.queryTopicBykeyword(keyword,pageSize,pageIndex);

        Map<String,Object> maps=new HashMap<>();
        maps.put("topicList",list);
        maps.put("pageIndex",pageIndex);
        maps.put("totalPage",totalpage);

        JsonServlet.getJson(request,response,maps);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
