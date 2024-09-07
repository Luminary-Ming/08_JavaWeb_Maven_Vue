package servlet;

import service.UserService;
import service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/deleteUser")
public class DeleteUserServlet extends HttpServlet {
    private UserService userService =new UserServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Integer i = userService.deleteUserById(Integer.valueOf(id));
        // 给请求返回一个 1（删除成功） 或 0（删除失败）
        resp.getWriter().write(i.toString());
    }
}
