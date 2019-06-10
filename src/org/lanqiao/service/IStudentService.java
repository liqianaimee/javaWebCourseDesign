package org.lanqiao.service;

import org.lanqiao.dao.impl.StudentDaoImpl;
import org.lanqiao.entity.Student;

import java.util.List;

public interface IStudentService {

    public List<Student> queryAllStudents();

    public Student queryStudentBySno(int sno);

    public boolean updateStudentBySno(int sno,Student student);

    public boolean deleteStudentBySno(int sno);

    public boolean addStudent(Student student);

    public int getTotalCount();

    public List<Student> queryStudentsByPage(int currentPage,int pagSize);
}
