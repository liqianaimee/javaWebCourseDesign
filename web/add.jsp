<%--
  Created by IntelliJ IDEA.
  User: 14023
  Date: 2019/6/8
  Time: 11:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>add</title>
    <script src="https://code.jquery.com/jquery-3.4.0.min.js"></script>
    <script type="text/javascript">

        function check()
        {//return true表单正常提交，否则终止提交
            var sno=$("sno").val();
            var sname=$("sname").val();
            var sage=$("sage").val();
            var saddress=$("saddress").val();
            if(!(sno>0&&sno<100)){
                return false;
            }
            if(!(sname.length>1&&sname.length<11)){
                alert("姓名长度有误！必须是2-10位")
                return false;
            }
            //if() return false;......
            return true;
        }
        $(document).ready(function () {

        });
    </script>
</head>
<body>
<form action="AddStudentServlet" method="post" >
    学号：<input type="text" name="sno" id="sno"><br>
    姓名：<input type="text" name="sname" id="sname"> <br>
    年龄：<input type="text" name="sage" id="sage"><br>
    地址：<input type="text" name="saddress" id="saddress"><br>
    <input type="submit" value="新增">
  </form>
</body>
</html>
