package servlet;

import pojo.User;
import service.UserService;
import service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/addUser")
public class AddUserServlet extends HttpServlet {
    private UserService userService =new UserServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置请求中数据的编码格式
        req.setCharacterEncoding("utf-8");
        // 获取请求中的参数
        String username = req.getParameter("username");
        String type = req.getParameter("type");
        String age = req.getParameter("age");
        String sex = req.getParameter("sex");
        String address = req.getParameter("address");
        // 创建一个 User 对象，存放接收的数据
        User user = new User();
        user.setUsername(username);
        user.setType(type);
        user.setAge(Integer.valueOf(age));
        user.setSex(sex);
        user.setAddress(address);

        Integer i = userService.addUser(user);
        resp.getWriter().write(i.toString());
    }
}
