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

@WebServlet("/admin/UpdateTopicServlet")
public class UpdateTopicServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id= Integer.parseInt(request.getParameter("topicId"));
        String name=request.getParameter("topicName");
        Topic topic=new Topic(id,name);
        ITopicService topicService=new TopicService();
        boolean flag=topicService.editTopic(topic);
        if (flag){
            response.sendRedirect("EditTopicServlet");
        }else {
            response.sendRedirect("UpdateTopic");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
