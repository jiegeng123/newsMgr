package adminServlet;

import Services.INewsService;
import Services.impl.NewsService;
import com.jspsmart.upload.Request;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspFactory;
import javax.servlet.jsp.PageContext;
import java.io.IOException;

/**
 * 添加新闻的action页面
 */
@WebServlet("/admin/AddNewsServlet")
public class AddNewsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SmartUpload sm=new SmartUpload();
        try {
            //PageContext是jsp的内置对象，在servlet不能直接使用，需要做一些处理
            JspFactory _jspxFactory = null;
            PageContext pageContext = null;
            _jspxFactory = JspFactory.getDefaultFactory();
            pageContext = _jspxFactory.getPageContext(this,request,response,"",true,8192,true);
            sm.initialize(pageContext);
            sm.setCharset("UTF-8");
            sm.upload();
            sm.save("upfiles");
            Request re=sm.getRequest();
            String title=re.getParameter("title");
            String content=re.getParameter("content");
            int tid= Integer.parseInt(re.getParameter("topicid"));
            HttpSession session=request.getSession();
            User user= (User) session.getAttribute("User");
            String pic=sm.getFiles().getFile(0).getFileName();
            INewsService newsService=new NewsService();
            boolean result= newsService.addNews(title,content,pic,tid,user.getId());
            if (result){
                response.sendRedirect("main.html");
            }else{
                response.sendRedirect("addNews.html");
            }
        } catch (SmartUploadException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
