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
import java.io.PrintWriter;

@WebServlet(value = "/UpdateStudentServlet",loadOnStartup = 1,initParams = {@WebInitParam(name="servletParamName",value = "servletParamValue")})
public class UpdateStudentServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");//设置请求编码
        //获取待修改人的学号
        int no=Integer.parseInt(request.getParameter("sno"));//根据表单中的name，获取对应的value
        //获取修改的内容
        String name =request.getParameter("sname");
        int age =Integer.parseInt(request.getParameter("sage"));
        String address =request.getParameter("saddress");
        //将修改的信息封装起来
        Student student=new Student(name,age,address);

        //将封装好的student对象传到service
        StudentServiceImpl studentServiceImpl =new StudentServiceImpl();
        boolean result = studentServiceImpl.updateStudentBySno(no,student);


        //设置响应编码，要在out之前
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();

        if(result){
            out.println("修改成功");
            response.sendRedirect("QueryStudentByPage");//修改完毕后再调回查询全部学生并显示
        }else {
            out.println("修改失败");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }
}
