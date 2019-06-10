package org.lanqiao.service.impl;

import org.lanqiao.dao.IStudentDao;
import org.lanqiao.dao.impl.StudentDaoImpl;
import org.lanqiao.dao.impl.StudentDaoImpl0;
import org.lanqiao.entity.Student;
import org.lanqiao.service.IStudentService;

import java.util.List;

//业务逻辑层：逻辑性的增删改查，是对Dao层的组装
public class StudentServiceImpl implements IStudentService {
    IStudentDao studentDao=new StudentDaoImpl();

    public List<Student> queryAllStudents(){
        return studentDao.queryAllStudent();
    }
    public Student queryStudentBySno(int sno){
        return studentDao.queryStudentBySo(sno);
    }

    public boolean updateStudentBySno(int sno,Student student){
        if(studentDao.isExist(sno)){
            return studentDao.updateStudentBySno(sno,student);
        }
        return false;
    }
    public boolean deleteStudentBySno(int sno){
        if(studentDao.isExist(sno)){
            return (boolean) studentDao.deleteStudentBySno(sno);
        }
        return false;
    }
    public boolean addStudent(Student student){
        if(!studentDao.isExist(student.getSno())){//如果不存在
            studentDao.addStudent(student);
            return true;
        }else {
            System.out.println("此人已存在！");
            return false;
        }
    }

    @Override
    //查询数据总条数
    public int getTotalCount() {
        return studentDao.getTotalCount();
    }

    @Override
    //查询当前页的数据集合
    public List<Student> queryStudentsByPage(int currentPage, int pagSize) {
        return studentDao.queryStudentsByPage(currentPage,pagSize);
    }
}
