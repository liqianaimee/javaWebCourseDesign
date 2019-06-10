package org.lanqiao.servlet;

import org.lanqiao.service.IStudentService;
import org.lanqiao.service.impl.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/DeleteStudentServlet",loadOnStartup = 1,initParams = {@WebInitParam(name="servletParamName",value = "servletParamValue")})
public class DeleteStudentServlet extends HttpServlet{
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            request.setCharacterEncoding("utf-8");//设置请求编码
            int no=Integer.parseInt(request.getParameter("sno"));

            IStudentService studentServiceImpl =new StudentServiceImpl();
            boolean result = studentServiceImpl.deleteStudentBySno(no);


            //设置响应编码，要在out之前
            response.setContentType("text/html;charset=UTF-8");
            response.setCharacterEncoding("utf-8");
            PrintWriter out = response.getWriter();

            if(result){
                JOptionPane.showMessageDialog(null, "确认删除？");
                out.println("删除成功");
                response.sendRedirect("QueryStudentByPage");//删除后重新查询所有学生信息
            }else {
                out.println("删除失败");
            }
        }

        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            this.doGet(request,response);
        }
}
