<%@ page import="org.lanqiao.entity.Student" %><%--
  Created by IntelliJ IDEA.
  User: 14023
  Date: 2019/6/8
  Time: 9:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        Student student = (Student) request.getAttribute("student");
    %>
        <!--通过表单显示此人-->
    <form action="UpdateStudentServlet">
        学号：<input type="text"  name="sno" value="<%=student.getSno()%>" readonly/><br/>
        姓名：<input type="text" name="sname" value="<%=student.getSname()%>"/><br/>
        年龄：<input type="text" name="sage" value="<%=student.getSage()%>"/><br/>
        地址：<input type="text" name="saddress" value="<%=student.getSaddress()%>"/><br/>
        <input type="submit" value="修改"/>
        <a href="QueryStudentByPage">返回</a>

    </form>
</body>
</html>
