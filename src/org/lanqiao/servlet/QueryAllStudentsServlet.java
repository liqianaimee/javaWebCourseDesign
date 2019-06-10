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
import java.util.List;

@WebServlet(value = "/QueryAllStudentsServlet",loadOnStartup = 1,initParams = {@WebInitParam(name="servletParamName",value = "servletParamValue")})
public class QueryAllStudentsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");//设置请求编码

        StudentServiceImpl studentServiceImpl =new StudentServiceImpl();
        List<Student> students = studentServiceImpl.queryAllStudents();
        System.out.println(students);
        response.setContentType("text/html;charset=utf-8");

        response.setCharacterEncoding("utf-8");
        request.setAttribute("students",students);


        //因为request域中有数据，需要通过请求转发的方式跳转（重定向会丢失request域）pageContext<request<session<application域越小越好
        request.getRequestDispatcher("index.jsp").forward(request,response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }
}

