package servlet.edit;

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
 * 编辑学生信息
 */
@WebServlet(name = "EditServlet",value = "/edit")
public class EditServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        name = new String(name.getBytes("ISO8859-1"),"UTF-8");

        Student student = new Student();
        student.setId(id);
        student.setName(name);

        String msg = StudentServiceImpl.getInstance().editStudentInfo(student);
        HttpSession session = request.getSession();
        session.setAttribute("msg",msg);
        request.getRequestDispatcher("list").forward(request,response);
    }
}
