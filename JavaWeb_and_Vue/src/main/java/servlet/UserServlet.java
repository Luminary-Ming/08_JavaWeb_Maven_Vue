package servlet;

import com.google.gson.Gson;
import pojo.User;
import service.UserService;
import service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 查询数据库中所有的用户信息
 */
@WebServlet("/userList")
public class UserServlet extends HttpServlet {
    private UserService userService =new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = userService.userList();
        // 推荐使用 gson 工具（阿里是 fastjson），可以将 java 中的对象转成字符串格式的 json 对象，
        // 相反也能让字符串格式的 json 对象转成 java 对象
        Gson gson = new Gson();
        String json = gson.toJson(users);
        // 设置响应数据的格式
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write(json);
    }
}
