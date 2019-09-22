package adminServlet;

import Services.ITopicService;
import Services.impl.TopicService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/admin/DeleteTopicServlet")
public class DeleteTopicServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int id= Integer.parseInt(request.getParameter("topicId"));
        ITopicService topicService=new TopicService();
        boolean flag=topicService.delTopic(id);
        if (flag){
            response.sendRedirect("EditTopicServlet");
        }else {
            PrintWriter out=response.getWriter();
            out.print("<script>alert('删除失败！');</script>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
