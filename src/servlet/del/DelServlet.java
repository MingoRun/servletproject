package servlet.del;

import service.impl.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "DelServlet",value = "/del")
public class DelServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        String msg = StudentServiceImpl.getInstance().delStudentById(id);

        HttpSession session = request.getSession();

        if (msg!=null && msg.length()>0) {
            session.setAttribute("msg",msg);
            response.sendRedirect("list");
        }else {
            session.setAttribute("msg","操作失败, 请联系管理员！");
            response.sendRedirect("list");
        }
    }
}
