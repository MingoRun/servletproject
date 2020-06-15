package service.impl;

import service.StudentService;
import entity.Student;
import org.apache.log4j.Logger;
import utils.JDBCUtil;
import utils.Page;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 学生ServiceImpl类
 */
public class StudentServiceImpl implements StudentService {
    private static final Logger logger = Logger.getLogger(StudentServiceImpl.class);

    private static StudentService studentService = new StudentServiceImpl();

    public static StudentService getInstance() {
        return studentService;
    }

    private StudentServiceImpl(){}

    /**
     * 查询学生信息
     * @param s
     * @return
     */
    @Override
    public Page<Student> getStudentInfo(Student s) {
        boolean flag = false; //是否有查询条件

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Student student = null;
        Page<Student> page = null;
        StringBuffer sql = new StringBuffer("select * from student t where 1=1 ");

        if (s.getId()!=null && s.getId().length()>0) {
            flag = true;
            sql.append(" and t.Sid = '"+s.getId()+"' ");
        }

        if (s.getName()!=null && s.getName().length()>0) {
            flag = true;
            sql.append(" and t.Sname like '%"+s.getName()+"%' ");
        }

        if (s.getAge()!=null && s.getAge().length()>0) {
            flag = true;
            sql.append(" and DATE_FORMAT(t.Sage,'%Y-%m-%d') = '"+s.getAge()+"'");
        }

        if (s.getSex()!=null && s.getSex().length()>0) {
            flag = true;
            sql.append(" and t.Ssex = '"+s.getSex()+"'");
        }

        sql.append(" limit " + ((s.getCurrentPage()-1) * s.getPageSize()) + "," + s.getPageSize());
        //List<Map<String,Object>> list = new ArrayList<>();
        List<Student> list = new ArrayList<>();

        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql.toString());
            rs = ps.executeQuery();
            ResultSetMetaData md = rs.getMetaData();
            int colnumCount = md.getColumnCount();
            while (rs.next()) {
                //Map<String,Object> rowData = new HashMap<>();
                student = new Student();
                for (int i = 1; i <= colnumCount; i++) {
                    //rowData.put(md.getColumnName(i),rs.getObject(i));
                    if ("Sid".equals(md.getColumnName(i))) {
                        student.setId(String.valueOf(rs.getObject(i)));
                    }

                    if ("Sname".equals(md.getColumnName(i))) {
                        student.setName(String.valueOf(rs.getObject(i)));
                    }

                    if ("Sage".equals(md.getColumnName(i))) {
                        student.setAge(String.valueOf(rs.getObject(i)).substring(0, 10));
                    }

                    if ("Ssex".equals(md.getColumnName(i))) {
                        student.setSex(String.valueOf(rs.getObject(i)));
                    }
                }
                //list.add(rowData);
                list.add(student);
            }

            page = new Page<>();
            page.setData(list);
            page.setDataSize(list.size());

            if (flag!=true) {
                page.setRecordsRows(StudentServiceImpl.getInstance().getStudentCount());
            }else {
                page.setRecordsRows(list.size());
            }

            page.setCurrentPage(s.getCurrentPage());
            page.setPageSize(s.getPageSize());

            if (page.getRecordsRows() % page.getPageSize() == 0) {
                page.setPageNum(page.getRecordsRows() / page.getPageSize());
            } else {
                page.setPageNum(page.getRecordsRows() / page.getPageSize() + 1);
            }
        }catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs,ps,conn);
        }
        return page;
    }

    /**
     * 查询学生总数
     * @return
     */
    @Override
    public int getStudentCount() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "select count(*) from student";
        int count = 0;

        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            logger.error("查询学生总数失败：" + e.getSQLState());
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs,ps,conn);
        }
        return count;
    }

    @Override
    public String delStudentById(String id) {
        Connection conn = null;
        PreparedStatement ps = null;

        String sql = "delete from student t where t.Sid = ?";

        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1,id);
            int result = ps.executeUpdate();

            if (result>0) {
                return "删除编号为"+id+"的学生信息成功";
            }else {
                return "删除编号为"+id+"的学生信息失败";
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            JDBCUtil.close(ps,conn);
        }
    }

    @Override
    public String addStudentInfo(Student student) {
        Connection conn = null;
        PreparedStatement ps = null;

        String sql = "insert into student values(?,?,?,?)";

        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1,student.getId());
            ps.setString(2,student.getName());

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            Date date = sdf.parse(student.getAge());
            ps.setDate(3,new java.sql.Date(date.getTime()));

            ps.setString(4,student.getSex());

            int result = ps.executeUpdate();

            if (result>0) {
                return "添加编号为: " + student.getId()+", 姓名为: " + student.getName()+"的学生信息成功！";
            }else {
                return "添加编号为: " + student.getId()+", 姓名为: " + student.getName()+"的学生信息失败！";
            }

        } catch (SQLException e) {
            logger.error("添加学生信息失败：" + e.getMessage());
            e.printStackTrace();
            return null;
        } catch (ParseException e) {
            logger.error("日期转换格式有误：" + e.getMessage());
            e.printStackTrace();
            return null;
        } finally {
            JDBCUtil.close(ps,conn);
        }

    }

    /**
     * 编辑学生信息
     * @param student
     * @return
     */
    @Override
    public String editStudentInfo(Student student) {
        Connection conn = null;
        PreparedStatement ps = null;
        StringBuffer sql = new StringBuffer("update student t set ");

        if (student.getName()!=null && student.getName().length()>0) {
            sql.append(" t.Sname = '"+student.getName().trim()+"'");
        }

        sql.append(" where t.Sid = '"+student.getId()+"'");

        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql.toString());
            int result = ps.executeUpdate();

            if (result>0) {
                return "更新编号为：" + student.getId() + "的学生信息成功！";
            }else {
                return "更新编号为：" + student.getId() + "的学生信息失败！";
            }
        } catch (SQLException e) {
            logger.error("编辑学生有误：" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        StudentServiceImpl impl = new StudentServiceImpl();
        System.out.println(impl.getStudentCount());
    }
}
