package util;

import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 将任意类型转化为JSON格式
 */
public class JsonServlet {
    public static void getJson(HttpServletRequest request, HttpServletResponse response,Object object){
        response.setContentType("text/html;charset=utf-8");
        response.setHeader("Pragma","No-cache");
        response.setHeader("Cache-Control","no-cache");
        response.setDateHeader("Expires",-10);

        PrintWriter out=null;
        try {
            out=response.getWriter();
            String strJson= JSON.toJSONString(object);
            out.print(strJson);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            out.close();
        }
    }
}
