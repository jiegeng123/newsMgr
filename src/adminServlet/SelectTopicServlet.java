package adminServlet;

import Services.ITopicService;
import Services.impl.TopicService;
import entity.Topic;
import util.JsonServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 添加新闻的时候选择所属主题
 */
@WebServlet("/admin/SelectTopicServlet")
public class SelectTopicServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ITopicService topicService=new TopicService();
        List<Topic> list = topicService.queryTopic();
        Map<String,Object> map=new HashMap<>();
        map.put("topicList",list);
        JsonServlet.getJson(request,response,map);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

}
