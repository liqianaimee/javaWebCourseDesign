<%@ page import="java.util.List" %>
<%@ page import="org.lanqiao.entity.Student" %>
<%@ page import="org.lanqiao.entity.Page" %><%--
  Created by IntelliJ IDEA.
  User: 14023
  Date: 2019/6/6
  Time: 17:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>学生信息列表</title>

    <script src="https://code.jquery.com/jquery-3.4.0.min.js"></script>
    <script type="text/javascript">
      $(document).ready(function () {
        $("tr:odd").css("background-color","lightgray");
      });
    </script>
  </head>
  <body>
  <%
    //1、addError：失败  2、执行了增加  3、直接访问查询全部学生，根本没有执行增加
    String error=(String)request.getAttribute("error");
    System.out.println(request.getAttribute("error"));
    if(error!=null) {//如果equals前后的字符串换过来就可以避免空指针异常了，就不用了判断是否为null
      if (error.equals("addError")) {
        out.print("增加失败！");
      } else if (error.equals("noaddError")) {
        out.print("增加成功！");
      }
    }else {
      //根本没有执行增加
    }
  %>
  <table border="1px">
    <tr>
      <td>学号</td>
      <td>姓名</td>
      <td>年龄</td>
      <!--<td>地址</td>-->
      <td>操作</td>
    </tr>

    <%
    //获取request域中的数据
     Page p= (Page) request.getAttribute("p");
     List<Student> students= (List<Student>) request.getAttribute("students");
     if(p!=null){
     for(Student student:p.getStudents()){
    %>
    <tr>
      <td><a href="QueryStudentBySnoServlet?sno=<%=student.getSno()%>"><%=student.getSno()%></a></td>
      <td><%=student.getSname()%></td>
      <td><%=student.getSage()%></td>
      <td><a href="DeleteStudentServlet?sno=<%=student.getSno()%>">删除</a> </td>
    </tr>
     <%
     }
       }
     if(students!=null){
       for(Student student:students){
     %>
    <tr>
      <td><a href="QueryStudentBySnoServlet?sno=<%=student.getSno()%>"><%=student.getSno()%></a></td>
      <td><%=student.getSname()%></td>
      <td><%=student.getSage()%></td>
      <td><a href="DeleteStudentServlet?sno=<%=student.getSno()%>">删除</a> </td>
    </tr>
    <%
       }
     }
     %>
  </table>
  <a href="add.jsp">新增</a>
  <%
    if(p.getCurrentPage()==0){
      %>
    <a href="QueryStudentByPage?currentPage=<%=p.getCurrentPage()+1%>">下一页</a>
    <a href="QueryStudentByPage?currentPage=<%=p.getTotalPage()-1%>">尾页</a>

  <%
    }
    else if(p.getCurrentPage()==p.getTotalPage()-1){
      %>
    <a href="QueryStudentByPage?currentPage=0">首页</a>
    <a href="QueryStudentByPage?currentPage=<%=p.getCurrentPage()-1%>">上一页</a>
  <%
  }
    else{
      %>
  <a href="QueryStudentByPage?currentPage=0">首页</a>
  <a href="QueryStudentByPage?currentPage=<%=p.getCurrentPage()-1%>">上一页</a>
  <a href="QueryStudentByPage?currentPage=<%=p.getCurrentPage()+1%>">下一页</a>
  <a href="QueryStudentByPage?currentPage=<%=p.getTotalPage()-1%>">尾页</a>
  <%
    }
  %>
  <br/>
  每页显示
  <select>
    <option value="3">3</option>
    <option value="5">5</option>
    <option value="8">8</option>
  </select>
  条
  </body>
</html>
