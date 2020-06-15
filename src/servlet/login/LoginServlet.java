package servlet.login;

import utils.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录Servlet
 */
@WebServlet(name = "LoginServlet",value = "/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

       String username = request.getParameter("username");
       String password = request.getParameter("password");

       if ((username!=null && username.length()>0) && (password!=null && password.length()>0)) {
           if (!"test".equals(username)) {
               request.setAttribute("msg","用户名不正确请重新输入");
               request.getRequestDispatcher("index"+ Constants.SUF_FIX.getValue()).forward(request,response);
           }else if (!"123456".equals(password)){
               request.setAttribute("msg","密码不正确请重新输入");
               request.getRequestDispatcher("index"+ Constants.SUF_FIX.getValue()).forward(request,response);
           } else {
               // request.getRequestDispatcher(Constants.PRE_FIX.getValue() + "list" + Constants.SUF_FIX.getValue()).forward(request,response);
               response.sendRedirect("list");
           }
       }else {
           request.setAttribute("msg","用户名或密码不能为空");
           request.getRequestDispatcher("index"+ Constants.SUF_FIX.getValue()).forward(request,response);
       }

    }
}
