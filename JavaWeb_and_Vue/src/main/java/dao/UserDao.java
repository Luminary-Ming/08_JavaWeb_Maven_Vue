package dao;

import pojo.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    /**
     * 查询数据库中所有的用户信息
     */
    public List<User> userList() throws ClassNotFoundException, SQLException;

    /**
     * 根据 id 删除用户
     */
    public Integer deleteUserById(Integer id) throws SQLException;

    /**
     * 向数据库中添加用户
     */
    public Integer addUser(User user) throws SQLException;

    /**
     * 根据 id 修改用户信息
     */
    public Integer UpdateUserById(User user) throws SQLException;
}
