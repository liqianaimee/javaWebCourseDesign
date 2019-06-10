package org.lanqiao.dao.impl;


import org.lanqiao.dao.IStudentDao;
import org.lanqiao.entity.Student;
import org.lanqiao.util.DBUtil;

import java.io.RandomAccessFile;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements IStudentDao {
    private final String URL = "jdbc:mysql://localhost:3306/javaWeb?characterEncoding=utf-8 " ;
    private final String USERNAME = "root";
    private final String PWD = "123456";
    
    public boolean isExist(int sno){
      return queryStudentBySo(sno)==null?false:true;
    }

    public boolean addStudent(Student student){
        String sql="insert into student(sno,sname,sage,saddress)values (?,?,?,?)";
        Object[] params={student.getSno(),student.getSname(),student.getSage(),student.getSaddress()};
        return DBUtil.executeUpdate(sql,params);

    }

    //根据学号删除学生
    public boolean deleteStudentBySno(int sno){
        String sql="delete from student where sno=?";
        Object[] params={sno};
        return DBUtil.executeUpdate(sql,params);
        }

    //根据sno 知道待修改的人，把这个人修改成student对象
    public boolean updateStudentBySno(int sno,Student student){
        String sql="update student set  sname =?,sage =?,saddress=? where sno=?";
        Object[] params={student.getSname(),student.getSage(),student.getSaddress(),sno};
        return DBUtil.executeUpdate(sql,params);
        }

    @Override
    public int getTotalCount() {
        //查询总数据量
        String sql="select count(1) from student";
        return DBUtil.getTotalCount(sql);
    }

    @Override
    public List<Student> queryStudentsByPage(int currentPage, int pageSize) {



        List<Student> students=new ArrayList<>();

        try{
            String sql="select * from student limit ?,?";
            Object[] params={currentPage*pageSize,pageSize};
            ResultSet rs=DBUtil.executeQuery(sql,params);
            while (rs.next()){
                Student student=new Student(rs.getInt("sno"),rs.getString("sname"),rs.getInt("sage"),rs.getString("saddress"));
                students.add(student);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return students;
    }

    //查询全部学生(很多学生)
    public List<Student> queryAllStudent() {//查完之后返回Student对象
        List<Student>students=new ArrayList<>();
        Student student=null;
        PreparedStatement pstmt = null;
        ResultSet rs=null;
        try {
            String sql="select * from  student";
            rs= DBUtil.executeQuery(sql,null);
            //rs=pstmt.executeQuery();
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

        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            //查完之后关闭
            DBUtil.closeAll(rs,pstmt,DBUtil.connection);
//            try {
//                if (rs != null) rs.close();
//                if (pstmt != null) pstmt.close();
//                if (DBUtil.connection!= null)DBUtil.connection.close();
//            }
//            catch (SQLException e) {
//                e.printStackTrace();
//            }
        }
    }



    //根据学号查询学生
    public Student queryStudentBySo(int sno){//查完之后返回Student对象
        PreparedStatement pstmt = null;
        ResultSet rs=null;
        Student student=null;
        try {
            String sql="select * from  student where sno=?";
            Object[] params={sno};
            rs=DBUtil.executeQuery(sql,params);
            if(rs.next()){
                int no = rs.getInt("sno");
                String name=rs.getString("sname");
                int age=rs.getInt("sage");
                String address=rs.getString("saddress");
                //将查询到的学生信息封装起来
                student=new Student(no,name,age,address);
            }
            return student;

        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            //查完之后关闭
            DBUtil.closeAll(rs,pstmt,DBUtil.connection);
        }
    }

}




