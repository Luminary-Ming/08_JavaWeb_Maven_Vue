package service.impl;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import pojo.User;
import service.UserService;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();
    /**
     * 查询数据库中所有的用户信息
     */
    @Override
    public List<User> userList() {
        try {
            return userDao.userList();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据 id 删除用户
     */
    @Override
    public Integer deleteUserById(Integer id) {
        try {
            return userDao.deleteUserById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 向数据库中添加用户
     */
    @Override
    public Integer addUser(User user) {
        try {
            return userDao.addUser(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据 id 修改用户信息
     */
    @Override
    public Integer UpdateUserById(User user) {
        try {
            return userDao.UpdateUserById(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
