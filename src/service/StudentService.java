package service;

import entity.Student;
import utils.Page;

public interface StudentService {

    /**
     * 查询学生信息
     */
    public Page<Student> getStudentInfo(Student s);

    /**
     * 获取学生总数
     */
    public int getStudentCount();

    /**
     * 根据编号删除学生信息
     */
    public String delStudentById(String id);

    /**
     * 添加学生信息
     */
    public String addStudentInfo(Student student);

    /**
     * 编辑学生信息
     */
    public String editStudentInfo(Student student);
}
