package org.lanqiao.dao;

import org.lanqiao.entity.Student;

import java.util.List;

public interface IStudentDao {

    public boolean isExist(int sno);

    public boolean addStudent(Student student);

        //根据学号删除学生
    public boolean deleteStudentBySno(int sno);
    //根据sno 知道待修改的人，把这个人修改成student对象
    public boolean updateStudentBySno(int sno,Student student);

    public int getTotalCount();//查询总数据数

    public List<Student> queryStudentsByPage(int currentPage,int pageSize);//currentPage:当前页页码，pageSize：页面大小
    //查询全部学生(很多学生)
    public List<Student> queryAllStudent();

    //根据学号查询学生
    public Student queryStudentBySo(int sno);

}
