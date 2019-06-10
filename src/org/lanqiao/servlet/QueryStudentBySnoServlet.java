package org.lanqiao.servlet;

import org.lanqiao.entity.Student;
import org.lanqiao.service.impl.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/QueryStudentBySnoServlet",loadOnStartup = 1,initParams = {@WebInitParam(name="servletParamName",value = "servletParamValue")})
public class QueryStudentBySnoServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");//设置请求编码
        int no=Integer.parseInt(request.getParameter("sno"));
        StudentServiceImpl studentServiceImpl =new StudentServiceImpl();
        Student student = studentServiceImpl.queryStudentBySno(no);
        System.out.println(student);
        //将此人的数据通过前台JSP显示studentInfo.jsp
        response.setCharacterEncoding("utf-8");
        request.setAttribute("student",student);//将查询到的学生放到request 域中
        //如果request域没有数据，使用重定向跳转response.sendRedirect();
        //如果request域有数据（request.setAttribute()）,使用请求转发跳转

        request.getRequestDispatcher("studentInfo.jsp").forward(request,response);


    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }
}
