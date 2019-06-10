package org.lanqiao.dao.impl;


import org.lanqiao.dao.IStudentDao;
import org.lanqiao.entity.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl0 implements IStudentDao {
    private final String URL = "jdbc:mysql://localhost:3306/javaWeb";
    private final String USERNAME = "root";
    private final String PWD = "123456";

    public boolean isExist(int sno){
        return queryStudentBySo(sno)==null?false:true;
    }
    public boolean addStudent(Student student){
        Connection connection=null;
        PreparedStatement pstmt=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PWD);
            String sql="insert into student(sno,sname,sage,saddress)values (?,?,?,?)";
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1,student.getSno());
            pstmt.setString(2,student.getSname());
            pstmt.setInt(3,student.getSage());
            pstmt.setString(4,student.getSaddress());
            int count=pstmt.executeUpdate();
            if(count>0) {
                return true;
            }else {
                return false;
            }

        }catch (ClassNotFoundException e){
            e.printStackTrace();
            return false;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            try {
                if (pstmt != null) pstmt.close();
                if (connection != null) connection.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //根据学号删除学生
    public boolean deleteStudentBySno(int sno){
        Connection connection=null;
        PreparedStatement pstmt=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PWD);
            String sql="delete from student where sno=?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1,sno);
            int count=pstmt.executeUpdate();
            if(count>0) {
                return true;
            }else {
                return false;
            }

        }catch (ClassNotFoundException e){
            e.printStackTrace();
            return false;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            try {
                if (pstmt != null) pstmt.close();
                if (connection != null) connection.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    //根据sno 知道待修改的人，把这个人修改成student对象
    public boolean updateStudentBySno(int sno,Student student){
        Connection connection=null;
        PreparedStatement pstmt=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PWD);
            String sql="update student set  sname =?,sage =?,saddress=? where sno=?";
            pstmt = connection.prepareStatement(sql);
            //修改后的内容

            pstmt.setString(1,student.getSname());
            pstmt.setInt(2,student.getSage());
            pstmt.setString(3,student.getSaddress());

            pstmt.setInt(4,sno);
            int count=pstmt.executeUpdate();
            if(count>0) {
                return true;
            }else {
                return false;
            }

        }catch (ClassNotFoundException e){
            e.printStackTrace();
            return false;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            try {
                if (pstmt != null) pstmt.close();
                if (connection != null) connection.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int getTotalCount() {
        return 0;
    }

    @Override
    public List<Student> queryStudentsByPage(int currentPage, int pageSize) {
        return null;
    }

    //查询全部学生(很多学生)
    public List<Student> queryAllStudent(){//查完之后返回Student对象
        List<Student>students=new ArrayList<>();
        Connection connection=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        Student student=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PWD);
            String sql="select * from  student";
            pstmt = connection.prepareStatement(sql);
            rs=pstmt.executeQuery();
            while(rs.next()){
                int no = rs.getInt("sno");
                String name=rs.getString("sname");
                int age=rs.getInt("sage");
                String address=rs.getString("saddress");
                //将查询到的学生信息封装起来
                student=new Student(no,name,age,address);
                students.add(student);
            }
            return students;

        }catch (ClassNotFoundException e){
            e.printStackTrace();
            return null;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (connection != null) connection.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    //根据学号查询学生
    public Student queryStudentBySo(int sno){//查完之后返回Student对象
        Connection connection=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        Student student=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PWD);
            String sql="select * from  student where sno=?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1,sno);
            rs=pstmt.executeQuery();
            if(rs.next()){
                int no = rs.getInt("sno");
                String name=rs.getString("sname");
                int age=rs.getInt("sage");
                String address=rs.getString("saddress");
                //将查询到的学生信息封装起来
                student=new Student(no,name,age,address);
            }
            return student;

        }catch (ClassNotFoundException e){
            e.printStackTrace();
            return null;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (connection != null) connection.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}


