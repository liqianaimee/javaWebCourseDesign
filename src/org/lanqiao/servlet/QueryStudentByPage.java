package org.lanqiao.servlet;

import org.lanqiao.entity.Page;
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
import java.util.List;

@WebServlet(value = "/QueryStudentByPage",loadOnStartup = 1,initParams = {@WebInitParam(name="servletParamName",value = "servletParamValue")})
public class QueryStudentByPage extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Query");
        IStudentService studentService=new StudentServiceImpl();
        //将分页所需的五个字段，组装到page对象
        Page page0=new Page();
        int totalCount = studentService.getTotalCount();//数据总数
        page0.setTotalCount(totalCount);
        String cPage = request.getParameter("currentPage");
        if(cPage==null){
            cPage="0";//第一次进来，默认第一页
        }

        int currentPage=Integer.parseInt(cPage);
        page0.setCurrentPage(currentPage);
        int pageSize=3;
        page0.setPageSize(pageSize);
        List<Student> students=studentService.queryStudentsByPage(currentPage,pageSize);
        page0.setStudents(students);
        System.out.println(currentPage);
        System.out.println(students);

        request.setAttribute("p",page0);
        request.getRequestDispatcher("index.jsp").forward(request,response);

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        System.out.println("fffffffffffjdsfjadsklfjalds");
    }
}
