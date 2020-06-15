package servlet.edit;

import entity.Student;
import service.impl.StudentServiceImpl;
import utils.Constants;
import utils.Page;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ToEditServlet",value = "/toEdit")
public class ToEditServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Student student = new Student();
        student.setId(id);

        Page<Student> page = StudentServiceImpl.getInstance().getStudentInfo(student);

        if (page!=null && page.getDataSize()==1) {
            request.setAttribute("student",page.getData().get(0));
            request.getRequestDispatcher(Constants.PRE_FIX.getValue()+"edit"+Constants.SUF_FIX.getValue()).forward(request,response);
        }else {
            HttpSession session = request.getSession();
            session.setAttribute("msg","修改编号为：" + id + "的学生信息有误, 请联系管理员！");
            request.getRequestDispatcher("list").forward(request,response);
        }

    }
}
