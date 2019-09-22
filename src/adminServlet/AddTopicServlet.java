package adminServlet;

import Services.ITopicService;
import Services.impl.TopicService;
import entity.Topic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 添加主题的action页面
 */
@WebServlet("/admin/AddTopicServlet")
public class AddTopicServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name=request.getParameter("topicName");
        ITopicService topicService=new TopicService();
        boolean result=topicService.addTopic(new Topic(name));
        if (result){
            response.sendRedirect("main.html");
        }else {
            response.sendRedirect("addTopic.html");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
