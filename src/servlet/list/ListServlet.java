package servlet.list;

import service.impl.StudentServiceImpl;
import entity.Student;
import org.apache.log4j.Logger;
import utils.Constants;
import utils.Page;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 获取学生信息列表
 */
@WebServlet(name = "ListServlet",value = "/list")
public class ListServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(ListServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String currepntPage = request.getParameter("currentPage");
        String id = request.getParameter("id");
        //解决中文乱码
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        String age = request.getParameter("age");
        String sex = request.getParameter("sex");

        Student student = new Student();

        if (currepntPage!=null && currepntPage.length()>0) {
            student.setCurrentPage(Integer.parseInt(currepntPage));
        }

        if (id!=null && id.length()>0) {
            student.setId(id);
        }

        if (name!=null && name.length()>0) {
            name = new String(name.getBytes("iso8859-1"),"UTF-8");
            student.setName(name);
        }

        if (age!=null && age.length()>0) {
            student.setAge(age);
        }

        if (sex!=null && sex.length()>0) {
            sex = new String(sex.getBytes("iso8859-1"),"UTF-8");
            student.setSex(sex);
        }

        Page<Student> page = StudentServiceImpl.getInstance().getStudentInfo(student);
        request.setAttribute("page",page);
        request.setAttribute("stu",student);
        request.getRequestDispatcher(Constants.PRE_FIX.getValue()+"list"+Constants.SUF_FIX.getValue()).forward(request,response);
    }
}
