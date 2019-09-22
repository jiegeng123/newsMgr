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

@WebServlet("/admin/UpdateTopic")
public class UpdateTopic extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int topicId= Integer.parseInt(request.getParameter("topicId"));
        ITopicService topicService=new TopicService();
        Topic topic= topicService.getTopicById(topicId);
        request.setAttribute("topic",topic);
        request.getRequestDispatcher("updateTopic.jsp").forward(request,response);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
