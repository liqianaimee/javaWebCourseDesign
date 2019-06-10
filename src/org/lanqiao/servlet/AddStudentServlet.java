package org.lanqiao.servlet;


import org.lanqiao.entity.Student;
import org.lanqiao.service.IStudentService;
import org.lanqiao.service.impl.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/AddStudentServlet",loadOnStartup = 1,initParams = {@WebInitParam(name="servletParamName",value = "servletParamValue")})
public class AddStudentServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");//设置请求编码
        int no=Integer.parseInt(request.getParameter("sno"));
        String name =request.getParameter("sname");
        int age =Integer.parseInt(request.getParameter("sage"));
        String address =request.getParameter("saddress");
        Student student=new Student(no,name,age,address);
        //将封装好的student对象传到service
        //接口x=new 实现类（）；
        IStudentService studentServiceImpl =new StudentServiceImpl();
        boolean result = studentServiceImpl.addStudent(student);

        //设置响应编码，要在out之前
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("utf-8");

        if(!result){//如果增加失败，给request放入一条数据error
            request.setAttribute("error","addError");

        }else{//增加成功
            request.setAttribute("error","noaddError");
        }
        System.out.println(request.getAttribute("error"));
        System.out.println("dddddd");
       request.getRequestDispatcher("/QueryStudentByPage").forward(request,response);
        //response.sendRedirect("QueryStudentByPage");//经过两次转发到index。jsp中显示

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }
}
