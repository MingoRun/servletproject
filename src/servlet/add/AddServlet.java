package servlet.add;

import entity.Student;
import service.impl.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 添加学生信息Servlet
 */
@WebServlet(name = "AddServlet",value = "/add")
public class AddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        name=new String(name.getBytes("iso8859-1"),"UTF-8");
        String age = request.getParameter("age");
        String sex = request.getParameter("sex");
        sex = new String(sex.getBytes("iso8859-1"),"UTF-8");

        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setAge(age);
        student.setSex(sex);

        String msg = StudentServiceImpl.getInstance().addStudentInfo(student);
        HttpSession session = request.getSession();
        session.setAttribute("msg",msg);
        request.getRequestDispatcher("list").forward(request,response);
    }
}
